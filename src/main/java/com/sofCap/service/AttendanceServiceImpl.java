package com.sofCap.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sofCap.dao.AttendanceDao;
import com.sofCap.dto.AttendanceDto;

@Service
public class AttendanceServiceImpl implements AttendanceService {
	@Autowired
	AttendanceDao attendanceDao;

	@Override
	public List<String> findDate(int semId) {
		return attendanceDao.findDate(semId);
	}

	@Override
	public List<AttendanceDto> findBySemDate(int semId) {
		return attendanceDao.findBySemDate(semId);
	}

	@Override
	public List<String> findAdmin(int semId) {
		return attendanceDao.findAdmin(semId);
	}

}