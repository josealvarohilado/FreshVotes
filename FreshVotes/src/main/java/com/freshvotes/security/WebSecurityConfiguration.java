package com.freshvotes.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	// Authentication creates the user
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//inMemoryAuthentication creates user in memory. Not for Production
		auth.inMemoryAuthentication()
			.withUser("user@gmail.com")
			.password("qwerty")
			.roles("USER");
	}
	
	// Creates the roles
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
			.authorizeRequests() //creates the users 
				.antMatchers("/").permitAll() // tells what to access
				.anyRequest().hasRole("USER")
				.and() // says that any other request needs a USER role
			.formLogin()
				.loginPage("/login").permitAll()
				.and()
			.logout()
				.logoutUrl("/logout").permitAll();
	}
}
