package com.sofCap.service;

import java.util.List;

import com.sofCap.dto.UserDto;

public interface UserService {

	UserDto findOne(int id);
	UserDto findByLoginId(String login_id);
	List<UserDto> findByMember(int club_id);
	List<UserDto> findByNotMember(int club_id);
	void updateMypage(UserDto user);
	void updateRole(int id);
	void deleteCandidate(int user_id);
	List<UserDto> findAll();
	List<UserDto> findAllofNotMember();
}
