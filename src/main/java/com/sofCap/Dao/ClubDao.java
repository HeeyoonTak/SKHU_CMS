package com.sofCap.Dao;

import java.util.List;

import com.sofCap.dto.ClubDto;

public interface ClubDao {
	List<String> findClub();

	ClubDto findById(int id);

	List<ClubDto> findAll();
}