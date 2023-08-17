package com.gotracrat.managelocation.service;

import org.springframework.mail.SimpleMailMessage;


public interface EmailServiceForgotPassword {
	public void sendEmail(SimpleMailMessage email);
}
