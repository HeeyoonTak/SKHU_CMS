package com.sofCap.dao;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sofCap.dto.AttendanceDto;
import com.sofCap.dto.SemDateDto;
import com.sofCap.mapper.AttendanceMapper;

@Repository
public class AttendanceDaoImpl implements AttendanceDao {
	@Autowired
	AttendanceMapper attendanceMapper;

	@Override
	public List<String> findUser(int semId, int clubId) {
		return attendanceMapper.findUser(semId, clubId);
	}

	@Override
	public List<String> findDate(int semId, int clubId) {
		return attendanceMapper.findDate(semId, clubId);
	}

	@Override
	public List<AttendanceDto> findBySemDate(int semId, int clubId) {
		return attendanceMapper.findBySemDate(semId, clubId);
	}

	@Override
	public List<AttendanceDto> findByDate(Date date, int clubId) {
		return attendanceMapper.findByDate(date, clubId);
	}

	@Override
	public SemDateDto findBySemId(Date date) {
		return attendanceMapper.findBySemId(date);
	}

	@Override
	public SemDateDto findLastSem() {
		return attendanceMapper.findLastSem();
	}

	@Override
	public void dateNow(Date date, int semId, int clubId) {
		attendanceMapper.dateNow(date, semId, clubId);
	}

	@Override
	public void dateNewUnion(Date date) {
		attendanceMapper.dateNewUnion(date);
	}

	@Override
	public void dateNewAdmin(Date date, int clubId) {
		attendanceMapper.dateNewAdmin(date, clubId);
	}

	@Override
	public void allUpdate(String date, int clubId) {
		attendanceMapper.allUpdate(date, clubId);
	}

	@Override
	public void update(int id) {
		attendanceMapper.update(id);
	}

	@Override
	public void delete(Date date, int clubId) {
		attendanceMapper.delete(date, clubId);
	}

}