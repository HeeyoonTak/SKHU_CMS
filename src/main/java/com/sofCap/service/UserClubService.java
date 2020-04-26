package com.sofCap.service;

import com.sofCap.dto.UserClubDto;

public interface UserClubService {

	UserClubDto findByUserId(int user_id);
	void insert(UserClubDto userClub);
	void delete(int user_id);
}
