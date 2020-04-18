package com.sofCap.dao;

import java.sql.Date;
import java.util.List;

import com.sofCap.dto.AttendanceDto;

public interface AttendanceDao {

	List<String> findDate(int semId);

	List<AttendanceDto> findBySemDate(int semId);

	List<String> findAdmin(int semId);

	List<String> findByDateModal(Date date);

	List<AttendanceDto> findByDate(Date date);

	void delete(Date date);

	void insert(AttendanceDto attendance);

	void update(AttendanceDto attendance);
}