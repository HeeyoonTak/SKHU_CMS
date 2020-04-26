package com.sofCap.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.sofCap.dto.UserClubDto;

@Mapper
public interface UserClubMapper {

	UserClubDto findByUserId(int user_id);
	void insert(UserClubDto userClub);
	void delete(int user_id);
}
