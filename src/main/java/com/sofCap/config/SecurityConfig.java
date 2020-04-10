package com.sofCap.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.sofCap.service.MyAuthenticationProvider;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired MyAuthenticationProvider myAuthenticationProvider;

	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.authorizeRequests()
			.antMatchers("/club_admin/**").hasRole("ClubAdmin")
			.antMatchers("/club_union/**").hasRole("ClubUnion")
			.antMatchers("/member/**").hasRole("Member")
			.antMatchers("/**").permitAll()
			.antMatchers("/tiles/**").permitAll()
			.antMatchers("/").permitAll();
//			.antMatchers("/**").authenticated();

		http.csrf().disable();

		http.formLogin()
			.loginPage("/guest/login")
			.loginProcessingUrl("/login_processing")
			.failureUrl("/login?error")
			.defaultSuccessUrl("/")
			.usernameParameter("login_id")
			.passwordParameter("password");

		http.logout()
		.logoutRequestMatcher(new AntPathRequestMatcher("logout_processing"))
		.logoutSuccessUrl("/guest/login")
		.invalidateHttpSession(true);

		http.authenticationProvider(myAuthenticationProvider);

	}
}
