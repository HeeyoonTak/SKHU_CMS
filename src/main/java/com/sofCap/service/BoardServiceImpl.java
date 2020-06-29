package com.sofCap.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sofCap.dao.BoardDao;
import com.sofCap.dto.BoardDto;
import com.sofCap.model.Pagination;
import com.sofCap.model.SemDate;

@Service
public class BoardServiceImpl implements BoardService{
	@Autowired
	BoardDao boardDao;

	@Override
	public BoardDto findById(int id) {
		return boardDao.findById(id);
	}

	@Override
	public BoardDto findOneClub(int id) {
		return boardDao.findOneClub(id);
	}

	@Override
	public List<BoardDto> findBySem_m(SemDate sem_name,Pagination pagination) {
		return boardDao.findBySem_m(sem_name,pagination);
	}

//	@Override
//	public List<BoardDto> findBySem_m(SemDate sem_name, Pagination pagination) {
//		return boardDao.findBySem_m(sem_name, pagination);
//	}

	@Override
	public List<BoardDto> findBySem_a(String sem_name, int club_id) {
		return boardDao.findBySem_a(sem_name, club_id);
	}

	@Override
	public List<BoardDto> findByClubId_p(@Param("id") int club_id) {
		return boardDao.findByClubId_p(club_id);
	}

	@Override
	public List<BoardDto> findByClubId_r(@Param("id") int club_id) {
		return boardDao.findByClubId_r(club_id);
	}

	@Override
	public List<BoardDto> findByClubId_n(int club_id, Pagination pagination) {
		return boardDao.findByClubId_n(club_id, pagination);
	}

	@Override
	public List<BoardDto> findAll_p() {
		return boardDao.findAll_p();
	}

	@Override
	public List<BoardDto> findAll_r() {
		return boardDao.findAll_r();
	}

	@Override
	public List<BoardDto> findAll_n(Pagination pagination) {
		return boardDao.findAll_n(pagination);
	}

	@Override
	public List<BoardDto> findAll_m() {
		return boardDao.findAll_m();
	}

	@Override
	public List<BoardDto> listFive_p() {
		return boardDao.listFive_p();
	}

	@Override
	public List<BoardDto> listFive_r() {
		return boardDao.listFive_r();
	}

	@Override
	public BoardDto findOne(@Param("id") int id) {
		return boardDao.findOne(id);
	}

	@Override
	public void delete(int id) {
		boardDao.delete(id);
	}

	@Override
	public void insert(BoardDto board) {
		boardDao.insert(board);
	}

	@Override
	public void update(BoardDto board) {
		boardDao.update(board);
	}
}
