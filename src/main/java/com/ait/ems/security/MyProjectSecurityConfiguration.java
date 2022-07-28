package com.ait.ems.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MyProjectSecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	BCryptPasswordEncoder encoder;
	
	@Autowired
	UserDetailsService  userDetailsService;
	
	@Autowired
	DataSource dataSource;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests()
		.antMatchers("/index**", "/logoutUser**", "/logout**")
		.permitAll()
		.antMatchers("/add**", "/edit**", "/delete**")
		.hasAuthority("ROLE_MANAGER")
		.anyRequest()
		.authenticated()
		.and()
		.exceptionHandling()
		.accessDeniedPage("/WEB-INF/views/noAccessToYou.jsp")
		.and()
		//.httpBasic();
		.formLogin();
		/*
		.loginPage("xxxxxxxxxx")
		.usernameParameter(null)
		.passwordParameter(null)
		.loginProcessingUrl(null)
		.failureUrl(null);
	    */
		
		http.logout().logoutUrl("/logout").logoutSuccessUrl("/logoutUser");
		http.sessionManagement().maximumSessions(1);
		
		http.requiresChannel().anyRequest().requiresSecure();
		
	}
	
		
	@Autowired
	public void configureGlobal( AuthenticationManagerBuilder builder) throws Exception {
		/*
		  builder .inMemoryAuthentication()
		  .withUser("shekher").password(encoder.encode("12345")).roles("MANAGER")
		  .and() .withUser("kelvin").password(encoder.encode("54321")).roles("LEAD");
		 */
		//builder.userDetailsService(userDetailsService).passwordEncoder(encoder);
		
		builder
		.jdbcAuthentication()
		.dataSource(dataSource)
		.usersByUsernameQuery("select username, password, status from users where username=?")
		.authoritiesByUsernameQuery("select username, authority  from authorities where username=?")
		.passwordEncoder(encoder);
		
		
	}
	
	

}
