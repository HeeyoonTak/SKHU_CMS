package com.sofCap.service;

import java.util.List;

import com.sofCap.dto.ClubDto;

public interface ClubService {
	List<String> findClub();

	ClubDto findById(int id);

	List<ClubDto> findAll();
}