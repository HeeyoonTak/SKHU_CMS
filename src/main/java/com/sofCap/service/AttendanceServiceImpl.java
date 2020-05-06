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
	public List<String> findUser(int semId, int clubId) {
		return attendanceDao.findUser(semId, clubId);
	}

	@Override
	public List<String> findDate(int semId, int clubId) {
		return attendanceDao.findDate(semId, clubId);
	}

	@Override
	public List<AttendanceDto> findBySemDate(int semId, int clubId) {
		return attendanceDao.findBySemDate(semId, clubId);
	}

	@Override
	public List<AttendanceDto> findByDate(Date date, int clubId) {
		return attendanceDao.findByDate(date, clubId);
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
	public void dateNow(Date date, int semId, int clubId) {
		attendanceDao.dateNow(date, semId, clubId);
		return;
	}

	/*
	 * @Transactional
	 *
	 * @Override public void dateNew(Date date) { attendanceDao.dateNew(date);
	 * return; }
	 */

	@Override
	public void allUpdate(String date, int clubId) {
		attendanceDao.allUpdate(date, clubId);
	}

	@Override
	public void update(int id) {
		attendanceDao.update(id);
	}

	@Override
	public void delete(Date date, int clubId) {
		attendanceDao.delete(date, clubId);
	}
}