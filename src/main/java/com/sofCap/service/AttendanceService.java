package com.sofCap.service;

import java.sql.Date;
import java.util.List;

import com.sofCap.dto.AttendanceDto;

public interface AttendanceService {

	List<String> findDate(int semId);

	List<AttendanceDto> findBySemDate(int semId);

	List<String> findAdmin(int semId);

	void delete(Date date);

	void insert(AttendanceDto attendance);

	void update(AttendanceDto attendance);
}