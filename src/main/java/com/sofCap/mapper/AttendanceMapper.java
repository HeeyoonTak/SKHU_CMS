package com.sofCap.mapper;

import java.sql.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sofCap.dto.AttendanceDto;

@Mapper
public interface AttendanceMapper {

	List<String> findDate(int semId);

	List<AttendanceDto> findBySemDate(int semId);

	List<String> findAdmin(int semId);

	List<String> findByDateModal(Date date);

	List<AttendanceDto> findByDate(Date date);

	void delete(Date date);

	void insert(AttendanceDto attendance);

	void update(AttendanceDto attendance);
}