package com.sofCap.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sofCap.Dao.AttendanceDao;
import com.sofCap.dto.AttendanceDto;

@Service
public class AttendanceServiceImpl implements AttendanceService {
	@Autowired
	AttendanceDao attendanceDao;

	@Override
	public List<AttendanceDto> findByDate() {
		return attendanceDao.findByDate();
	}

	@Override
	public AttendanceDto CountByDate() {
		return attendanceDao.CountByDate();
	}

}