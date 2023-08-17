/*
 * package com.gotracrat.managecompany.config;
 * 
 * import org.springframework.boot.autoconfigure.security.SecurityProperties;
 * import org.springframework.context.annotation.Configuration; import
 * org.springframework.core.annotation.Order; import
 * org.springframework.http.HttpMethod; import
 * org.springframework.security.config.annotation.web.builders.HttpSecurity;
 * import
 * org.springframework.security.oauth2.config.annotation.web.configuration.
 * EnableResourceServer; import
 * org.springframework.security.oauth2.config.annotation.web.configuration.
 * ResourceServerConfigurerAdapter; import
 * org.springframework.security.oauth2.config.annotation.web.configurers.
 * ResourceServerSecurityConfigurer;
 * 
 *//**
	 * Created by RANGAREJ on 11/17/2018.
	 *//*
		 * 
		 * @Configuration
		 * 
		 * @EnableResourceServer
		 * 
		 * @Order(SecurityProperties.ACCESS_OVERRIDE_ORDER-1) public class
		 * ResourceServerConfiguration extends ResourceServerConfigurerAdapter {
		 * 
		 * private static final String RESOURCE_ID = "resource-server-rest-api"; private
		 * static final String SECURED_READ_SCOPE = "#oauth2.hasScope('read')"; private
		 * static final String SECURED_WRITE_SCOPE = "#oauth2.hasScope('write')";
		 * private static final String SECURED_PATTERN = "/api/company/**"; private
		 * static final String SECURED_PATTERN_VENDOR = "/api/vendor/**"; private static
		 * final String SECURED_PATTERN_TEMPLATE = "/api/template/**";
		 * 
		 * @Override public void configure(ResourceServerSecurityConfigurer resources) {
		 * resources.resourceId(RESOURCE_ID); }
		 * 
		 * @Override public void configure(HttpSecurity http) throws Exception {
		 * http.requestMatchers() .antMatchers(SECURED_PATTERN)
		 * .antMatchers(SECURED_PATTERN_VENDOR) .antMatchers(SECURED_PATTERN_TEMPLATE)
		 * .and().authorizeRequests() .antMatchers(HttpMethod.GET,
		 * SECURED_PATTERN).access(SECURED_WRITE_SCOPE) .antMatchers(HttpMethod.GET,
		 * SECURED_PATTERN_VENDOR).access(SECURED_WRITE_SCOPE)
		 * .antMatchers(HttpMethod.GET,
		 * SECURED_PATTERN_TEMPLATE).access(SECURED_WRITE_SCOPE); } }
		 * 
		 */