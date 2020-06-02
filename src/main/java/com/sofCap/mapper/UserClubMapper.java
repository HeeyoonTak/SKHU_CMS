package com.sofCap.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sofCap.dto.UserClubDto;

@Mapper
public interface UserClubMapper {

	List<UserClubDto> findByUserId(int user_id);
	void insert(int user_id, int club_id);
	void delete(int user_id);
	void deleteMember(int user_id, int club_id);
	int userCount(int user_id);
}
