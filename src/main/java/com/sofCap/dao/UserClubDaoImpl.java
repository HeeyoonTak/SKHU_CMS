package com.sofCap.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sofCap.dto.UserClubDto;
import com.sofCap.mapper.UserClubMapper;

@Repository
public class UserClubDaoImpl implements UserClubDao{

	@Autowired UserClubMapper userClubMapper;

	@Override
	public void insert(int user_id, int club_id) {
		// TODO Auto-generated method stub
		userClubMapper.insert(user_id, club_id);
	}

	@Override
	public List<UserClubDto> findByUserId(int user_id) {
		// TODO Auto-generated method stub
		return userClubMapper.findByUserId(user_id);
	}

	@Override
	public void delete(int user_id) {
		// TODO Auto-generated method stub
		userClubMapper.delete(user_id);
	}

	@Override
	public void deleteMember(int user_id, int club_id) {
		// TODO Auto-generated method stub
		userClubMapper.deleteMember(user_id, club_id);
	}

	@Override
	public int userCount(int user_id) {
		// TODO Auto-generated method stub
		return userClubMapper.userCount(user_id);
	}

}
