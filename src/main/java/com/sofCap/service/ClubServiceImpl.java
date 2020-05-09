package com.sofCap.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sofCap.dao.ClubDao;
import com.sofCap.dto.ApplyADto;
import com.sofCap.dto.ClubDto;

@Service
public class ClubServiceImpl implements ClubService {
	@Autowired
	ClubDao clubDao;

	@Override
	public List<String> findClub() {
		return clubDao.findClub();
	}

	@Override
	public ClubDto findById(int id) {
		return clubDao.findById(id);
	}

	@Override
	public List<ClubDto> findAll(){
		return clubDao.findAll();
	}

	@Override
	public List<ApplyADto> findAnswer(int club_id) {
		// TODO Auto-generated method stub
		return clubDao.findAnswer(club_id);
	}

}
