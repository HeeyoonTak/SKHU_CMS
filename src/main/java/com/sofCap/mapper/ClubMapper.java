package com.sofCap.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sofCap.dto.ClubDto;
import com.sofCap.dto.UserDto;

@Mapper
public interface ClubMapper {

	List<String> findClub();

	ClubDto findById(int id);

	ClubDto findByName(String name);

	List<ClubDto> findAll();

	void insert(ClubDto club);

	void delete(String name);
}