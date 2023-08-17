package com.gotracrat.managecompany.controller;


import com.gotracrat.managecompany.entity.AspnetMembership;
import com.gotracrat.managecompany.entity.AspnetUsers;
import com.gotracrat.managecompany.resource.ForgotPasswordResource;
import com.gotracrat.managecompany.resource.ResetPasswordDTO;
import com.gotracrat.managecompany.service.ForgotPasswordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping(value = "/api/v1/password")
public class ForgotPasswordController {
    @Autowired
    ForgotPasswordService forgotPasswordService;


    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Value("${spring.profiles.active}")
    private String profile;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

    private static final String NOT_FOUND = "account not  found for email address";


    @PostMapping(value = "/forgot/{userName}")
    public ResponseEntity<ForgotPasswordResource> submitForgotPassword(
            @PathVariable("userName") String userName, HttpServletRequest request) {
        ForgotPasswordResource forgotPasswordResource = new ForgotPasswordResource();
        AspnetUsers aspnetUser = forgotPasswordService.getUserByUserName(userName);
        if (aspnetUser == null) {
            forgotPasswordResource.setStatus("NotFound");
            log.info(NOT_FOUND);
        } else {
            // Generate random 36-character string token for reset password
            AspnetMembership aspnetMembership = aspnetUser.getAspnetMembership();
            aspnetMembership.setResetToken(UUID.randomUUID().toString());
            // Save token to database
            forgotPasswordService.save(aspnetMembership);
            String appUrl;
            if(profile.equalsIgnoreCase("dev")){
                appUrl = request.getScheme() + "://3.22.116.126:100";
            } else {
                appUrl = request.getScheme() + "://gotracrat.com";
            }
            // Email message
            SimpleMailMessage passwordResetEmail = new SimpleMailMessage();
            passwordResetEmail.setFrom("testikonicit@gmail.com");
            passwordResetEmail.setTo(aspnetMembership.getEmail());
            passwordResetEmail.setSubject("TracRat Password Reset Email Request");
            passwordResetEmail.setText(
                    "Your password for the TracRat Equipment Management site can be reset on click the following link:\n"
                            + appUrl + "/#/reset?token=" + aspnetMembership.getResetToken());
            forgotPasswordService.sendEmail(passwordResetEmail);
            // Add success message to view
            forgotPasswordResource.setStatus("Success");
            log.info("successMessage", "A password reset link has been sent to " + aspnetMembership);
        }
        return new ResponseEntity<>(forgotPasswordResource, HttpStatus.OK);
    }

    @PostMapping(value = "/reset")
    public ResponseEntity<ForgotPasswordResource> resetPassword(@RequestBody ResetPasswordDTO resetPasswordDTO) {
        ForgotPasswordResource forgotPasswordResource = new ForgotPasswordResource();
        // Find the AspnetMembership associated with the reset token
        Optional<AspnetMembership> aspnetMembershipOptional = forgotPasswordService
                .findUserByResetToken(resetPasswordDTO.getResetToken());
        // This should always be non-null but we check just in case
        if (aspnetMembershipOptional.isPresent() && aspnetMembershipOptional.get().getResetToken() != null) {
            AspnetMembership aspnetMembership = aspnetMembershipOptional.get();
            // Set new password
            aspnetMembership.setPassword(bCryptPasswordEncoder.encode(resetPasswordDTO.getPassword()));
            aspnetMembership.setLastpasswordchangeddate(new Date());
            // Set the reset token to null so it cannot be used again
            aspnetMembership.setResetToken(null);
            // Save AspnetMembership
            forgotPasswordService.save(aspnetMembership);
            log.info("SuccessMessage", "Password reset Successful for User: " + aspnetMembership.getUserid());
            forgotPasswordResource.setStatus("Success");
            // In order to set a model attribute on a redirect, we must use
            // RedirectAttributes
            //redir.addFlashAttribute("successMessage", "You have successfully reset your password.  You may now login.");
        } else {
            log.info("errorMessage", "Oops!  This is an invalid password reset link.");
            forgotPasswordResource.setStatus("NotFound");
        }
        return new ResponseEntity<ForgotPasswordResource>(forgotPasswordResource, HttpStatus.OK);
    }

}

