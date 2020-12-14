package com.freshvotes.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Bean // allows to get access outside of file
	public PasswordEncoder getPasswordEncoder() {
		// password encryption
		return new BCryptPasswordEncoder();
	}
	
	// Authentication creates the user
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//inMemoryAuthentication creates user in memory. Not for Production
		auth.inMemoryAuthentication()
			.passwordEncoder(getPasswordEncoder())
			.withUser("user@gmail.com")
			.password(getPasswordEncoder().encode("qwerty"))
			.roles("USER");
	}
	
	// Creates the roles
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			// CSRF cross site reforce forgery
			// create a csrf token to protect from hackers
			//	creates a random number as a token
			// .csrf().disable()
			.authorizeRequests() //creates the users 
				.antMatchers("/").permitAll() // tells what to access
				.anyRequest().hasRole("USER")
				.and() // says that any other request needs a USER role
			.formLogin()
				.loginPage("/login")
				.defaultSuccessUrl("/dashboard")
				.permitAll()
				.and()
			.logout()
				.logoutUrl("/logout").permitAll();
	}
}
