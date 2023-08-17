package com.gotracrat.attributesandtypes.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@ConfigurationProperties("spring.datasource")
public class DBConfiguration {
	@Profile("dev")
	@Bean
	public String devDatabaseConnection() {
		return "DB connection for DEV - OLEM";
	}

	@Profile("prod")
	@Bean
	public String prodDatabaseConnection() {
		return "DB Connection for PROD - OLEM_DEV";
	} 
}
