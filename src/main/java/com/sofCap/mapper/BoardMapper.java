package com.sofCap.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sofCap.dto.BoardDto;
import com.sofCap.model.Pagination;
import com.sofCap.model.SemDate;

@Mapper
public interface BoardMapper {
	BoardDto findById(int id);

	BoardDto findOneClub(int id);

	List<BoardDto> findBySem_m(SemDate sem_name);

	List<BoardDto> findBySem_a(String sem_name,  int club_id);

	List<BoardDto> findByClubId_p(@Param("id") int club_id);

	List<BoardDto> findByClubId_r(@Param("id") int club_id);

	List<BoardDto> findByClubId_n(@Param("id") int club_id);

	List<BoardDto> findAll_p();

	List<BoardDto> findAll_r();

	List<BoardDto> findAll_n();

	List<BoardDto> findAll_m();

	BoardDto findOne(@Param("id") int id);

	List<BoardDto> listFive_p();

	List<BoardDto> listFive_r();

    List<BoardDto> findAll_r(Pagination pagination);
    int count_p();
    int count_r();

	void delete(int id);

	void insert(BoardDto board);

	void update(BoardDto board);
}