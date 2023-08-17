/*
 * Created on 2018-10-27 ( Date ISO 2018-10-27 - Time 19:57:22 )
 * Generated by
 */
package com.gotracrat.managelocation.service;

import java.util.List;
import java.util.Optional;

import com.gotracrat.managelocation.exception.ResourceNotFoundException;
import com.gotracrat.managelocation.utils.GoTracratConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gotracrat.managelocation.resource.AspnetMembershipResource;
import com.gotracrat.managelocation.resource.LogManagementResource;
import com.gotracrat.managelocation.entity.AspnetMembership;
import com.gotracrat.managelocation.entity.UserLog;
import com.gotracrat.managelocation.repository.AspnetMembershipDAO;
import com.gotracrat.managelocation.repository.AspnetMembershipRepository;
import com.gotracrat.managelocation.repository.UserLogRepository;

/**
 * Service implementation for AspnetMembership.
 *
 * @author Prabhakar
 */
@Service
public class AspnetMembershipServiceImpl implements AspnetMembershipService {

	@Autowired
	private AspnetMembershipRepository membershipRepository;

	@Autowired
	private UserLogRepository userLogRepository;

	@Autowired
	private AspnetMembershipDAO aspnetMembershipDAO;

	@Override
	public AspnetMembership get(String userid) {
		return membershipRepository.findById(userid).orElseThrow(() ->
				new ResourceNotFoundException(GoTracratConstants.USER_NOT_FOUND));
	}

	@Override
	public Boolean delete(String userid) {
		if (membershipRepository.findById(userid).isPresent()) {
			membershipRepository.deleteById(userid);
			return true;
		}
		return false;
	}

	@Override
	public AspnetMembership create(AspnetMembership aspnetMembership) {
		return membershipRepository.save(aspnetMembership);
	}

	@Override
	public AspnetMembership save(AspnetMembership aspnetMembership) {
		final String pk = aspnetMembership.getUserid();
		if (membershipRepository.findById(pk).isPresent()) {
			return membershipRepository.save(aspnetMembership);
		}
		throw new ResourceNotFoundException(GoTracratConstants.USER_NOT_FOUND);
	}

	@Override
	public AspnetMembershipResource updateLoginDate(AspnetMembershipResource aspnetMembership) {
		return aspnetMembershipDAO.updateLoginDate(aspnetMembership);

	}

	@Override
	public void updateUserLogOut(String userId) {
		Optional<AspnetMembership> userOptional = Optional.ofNullable(membershipRepository.findById(userId).orElseThrow(() ->
				new ResourceNotFoundException(GoTracratConstants.USER_NOT_FOUND)));
		AspnetMembership user = null;
		if (userOptional.isPresent()) {
			user = userOptional.get();
			user.setLoggedIn(Boolean.FALSE);
			user.setLastlogoutdate(java.util.Calendar.getInstance().getTime());
			membershipRepository.save(user);
		}
	}

	@Override
	public List<LogManagementResource> getUserlog(String companyid) {
		return aspnetMembershipDAO.getUsersLog(companyid);
	}

	@Override
	public List<UserLog> getUserlogDetails(Integer companyid, String userName) {
		return userLogRepository.findByuserNameAndCompanyIDOrderByLogDateDesc(userName, companyid);
	}

	@Override
	public Boolean exist(AspnetMembership aspnetMembership) {
		return membershipRepository.existsById(aspnetMembership.getUserid());
	}

	@Override
	public Integer getUserCountByEmail(String emailid) {
		return aspnetMembershipDAO.getCountOfUserIDByEmail(emailid);
	}

}