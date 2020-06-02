package com.sofCap.service;

import com.sofCap.dto.UserClubDto;

public interface UserClubService {

	UserClubDto findByUserId(int user_id);
	void insert(int user_id, int club_id);
	void delete(int user_id);
	void deleteMember(int user_id, int club_id);
	int userCount(int user_id);
}
