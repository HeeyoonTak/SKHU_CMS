package com.sofCap.Dao;

import com.sofCap.dto.UserDto;

public interface UserDao {
	UserDto findByLoginId(String login_id);
}
