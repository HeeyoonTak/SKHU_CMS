package com.sofCap.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sofCap.dao.UserClubDao;
import com.sofCap.dto.UserClubDto;

@Service
public class UserClubServiceImpl implements UserClubService{

	@Autowired UserClubDao userClubDao;

	@Override
	public void insert(int user_id, int club_id) {
		// TODO Auto-generated method stub
		userClubDao.insert(user_id, club_id);
	}

	@Override
	public UserClubDto findByUserId(int user_id) {
		// TODO Auto-generated method stub
		return userClubDao.findByUserId(user_id);
	}

	@Override
	public void delete(int user_id) {
		// TODO Auto-generated method stub
		userClubDao.delete(user_id);
	}

	@Override
	public void deleteMember(int user_id, int club_id) {
		// TODO Auto-generated method stub
		userClubDao.deleteMember(user_id, club_id);
	}

}
