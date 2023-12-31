/*
 * Created on 2018-10-27 ( Date ISO 2018-10-27 - Time 19:57:24 )
 * Generated by 
*/
package com.gotracrat.managelocation.service;
import com.gotracrat.managelocation.exception.ResourceNotFoundException;
import com.gotracrat.managelocation.utils.GoTracratConstants;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.gotracrat.managelocation.resource.ReportSecurityResource;
import com.gotracrat.managelocation.entity.Reportsecurity;
import com.gotracrat.managelocation.repository.ReportsecurityRepository;

/**
 * Service implementation for Reportsecurity.
 * @author Prabhakar
 */
@Service
public class ReportSecurityServiceImpl implements ReportSecurityService {

	@Autowired
	private ReportsecurityRepository reportsecurityRepository;

	@Override
	public Page<Reportsecurity> search(Pageable pageable) {
		return reportsecurityRepository.findAll(pageable);
	}

	@Override
	public Reportsecurity get(Integer profileid) {
		return reportsecurityRepository.findById(profileid).orElseThrow(() ->
				new ResourceNotFoundException(GoTracratConstants.PROFILE_NOT_FOUND));
	}

	@Override
	public String delete(Integer profileid) {
		if (reportsecurityRepository.findById(profileid).isPresent()) {
			reportsecurityRepository.deleteById(profileid);
			return GoTracratConstants.PROFILE_DELETED;
		}
		throw  new ResourceNotFoundException(GoTracratConstants.INVALID_PROFILE_ID+profileid);
	}

	@Override
	public Reportsecurity create(Reportsecurity reportsecurity) {
		return reportsecurityRepository.save(reportsecurity);
	}

	@Override
	public ReportSecurityResource save(ReportSecurityResource reportsecurityResource) {
		final Integer pk = reportsecurityResource.getProfileid();
		if (reportsecurityRepository.findById(pk).isPresent()) {
			Reportsecurity reportSecurity=new Reportsecurity();
			BeanUtils.copyProperties(reportsecurityResource, reportSecurity);
			Reportsecurity reportSecuritysaved=reportsecurityRepository.save(reportSecurity);
			ReportSecurityResource reportsecurityResourceTemp=new ReportSecurityResource();
			BeanUtils.copyProperties(reportSecuritysaved, reportsecurityResourceTemp);
			return reportsecurityResourceTemp;
		}
		else
		{
			Reportsecurity reportSecurity=reportsecurityRepository.findById(pk).orElseThrow(() ->
					new ResourceNotFoundException(GoTracratConstants.INVALID_ID));
			BeanUtils.copyProperties(reportsecurityResource, reportSecurity);
			Reportsecurity reportSecuritysaved=reportsecurityRepository.save(reportSecurity);
			ReportSecurityResource reportsecurityResourceTemp=new ReportSecurityResource();
			BeanUtils.copyProperties(reportSecuritysaved, reportsecurityResourceTemp);
			return reportsecurityResourceTemp;
		}
	}

	@Override
	public Boolean exist(Reportsecurity reportsecurity) {
		return reportsecurityRepository.existsById(reportsecurity.getProfileid());
	}


}
