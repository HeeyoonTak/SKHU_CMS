package com.sofCap.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sofCap.dto.ApplyADto;
import com.sofCap.dto.ApplyQDto;
import com.sofCap.dto.ClubDto;

@Mapper
public interface ClubMapper {

	List<String> findClub();

	ClubDto findById(int id);

	ClubDto findByName(String name);

	List<ClubDto> findAll();

	List<ClubDto> findByUserId(int user_id);

	List<ApplyADto> findAnswer(int club_id,int user_id);
	
	List<ApplyADto> findAnswerByClubId(int club_id);

	List<ApplyQDto> findQuestion(int club_id);
	
	void insertQ(ApplyQDto applyQ);

	void insert(ClubDto club);

	void delete(String name);
}