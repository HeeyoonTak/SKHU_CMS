package com.sofCap.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sofCap.dao.AttendanceDao;
import com.sofCap.dto.AttendanceDto;
import com.sofCap.dto.SemDateDto;

@Service
public class AttendanceServiceImpl implements AttendanceService {
	@Autowired
	AttendanceDao attendanceDao;

	@Override
	public List<String> findAdmin(int semId) {
		return attendanceDao.findAdmin(semId);
	}

	@Override
	public List<String> findDate(int semId) {
		return attendanceDao.findDate(semId);
	}

	@Override
	public List<AttendanceDto> findBySemDate(int semId) {
		return attendanceDao.findBySemDate(semId);
	}

	@Override
	public List<AttendanceDto> findByDate(Date date) {
		return attendanceDao.findByDate(date);
	}

	@Override
	public SemDateDto findBySemId(Date date) {
		return attendanceDao.findBySemId(date);
	}

	@Override
	public SemDateDto findLastSem() {
		return attendanceDao.findLastSem();
	}

	@Transactional
	@Override
	public void dateNow(Date date, int semId) {
		attendanceDao.dateNow(date, semId);
		return;
	}

	/*
	 * @Transactional
	 *
	 * @Override public void dateNew(Date date) { attendanceDao.dateNew(date);
	 * return; }
	 */

	@Override
	public void allUpdate(String date) {
		attendanceDao.allUpdate(date);
	}

	@Override
	public void update(int id) {
		attendanceDao.update(id);
	}

	@Override
	public void delete(Date date) {
		attendanceDao.delete(date);
	}
}