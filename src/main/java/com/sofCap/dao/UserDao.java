package com.sofCap.dao;

import com.sofCap.dto.UserDto;

public interface UserDao {
	UserDto findByLoginId(String login_id);
}
