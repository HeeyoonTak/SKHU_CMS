package com.sofCap.Dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sofCap.dto.AttendanceDto;
import com.sofCap.mapper.AttendanceMapper;
import com.sofCap.model.SemDate;

@Repository
public class AttendanceDaoImpl implements AttendanceDao {
	@Autowired
	AttendanceMapper attendanceMapper;

	@Override
	public List<AttendanceDto> findByDate() {
		return attendanceMapper.findByDate();
	}

	@Override
	public List<String> findDate() {
		return attendanceMapper.findDate();
	}

	@Override
	public List<AttendanceDto> findBySem(SemDate sem_name) {
		return attendanceMapper.findBySem(sem_name);
	}

}