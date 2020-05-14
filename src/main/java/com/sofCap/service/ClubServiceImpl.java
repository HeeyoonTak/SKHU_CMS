package com.sofCap.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sofCap.dao.ClubDao;
import com.sofCap.dto.ApplyADto;
import com.sofCap.dto.ApplyQDto;
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
	public List<ClubDto> findAll() {
		return clubDao.findAll();
	}

	@Override
	public List<ClubDto> findByUser(String user_name) {
		return clubDao.findByUser(user_name);
	}

	@Override
	public ClubDto findByName(String name) {
		return clubDao.findByName(name);
	}

	@Override
	public List<ApplyADto> findAnswer(int club_id) {
		// TODO Auto-generated method stub
		return clubDao.findAnswer(club_id);
	}
	@Override
	public List<ApplyQDto> findQuestion(int club_id) {
		// TODO Auto-generated method stub
		return clubDao.findQuestion(club_id);
	}

	@Override
	public void insert(ClubDto club) {
		clubDao.insert(club);
	}

	@Override
	public void delete(String name) {
		clubDao.delete(name);
	}

}
