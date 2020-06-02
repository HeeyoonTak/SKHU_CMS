package com.sofCap.dao;

import java.util.List;

import com.sofCap.dto.UserClubDto;

public interface UserClubDao {

	List<UserClubDto> findByUserId(int user_id);
	void insert(int user_id, int club_id);
	void delete(int user_id);
	void deleteMember(int user_id, int club_id);
}
