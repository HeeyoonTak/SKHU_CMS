package com.sofCap.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.sofCap.dto.UserDto;

/*MyAuthenticationProvier 클래스는
사용자가 입력한 로그인 아이디와 비밀번호를 검사할 때 사용되는 클래스이다.*/
@Component
public class MyAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	UserServiceImpl userService;

	/* 사용자가 입력한 로그인 아이디와 비밀번호가 authenticate 메소드의 파라미터로 전달된다. */
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String loginId = authentication.getName();
		String passwd = authentication.getCredentials().toString();
		return authenticate(loginId, passwd);
	}

	public Authentication authenticate(String login_id, String password) throws AuthenticationException {
		UserDto userDto = userService.login(login_id, password);
		if (userDto == null) {
			return null;
		}
		List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
		String role = "";
		switch (userDto.getUser_type()) {
		case "동연":
			role = "ROLE_ClubUnion";
			break;
		case "동아리관리자":
			role = "ROLE_ClubAdmin";
			break;
		case "일반회원":
			role = "ROLE_Member";
			break;
		case "비회원":
			role = "ROLE_guest";
			break;
		}

		System.out.println(login_id);
		System.out.println(password);
		grantedAuthorities.add(new SimpleGrantedAuthority(role));
		return new MyAuthenticaion(login_id, password, grantedAuthorities, userDto);
	}
	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

	public class MyAuthenticaion extends UsernamePasswordAuthenticationToken {
		private static final long serialVersionUID = 1L;
		UserDto userDto;

		public MyAuthenticaion(String loginId, String password, List<GrantedAuthority> grantedAuthorities, UserDto userDto) {
			super(loginId, password, grantedAuthorities);
			this.userDto = userDto;
		}

		public UserDto getUser() {
			return userDto;
		}

		public void setUser(UserDto userDto) {
			this.userDto = userDto;
		}

	}
}
