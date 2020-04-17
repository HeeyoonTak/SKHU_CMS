package com.sofCap.dao;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sofCap.dto.AttendanceDto;
import com.sofCap.mapper.AttendanceMapper;

@Repository
public class AttendanceDaoImpl implements AttendanceDao {
	@Autowired
	AttendanceMapper attendanceMapper;

	@Override
	public List<String> findDate(int semId) {
		return attendanceMapper.findDate(semId);
	}

	@Override
	public List<AttendanceDto> findBySemDate(int semId) {
		return attendanceMapper.findBySemDate(semId);
	}

	@Override
	public List<String> findAdmin(int semId) {
		return attendanceMapper.findAdmin(semId);
	}

	@Override
	public void delete(Date date) {
		attendanceMapper.delete(date);
	}

	@Override
	public void insert(AttendanceDto attendance) {
		attendanceMapper.insert(attendance);
	}

	@Override
	public void update(AttendanceDto attendance) {
		attendanceMapper.update(attendance);
	}

}