package com.gotracrat.managecompany.service;

import com.gotracrat.managecompany.entity.AspnetMembership;
import com.gotracrat.managecompany.entity.AspnetUsers;
import org.springframework.mail.SimpleMailMessage;

import java.util.Optional;


public interface ForgotPasswordService {

    public Optional<AspnetMembership> findUserByEmail(String email);

    public Optional<AspnetMembership> findUserByResetToken(String resetToken);

    public void save(AspnetMembership aspnetMembership);
    
	public AspnetUsers getUserByUserName(String userName);

    public void sendEmail(SimpleMailMessage email);
}
    
    
