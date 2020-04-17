package com.sofCap.service;

import java.util.List;

import com.sofCap.dto.UserDto;

public interface UserService {

	UserDto findOne(int id);
	UserDto findByLoginId(String login_id);
	void updateMypage(UserDto user);
	List<UserDto> findAll();


}
