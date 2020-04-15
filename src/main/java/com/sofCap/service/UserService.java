package com.sofCap.service;

import java.util.List;

import com.sofCap.dto.UserDto;

public interface UserService {

	UserDto findByLoginId(String login_id);

	List<UserDto> findAll();

}
