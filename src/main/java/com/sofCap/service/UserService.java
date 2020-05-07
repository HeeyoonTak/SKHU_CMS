package com.sofCap.service;

import java.util.List;

import com.sofCap.dto.ClubDto;
import com.sofCap.dto.UserDto;

public interface UserService {

	UserDto findOne(int id);
	UserDto findByLoginId(String login_id);
	List<UserDto> findByMember(int club_id);
	List<ClubDto> findByNotMember(String login_id);
	void updateMypage(UserDto user);
	void updateRole(UserDto user);
	List<UserDto> findAll();
}
