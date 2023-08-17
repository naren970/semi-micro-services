/*
 * package com.gotracrat.managelocation.config;
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
		 * @Order(SecurityProperties.ACCESS_OVERRIDE_ORDER - 1) public class
		 * ResourceServerConfiguration extends ResourceServerConfigurerAdapter {
		 * 
		 * private static final String RESOURCE_ID = "resource-server-rest-api"; private
		 * static final String SECURED_READ_SCOPE = "#oauth2.hasScope('read')"; private
		 * static final String SECURED_WRITE_SCOPE = "#oauth2.hasScope('write')";
		 * private static final String SECURED_PATTERN = "/api/location/**"; private
		 * static final String SECURED_PATTERN_ITEM = "/api/item/**"; private static
		 * final String SECURED_PATTERN_USER = "/api/users/**"; private static final
		 * String SECURED_PATTERN_REPORTSECURITY = "/api/reportsecurity/**"; private
		 * static final String SECURED_PATTERN_USER_SECURITY = "/api/usersecurity/**";
		 * private static final String SECURED_PATTERN_TRANSFER_LOG =
		 * "/api/transferlog/**"; private static final String
		 * SECURED_PATTERN_WARRANTY_TYPE = "/api/warrantytype/**"; private static final
		 * String SECURED_PATTERN_FAILURETYPE = "/api/failuretype/**"; private static
		 * final String SECURED_PATTERN_ITEMREPAIRITEM = "/api/itemrepairitem/**";
		 * private static final String SECURED_PATTERN_ITEMREPAIR =
		 * "/api/itemrepair/**"; private static final String SECURED_PATTERN_PROFILE =
		 * "/api/profile/**"; private static final String SECURED_PATTERN_DASHBOARD =
		 * "/api/dashboard/**";
		 * 
		 * @Override public void configure(ResourceServerSecurityConfigurer resources) {
		 * resources.resourceId(RESOURCE_ID); }
		 * 
		 * @Override public void configure(HttpSecurity http) throws Exception {
		 * http.requestMatchers() .antMatchers(SECURED_PATTERN) .antMatchers(
		 * SECURED_PATTERN_ITEM).antMatchers(SECURED_PATTERN_USER).antMatchers(
		 * SECURED_PATTERN_USER_SECURITY).antMatchers(SECURED_PATTERN_TRANSFER_LOG).
		 * antMatchers(SECURED_PATTERN_WARRANTY_TYPE).antMatchers(
		 * SECURED_PATTERN_FAILURETYPE).antMatchers(SECURED_PATTERN_ITEMREPAIRITEM).
		 * antMatchers(SECURED_PATTERN_ITEMREPAIR).antMatchers(
		 * SECURED_PATTERN_REPORTSECURITY).antMatchers(SECURED_PATTERN_PROFILE).
		 * antMatchers(SECURED_PATTERN_DASHBOARD).and().authorizeRequests()
		 * .antMatchers(HttpMethod.GET, SECURED_PATTERN).access(SECURED_WRITE_SCOPE)
		 * 
		 * .antMatchers(HttpMethod.GET,
		 * SECURED_PATTERN_ITEM).access(SECURED_WRITE_SCOPE)
		 * .antMatchers(HttpMethod.GET,
		 * SECURED_PATTERN_USER).access(SECURED_WRITE_SCOPE)
		 * .antMatchers(HttpMethod.GET,
		 * SECURED_PATTERN_USER_SECURITY).access(SECURED_WRITE_SCOPE)
		 * .antMatchers(HttpMethod.GET,
		 * SECURED_PATTERN_TRANSFER_LOG).access(SECURED_WRITE_SCOPE)
		 * .antMatchers(HttpMethod.GET,
		 * SECURED_PATTERN_WARRANTY_TYPE).access(SECURED_WRITE_SCOPE)
		 * .antMatchers(HttpMethod.GET,
		 * SECURED_PATTERN_FAILURETYPE).access(SECURED_WRITE_SCOPE)
		 * .antMatchers(HttpMethod.GET,
		 * SECURED_PATTERN_ITEMREPAIRITEM).access(SECURED_WRITE_SCOPE)
		 * .antMatchers(HttpMethod.GET,
		 * SECURED_PATTERN_ITEMREPAIR).access(SECURED_WRITE_SCOPE)
		 * .antMatchers(HttpMethod.GET,
		 * SECURED_PATTERN_REPORTSECURITY).access(SECURED_WRITE_SCOPE)
		 * .antMatchers(HttpMethod.GET,
		 * SECURED_PATTERN_PROFILE).access(SECURED_WRITE_SCOPE)
		 * .antMatchers(HttpMethod.GET,
		 * SECURED_PATTERN_DASHBOARD).access(SECURED_WRITE_SCOPE);
		 * 
		 * 
		 * } }
		 */