package com.sofCap.service;

import com.sofCap.dto.UserDto;

public interface UserService {

	UserDto findOne(int id);
	UserDto findByLoginId(String login_id);
}
