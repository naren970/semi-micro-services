/*
 * package com.gotracrat.attributesandtypes.config;
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
		 * private static final String SECURED_PATTERN = "/api/attributename/**";
		 * private static final String SECURED_PATTERN_ATTACHMENTS =
		 * "/api/attachment/**"; private static final String SECURED_PATTERN_NOTE =
		 * "/api/notes/**"; private static final String SECURED_PATTERN_ADVANCESEARCH =
		 * "/api/advancesearch/**"; private static final String SECURED_PATTERN_STATUS =
		 * "/api/status/**"; private static final String SECURED_PATTERN_TYPE =
		 * "/api/type/**";
		 * 
		 * @Override public void configure(ResourceServerSecurityConfigurer resources) {
		 * resources.resourceId(RESOURCE_ID); }
		 * 
		 * @Override public void configure(HttpSecurity http) throws Exception {
		 * http.requestMatchers().antMatchers(SECURED_PATTERN).antMatchers(
		 * SECURED_PATTERN_ATTACHMENTS)
		 * .antMatchers(SECURED_PATTERN_NOTE).antMatchers(SECURED_PATTERN_STATUS).
		 * antMatchers(SECURED_PATTERN_TYPE) .antMatchers(SECURED_PATTERN_ADVANCESEARCH)
		 * .and().authorizeRequests().antMatchers(HttpMethod.GET,
		 * SECURED_PATTERN).access(SECURED_WRITE_SCOPE) .antMatchers(HttpMethod.GET,
		 * SECURED_PATTERN_ATTACHMENTS).access(SECURED_WRITE_SCOPE)
		 * .antMatchers(HttpMethod.GET,
		 * SECURED_PATTERN_NOTE).access(SECURED_WRITE_SCOPE)
		 * .antMatchers(HttpMethod.GET,
		 * SECURED_PATTERN_STATUS).access(SECURED_WRITE_SCOPE)
		 * .antMatchers(HttpMethod.GET,
		 * SECURED_PATTERN_TYPE).access(SECURED_WRITE_SCOPE)
		 * .antMatchers(HttpMethod.GET,
		 * SECURED_PATTERN_ADVANCESEARCH).access(SECURED_WRITE_SCOPE);
		 * 
		 * } }
		 */