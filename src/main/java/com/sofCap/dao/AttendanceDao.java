package com.sofCap.dao;

import java.sql.Date;
import java.util.List;

import com.sofCap.dto.AttendanceDto;
import com.sofCap.dto.SemDateDto;

public interface AttendanceDao {

	List<String> findAdmin(int semId);

	List<String> findDate(int semId);

	List<AttendanceDto> findBySemDate(int semId);

	List<AttendanceDto> findByDate(Date date);

	SemDateDto findBySemId(Date date);

	SemDateDto findLastSem();

	void dateNow(Date date, int semId);

	/* void dateNew(Date date); */

	void allUpdate(String date);

	void update(int id);

	void delete(Date date);
}