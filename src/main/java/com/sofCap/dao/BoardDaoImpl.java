package com.sofCap.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sofCap.dto.BoardDto;
import com.sofCap.mapper.BoardMapper;
import com.sofCap.model.Pagination;
import com.sofCap.model.SemDate;

@Repository
public class BoardDaoImpl implements BoardDao {
	@Autowired
	BoardMapper boardMapper;

	@Override
	public BoardDto findById(int id) {
		return boardMapper.findById(id);
	}

	@Override
	public BoardDto findOneClub(int id) {
		return boardMapper.findOneClub(id);
	}

	@Override
	public List<BoardDto> findBySem_m(SemDate sem_name,Pagination pagination) {
		return boardMapper.findBySem_m(sem_name,pagination);
	}

//	@Override
//	public List<BoardDto> findBySem_m(SemDate sem_name, Pagination pagination) {
//		return boardMapper.findBySem_m(sem_name, pagination);
//	}

	@Override
	public List<BoardDto> findBySem_a(String sem_name, int club_id) {
		return boardMapper.findBySem_a(sem_name, club_id);
	}

	@Override
	public List<BoardDto> findByClubId_p(@Param("id") int club_id) {
		return boardMapper.findByClubId_p(club_id);
	}

	@Override
	public List<BoardDto> findByClubId_r(@Param("id") int club_id) {
		return boardMapper.findByClubId_r(club_id);
	}

	@Override
	public List<BoardDto> findByClubId_n(int club_id, Pagination pagination) {
		return boardMapper.findByClubId_n(club_id, pagination);
	}

	@Override
	public List<BoardDto> findAll_p() {
		return boardMapper.findAll_p();
	}

	@Override
	public List<BoardDto> findAll_r() {
		return boardMapper.findAll_r();
	}

	@Override
	public List<BoardDto> findAll_n(Pagination pagination) {
		return boardMapper.findAll_n(pagination);
	}

	@Override
	public List<BoardDto> findAll_m() {
		return boardMapper.findAll_m();
	}

	@Override
	public List<BoardDto> listFive_p() {
		return boardMapper.listFive_p();
	}

	@Override
	public List<BoardDto> listFive_r() {
		return boardMapper.listFive_r();
	}

	@Override
	public BoardDto findOne(@Param("id") int id) {
		return boardMapper.findOne(id);
	}

	@Override
	public void delete(int id) {
		boardMapper.delete(id);
	}

	@Override
	public void insert(BoardDto board) {
		boardMapper.insert(board);
	}

	@Override
	public void update(BoardDto board) {
		boardMapper.update(board);
	}
}