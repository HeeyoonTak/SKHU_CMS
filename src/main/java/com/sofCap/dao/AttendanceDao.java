package com.sofCap.dao;

import java.sql.Date;
import java.util.List;

import com.sofCap.dto.AttendanceDto;

public interface AttendanceDao {

	List<String> findDate(int semId);

	List<AttendanceDto> findBySemDate(int semId);

	List<String> findAdmin(int semId);

	void delete(Date date);
}