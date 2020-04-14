package com.sofCap.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.sofCap.service.MyAuthenticationProvider;
import com.sofCap.service.MyAuthenticationResultHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	MyAuthenticationProvider myAuthenticationProvider;
	@Autowired
	MyAuthenticationResultHandler myAuthenticationResultHandler;

	@Override
	public void configure(WebSecurity web) {
		web.ignoring().antMatchers("/res/**");
		// /res/** 패턴의 URL은 보안 검사를 하지 말고 무시하라는 설정
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.authorizeRequests()
			.antMatchers("/club_admin/**").hasRole("ClubAdmin")
//			.antMatchers("/club_union/**").hasRole("ClubUnion")
			.antMatchers("/member/**").hasRole("Member")
			.antMatchers("/**").permitAll()
			.antMatchers("/tiles/**").permitAll()
			.antMatchers("/").permitAll()
			.antMatchers("/member/**").authenticated()
//			.antMatchers("/club_union/**").authenticated()
			.antMatchers("/club_admin/**").authenticated();

		http.csrf().disable();

		http.formLogin()
			.loginPage("/login")
			.loginProcessingUrl("/login_processing")
			.failureUrl("/login?error")
			.usernameParameter("login_id")
			.passwordParameter("password")
			.successHandler(myAuthenticationResultHandler)
			.failureHandler(myAuthenticationResultHandler);
		http.logout()
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout_processing"))
		.logoutSuccessUrl("/")
		.invalidateHttpSession(true);

		http.authenticationProvider(myAuthenticationProvider);

	}
}
