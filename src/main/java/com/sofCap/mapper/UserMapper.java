package com.sofCap.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sofCap.dto.UserDto;

@Mapper
public interface UserMapper {
	UserDto findByLoginId(String login_id);

	UserDto findOne(int id);

	List<UserDto> findAll();

	List<UserDto> findAllofNotMember();

	List<UserDto> findByMember(int club_id);

	List<UserDto> findByNotMember(int club_id);

	void insert(UserDto user);

	void update(UserDto user);

	void updateMypage(UserDto user);

	void updateNotMemberRole(int id);

	void updateMemberRole(int id);

	void delete(int id);

	void deleteCandidate(int user_id);
}