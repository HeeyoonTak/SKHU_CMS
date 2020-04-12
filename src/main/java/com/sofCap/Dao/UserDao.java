package com.sofCap.Dao;

import java.util.List;

import com.sofCap.dto.UserDto;

public interface UserDao {
	UserDto findByLoginId(String login_id);

	List<UserDto> findAll();
}
