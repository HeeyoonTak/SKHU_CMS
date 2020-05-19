package com.sofCap.service;

import java.util.List;

import com.sofCap.dto.ApplyADto;
import com.sofCap.dto.ApplyQDto;
import com.sofCap.dto.ClubDto;

public interface ClubService {

	List<String> findClub();

	ClubDto findById(int id);

	ClubDto findByName(String name);

	List<ClubDto> findAll();

	List<ClubDto> findByUserId(int user_id);

	List<ApplyADto> findAnswer(int club_id);

	List<ApplyQDto> findQuestion(int club_id);

	void insert(ClubDto club);

	void delete(String name);
}