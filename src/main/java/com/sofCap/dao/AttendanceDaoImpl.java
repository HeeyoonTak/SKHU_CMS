package com.sofCap.dao;

import java.sql.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
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
	public List<String> findAdmin(int semId) {
		return attendanceMapper.findAdmin(semId);
	}

	@Override
	public List<Integer> findAdminId(int semId) {
		return attendanceMapper.findAdminId(semId);
	}

	@Override
	public List<String> findDate(int semId) {
		return attendanceMapper.findDate(semId);
	}

	@Override
	public List<AttendanceDto> findBySemDate(int semId) {
		return attendanceMapper.findBySemDate(semId);
	}

	@Override
	public List<AttendanceDto> findByDate(Date date) {
		return attendanceMapper.findByDate(date);
	}

	@Override
	public int findBySemId(Date date) {
		return attendanceMapper.findBySemId(date);
	}

	@Override
	public SemDateDto findLastSem() {
		return attendanceMapper.findLastSem();
	}

	@Override
	public void dateNow(Date date, int semId) {
		attendanceMapper.dateNow(date, semId);
	}

	@Override
	public void dateNew(Date date) {
		attendanceMapper.dateNew(date);
	}

	@Override
	public void insert(@Param("club_id") int club_id,@Param("check") int check,@Param("date") Date date,@Param("user_id") int user_id) {
		attendanceMapper.insert(club_id, check, date, user_id);
	}

	@Override
	public void allUpdate(String date) {
		attendanceMapper.allUpdate(date);
	}

	@Override
	public void update(int id) {
		attendanceMapper.update(id);
	}

	@Override
	public void delete(Date date) {
		attendanceMapper.delete(date);
	}

}