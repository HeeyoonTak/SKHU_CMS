package com.sofCap.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sofCap.dto.BoardDto;

@Mapper
public interface BoardMapper {
	BoardDto findById(int id);

	List<BoardDto> findByClubId(int club_no);
	List<BoardDto> list(@Param("id") int club_id);
}
