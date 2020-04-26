package com.sofCap.dao;

import com.sofCap.dto.UserClubDto;

public interface UserClubDao {

	UserClubDto findByUserId(int user_id);
	void insert(UserClubDto userClub);
	void delete(int user_id);
}
