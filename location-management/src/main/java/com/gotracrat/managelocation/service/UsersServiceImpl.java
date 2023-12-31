/*
 * Created on 2018-10-27 ( Date ISO 2018-10-27 - Time 19:57:22 )
 * Generated by
 */
package com.gotracrat.managelocation.service;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import com.gotracrat.managelocation.exception.ResourceNotFoundException;
import com.gotracrat.managelocation.repository.*;
import com.gotracrat.managelocation.utils.GoTracratConstants;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.gotracrat.managelocation.resource.AspnetMembershipResource;
import com.gotracrat.managelocation.resource.AspnetUsersResource;
import com.gotracrat.managelocation.resource.ProfileResource;
import com.gotracrat.managelocation.entity.AspnetMembership;
import com.gotracrat.managelocation.entity.AspnetUsers;
import com.gotracrat.managelocation.entity.Profile;
import com.gotracrat.managelocation.entity.UserLog;
import com.gotracrat.managelocation.entity.UserSecurity;

/**
 * Service implementation for AspnetUsers.
 *
 * @author Prabhakar
 */
@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersRepository aspnetUsersRepository;

    @Autowired
    private AspnetMembershipRepository aspnetMembershipRepository;

    @Autowired
    private AspnetMembershipServiceImpl aspnetMembershipService;

    @Autowired
    private ProfileServiceImpl profileService;

    @Autowired
    private UserSecurityDAO userSecurityDAO;

    @Autowired
    private UserDAO aspnetUserDAO;

    @Autowired
    private UserSecurityRepository usersecurityRepository;

    @Autowired
    private ProfileRepository profileRepository;



    @Override
    public AspnetUsersResource create(AspnetUsersResource aspnetUsers, UserLog userLog) {

        AspnetUsers user = new AspnetUsers();
        Date date = new Date();
        aspnetUsers.setLastactivitydate(date);
        aspnetUsers.setApplicationid("2E8DCDA9-84CE-4A7F-B8EB-CBD5E815656B");
        aspnetUsers.setLoweredusername(aspnetUsers.getUsername());
        aspnetUsers.setIsanonymous(false);
        BeanUtils.copyProperties(aspnetUsers, user);
        AspnetUsers aspnetUsersTemp = aspnetUsersRepository.save(user);
        AspnetUsersResource aspnetUsersResource1 = this.BuildProfileAndMembership(aspnetUsers, aspnetUsersTemp);
        aspnetUsersResource1.setUserid(aspnetUsersTemp.getUserid());
        this.saveUserSecurity(aspnetUsersResource1);
        return aspnetUsersResource1;
    }

    private void saveUserSecurity(AspnetUsersResource aspnetUsersResource1) {
        UserSecurity usersecurityTemp = new UserSecurity();
        usersecurityTemp.setRoleid("48146915-0F7E-429D-ACE2-C7993D6438D3");
        usersecurityTemp.setCompanyid(aspnetUsersResource1.getActualCompanyId());
        usersecurityTemp.setLocationid(0);
        usersecurityTemp.setUserid(aspnetUsersResource1.getUserid());
        usersecurityRepository.save(usersecurityTemp);
    }

    private AspnetUsersResource BuildProfileAndMembership(AspnetUsersResource aspnetUsersResource, AspnetUsers user) {

        AspnetMembership aspnetMembership = new AspnetMembership();
        Profile profile = new Profile();
        profile.setAcceptedterms(false);
        profile.setSendreceiverfq(false);
        profile.setCompanyid(aspnetUsersResource.getCompanyid());
        profile.setIsowneradmin(false);
        profile.setIsOwnerAdminReadOnly(false);
        profile.setHidepricing(true);
        profile.setFirstname(aspnetUsersResource.getFirstname());
        profile.setLastname(aspnetUsersResource.getLastname());
        profile.setJobtitle(aspnetUsersResource.getJobtitle());
        profile.setDepartment(aspnetUsersResource.getDepartment());
        profile.setPhone(aspnetUsersResource.getPhone());
        profile.setMobilephone(aspnetUsersResource.getMobilephone());
        profile.setFax(aspnetUsersResource.getFax());
        profile.setToplocationid(0);
        profile.setPreferredlocationid(0);
        aspnetMembership.setUserid(user.getUserid());
        aspnetMembership.setApplicationid(aspnetUsersResource.getApplicationid());
        String password = aspnetUsersResource.getPassword();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);
        aspnetMembership.setPassword(hashedPassword);
        aspnetMembership.setPasswordformat(1);
        aspnetMembership.setPasswordsalt(aspnetUsersResource.getPasswordsalt());
        aspnetMembership.setMobilepin(aspnetUsersResource.getMobilepin());
        aspnetMembership.setEmail(aspnetUsersResource.getEmail());
        aspnetMembership.setLoweredemail(aspnetUsersResource.getEmail());
        aspnetMembership.setPasswordquestion(aspnetUsersResource.getPasswordquestion());
        aspnetMembership.setPasswordanswer(aspnetUsersResource.getPasswordanswer());
        aspnetMembership.setIsapproved(true);
        aspnetMembership.setIslockedout(false);
        aspnetMembership.setCreatedate(aspnetUsersResource.getCreatedate());
        aspnetMembership.setLastlockoutdate(aspnetUsersResource.getLastlockoutdate());
        aspnetMembership.setLastlogindate(aspnetUsersResource.getLastlogindate());
        aspnetMembership.setIsapproved(aspnetUsersResource.getIsapproved());
        aspnetMembership.setIslockedout(aspnetUsersResource.getIslockedout());
        aspnetMembership.setFailedpasswordanswerattemptcount(0);
        aspnetMembership.setFailedpasswordanswerattemptwindowstart(
                aspnetUsersResource.getFailedpasswordanswerattemptwindowstart());
        aspnetMembership.setFailedpasswordattemptwindowstart(aspnetUsersResource.getFailedpasswordattemptwindowstart());
        aspnetMembership.setLastpasswordchangeddate(aspnetUsersResource.getLastpasswordchangeddate());
        aspnetMembership.setFailedpasswordattemptcount(0);
        profile.setUserid(user.getUserid());
        AspnetMembership aspnetmembershipTemp = aspnetMembershipService.create(aspnetMembership);
        Profile profileTemp = profileService.create(profile);
        aspnetUsersResource.setProfileid(profileTemp.getProfileid());
        aspnetUsersResource.setAcceptedterms(profileTemp.getAcceptedterms());
        aspnetUsersResource.setSendreceiverfq(profileTemp.getSendreceiverfq());
        aspnetUsersResource.setIsowneradmin(profileTemp.getIsowneradmin());
        aspnetUsersResource.setHidepricing(profileTemp.getHidepricing());
        aspnetUsersResource.setApplicationid(aspnetmembershipTemp.getApplicationid());
        aspnetUsersResource.setCreatedate(aspnetmembershipTemp.getCreatedate());
        aspnetUsersResource.setLastlockoutdate(aspnetmembershipTemp.getLastlockoutdate());
        aspnetUsersResource.setLastlogindate(aspnetmembershipTemp.getLastlogindate());
        aspnetUsersResource.setIsapproved(aspnetmembershipTemp.getIsapproved());
        aspnetUsersResource.setIslockedout(aspnetmembershipTemp.getIslockedout());
        aspnetUsersResource
                .setFailedpasswordanswerattemptcount(aspnetmembershipTemp.getFailedpasswordanswerattemptcount());
        aspnetUsersResource.setFailedpasswordanswerattemptwindowstart(
                aspnetmembershipTemp.getFailedpasswordanswerattemptwindowstart());
        aspnetUsersResource.setLastpasswordchangeddate(aspnetmembershipTemp.getLastpasswordchangeddate());
        aspnetUsersResource.setFailedpasswordattemptcount(aspnetmembershipTemp.getFailedpasswordattemptcount());
        aspnetUsersResource
                .setFailedpasswordattemptwindowstart(aspnetmembershipTemp.getFailedpasswordattemptwindowstart());

        return aspnetUsersResource;
    }

    @Override
    public Boolean exist(AspnetUsers aspnetUsers) {
        return aspnetUsersRepository.existsById(aspnetUsers.getUserid());
    }

    public List<ProfileResource> getUserNames(Integer companyid) throws SQLException {
        return userSecurityDAO.findAllBycompanyid(companyid);
    }

    public AspnetMembershipResource changePassword(AspnetMembershipResource aspnetMembershipResource) {

        final String pk = aspnetMembershipResource.getUserid();

        if (aspnetMembershipRepository.findById(pk).isPresent()) {

            AspnetMembership aspnetMembership = aspnetMembershipRepository.findById(pk).orElseThrow(() ->
                    new ResourceNotFoundException(GoTracratConstants.USER_NOT_FOUND));

            String pwd = aspnetMembership.getPassword();

            String cpassword = aspnetMembershipResource.getCurrentpassword();
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

            if (passwordEncoder.matches(cpassword, pwd)) {
                String pass = aspnetMembershipResource.getNewpassword();
                String hashedPassword = passwordEncoder.encode(pass);
                aspnetMembership.setPassword(hashedPassword);
                aspnetMembership.setLastpasswordchangeddate(aspnetMembershipResource.getLastpasswordchangeddate());
                AspnetMembership aspnetmembertemp = aspnetMembershipService.save(aspnetMembership);
                AspnetMembershipResource aspnetmemberResource1 = new AspnetMembershipResource();
                BeanUtils.copyProperties(aspnetmembertemp, aspnetmemberResource1);
                return aspnetmemberResource1;
            }

        }
        return aspnetMembershipResource;
    }

    @Override
    public AspnetUsers getUserIDByUserName(String userName) {
        return aspnetUsersRepository.findByUsername(userName);

    }

    @Override
    public List<ProfileResource> getUserProfilesAsAdmin(Integer companyid) throws SQLException {
        return userSecurityDAO.getUserProfilesAsAdmin(companyid);
    }

    public Integer getUserCountByUserName(String userName) {
        return aspnetUserDAO.getCountOfUserIDByUserName(userName);
    }


    @Override
    public String delete(String userId, Integer profileId, UserLog userLog) {
        if (aspnetUsersRepository.findById(userId).isPresent()) {
            aspnetMembershipService.delete(userId);
            profileService.delete(profileId);
            aspnetUsersRepository.deleteById(userId);
            return GoTracratConstants.USER_DELETED;
        }
        throw new ResourceNotFoundException(GoTracratConstants.INVALID_USER_ID + userId);
    }


    @Override
    public void save(ProfileResource profileResource) {
        Optional<Profile> profileOptional = profileRepository.findById(profileResource.getProfileid());
        if(profileOptional.isPresent()) {
            Profile profile = profileOptional.get();
            profile.setAcceptedterms(profileResource.getAcceptedterms());
            profile.setSendreceiverfq(profileResource.getSendreceiverfq());
            profile.setCompanyid(profileResource.getCompanyid());
            profile.setIsowneradmin(profileResource.getIsowneradmin());
            profile.setIsOwnerAdminReadOnly(profileResource.getIsOwnerAdminReadOnly());
            profile.setHidepricing(profileResource.getHidepricing());
            profile.setFirstname(profileResource.getFirstname());
            profile.setLastname(profileResource.getLastname());
            profile.setJobtitle(profileResource.getJobtitle());
            profile.setDepartment(profileResource.getDepartment());
            profile.setPhone(profileResource.getPhone());
            profile.setMobilephone(profileResource.getMobilephone());
            profile.setFax(profileResource.getFax());
            profile.setToplocationid(profileResource.getToplocationid());
            profile.setPreferredlocationid(profileResource.getPreferredlocationid());
            profile.setUserid(profileResource.getUserid());
            profileRepository.save(profile);
            Optional<AspnetUsers> userOptional = aspnetUsersRepository.findById(profileResource.getUserid());
            if(userOptional.isPresent()) {
                AspnetUsers user = userOptional.get();
                user.setUsername(profileResource.getUsername());
                user.setLoweredusername(profileResource.getUsername());
                aspnetUsersRepository.save(user);
                AspnetMembership aspnetMembership = aspnetMembershipRepository.findById(profileResource.getUserid()).get();
                aspnetMembership.setEmail(profileResource.getEmail());
                aspnetMembership.setLoweredemail(profileResource.getEmail());
                aspnetMembershipRepository.save(aspnetMembership);
            }
        }
        else {
            throw new ResourceNotFoundException("Invalid profileId or userId");
        }
    }
}
