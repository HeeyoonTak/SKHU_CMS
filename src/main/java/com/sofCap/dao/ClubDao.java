package com.sofCap.dao;

import java.util.List;

import com.sofCap.dto.ApplyADto;
import com.sofCap.dto.ClubDto;

public interface ClubDao {
	List<String> findClub();
	ClubDto findById(int id);
	List<ClubDto> findAll();
	List<ApplyADto> findAnswer(int club_id);
}

