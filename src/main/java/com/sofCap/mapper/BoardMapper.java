package com.sofCap.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sofCap.dto.BoardDto;

@Mapper
public interface BoardMapper {
	BoardDto findById(int id);

	List<BoardDto> findByClubId_p(@Param("id") int club_id);

	List<BoardDto> findAll_p();

	List<BoardDto> findAll_r();
}