package com.sofCap.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sofCap.dao.UserDao;
import com.sofCap.dto.UserDto;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;

	public UserDto login(String login_id, String password) {
		UserDto userDto = userDao.findByLoginId(login_id);
		if (userDto == null) {
			return null;
		}
		String pw = password;
		if (!userDto.getPassword().equals(pw)) {
			return null;
		}
		return userDto;
	}

	@Override
	public UserDto findByLoginId(String login_id) {
		// TODO Auto-generated method stub
		return userDao.findByLoginId(login_id);
	}

	@Override
	public UserDto findOne(int id) {
		return userDao.findOne(id);
	}

	@Override
	public void updateMypage(UserDto user) {
		// TODO Auto-generated method stub
		userDao.updateMypage(user);
	}

}
	public List<UserDto> findAll() {
		return userDao.findAll();
	}

}
