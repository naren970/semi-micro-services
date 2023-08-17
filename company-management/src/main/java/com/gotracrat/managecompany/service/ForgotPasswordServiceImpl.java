package com.gotracrat.managecompany.service;

import com.gotracrat.managecompany.entity.AspnetMembership;
import com.gotracrat.managecompany.entity.AspnetUsers;
import com.gotracrat.managecompany.repository.AspnetUsersRepository;
import com.gotracrat.managecompany.repository.ForgotPasswordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service("userServiceForgetPassword")
public class ForgotPasswordServiceImpl implements ForgotPasswordService {

    @Autowired(required = true)
    private JavaMailSender mailSender;

    @Autowired
    ForgotPasswordRepository userRepositoryForgotPassword;

    @Autowired
    AspnetUsersRepository aspnetUsersRepository;

    @Override
    public Optional<AspnetMembership> findUserByEmail(String email) {
        return userRepositoryForgotPassword.findByEmail(email);
    }

    @Override
    public Optional<AspnetMembership> findUserByResetToken(String resetToken) {
        return userRepositoryForgotPassword.findByResetToken(resetToken);
    }

    @Override
    @Transactional
    public void save(AspnetMembership aspnetMembership) {
        userRepositoryForgotPassword.save(aspnetMembership);
    }

    public AspnetUsers getUserByUserName(String name) {
        return aspnetUsersRepository.fetchUserByUserName(name);
    }

    @Override
    public void sendEmail(SimpleMailMessage email) {
        mailSender.send(email);
    }

}