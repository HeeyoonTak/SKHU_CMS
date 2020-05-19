package com.sofCap.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sofCap.dto.ApplyADto;
import com.sofCap.dto.ApplyQDto;
import com.sofCap.dto.ClubDto;
import com.sofCap.mapper.ClubMapper;

@Repository
public class ClubDaoImpl implements ClubDao {
	@Autowired
	ClubMapper clubMapper;

	@Override
	public List<String> findClub() {
		return clubMapper.findClub();
	}

	@Override
	public ClubDto findById(int id) {
		return clubMapper.findById(id);
	}

	@Override
	public ClubDto findByName(String name) {
		return clubMapper.findByName(name);
	}

	@Override
	public List<ClubDto> findAll() {
		return clubMapper.findAll();
	}

	@Override
	public List<ClubDto> findByUserId(int user_id) {
		return clubMapper.findByUserId(user_id);
	}

	@Override
	public List<ApplyADto> findAnswer(int club_id, int user_id) {
		// TODO Auto-generated method stub
		return clubMapper.findAnswer(club_id, user_id);
	}

	@Override
	public List<ApplyQDto> findQuestion(int club_id) {
		// TODO Auto-generated method stub
		return clubMapper.findQuestion(club_id);
	}

	@Override
	public void insert(ClubDto club) {
		clubMapper.insert(club);
	}

	@Override
	public void delete(String name) {
		clubMapper.delete(name);
	}
}