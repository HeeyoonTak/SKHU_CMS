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

	@Override
	public List<UserDto> findAll() {
		return userDao.findAll();
	}

	public UserDto createEntity(UserDto userModel) {
        UserDto user = new UserDto();
//        user.setLogin_id(login_id);(userModel.getLogin_id());
        user.setPassword(userModel.getPassword());
        user.setName(userModel.getName());
        user.setEmail(userModel.getEmail());
        user.setPhone(userModel.getPhone());
        return user;
    }

	@Override
	public List<UserDto> findByMember(int club_id) {
		// TODO Auto-generated method stub
		return userDao.findByMember(club_id);
	}

	@Override
	public List<UserDto> findByNotMember(int club_id) {
		// TODO Auto-generated method stub
		return userDao.findByNotMember(club_id);
	}

	@Override
	public void deleteCandidate(int user_id) {
		// TODO Auto-generated method stub
		userDao.deleteCandidate(user_id);
	}

	@Override
	public List<UserDto> findAllofNotMember() {
		// TODO Auto-generated method stub
		return userDao.findAllofNotMember();
	}

	@Override
	public void updateNotMemberRole(int id) {
		// TODO Auto-generated method stub
		userDao.updateNotMemberRole(id);
	}

	@Override
	public void updateMemberRole(int id) {
		// TODO Auto-generated method stub
		userDao.updateMemberRole(id);
	}

}
