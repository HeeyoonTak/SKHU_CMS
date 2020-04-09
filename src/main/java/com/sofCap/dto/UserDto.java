package com.sofCap.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {

	int id;

	String name;
	String login_id;
	String password;
	String user_type;
	String phone;
	String email;
}
