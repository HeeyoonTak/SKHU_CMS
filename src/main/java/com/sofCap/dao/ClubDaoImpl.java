package com.sofCap.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
	public List<ClubDto> findAll(){
		return clubMapper.findAll();
	}
}