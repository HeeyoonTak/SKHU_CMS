package com.sofCap.dao;

import java.util.List;

import com.sofCap.dto.ClubDto;
import com.sofCap.dto.UserDto;

public interface UserDao {
	UserDto findByLoginId(String login_id);
	List<UserDto> findAll();
	UserDto findOne(int id);
	List<ClubDto> findByMember(String login_id);
	List<ClubDto> findByNotMember(String login_id);
	void updateMypage(UserDto user);
	void updateRole(UserDto user);
}
