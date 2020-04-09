package com.sofCap.service;

import com.sofCap.dto.UserDto;

public interface UserService {

	UserDto findByLoginId(String login_id);
}
