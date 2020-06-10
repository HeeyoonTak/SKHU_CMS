package com.sofCap.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sofCap.dao.UserClubDao;
import com.sofCap.dto.UserClubDto;

@Service
public class UserClubServiceImpl implements UserClubService {

	@Autowired
	UserClubDao userClubDao;

	@Override
	public void insert(int user_id, int club_id) {
		// TODO Auto-generated method stub
		userClubDao.insert(user_id, club_id);
	}

	@Override
	public List<UserClubDto> findByUserId(int user_id) {
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

	@Override
	public int userCount(int user_id) {
		// TODO Auto-generated method stub
		return userClubDao.userCount(user_id);
	}

	@Override
	public boolean clubBelong(int user_id, int club_id) {
		// TODO Auto-generated method stub
		List<UserClubDto> clubs = userClubDao.findByUserId(user_id);
		boolean club_belong = false; // 동아리에 소속되어있는지 확인하는 변수
		for (int i = 0; i < clubs.size(); i++) {
			if (clubs.get(i).getClub_id() == club_id) {
				club_belong = true;
				// 파라미터 club_id와 소속되어있는 동아리목록(clubs)의 club_id가 같은게 있다면 소속확인하는 변수(clus_belong)을
				// true로 바꿈
			}else {
				club_belong = false;
			}
		}
		return club_belong;
	}

}
