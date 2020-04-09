package com.sofCap.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sofCap.Dao.UserDao;
import com.sofCap.dto.UserDto;

@Service
public class UserServiceImpl {

	@Autowired UserDao userDao;

	public UserDto findByLoginId(String login_id) {
		return userDao.findByLoginId(login_id);
	}
}
