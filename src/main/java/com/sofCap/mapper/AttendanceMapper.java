package com.sofCap.mapper;

import java.sql.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sofCap.dto.AttendanceDto;
import com.sofCap.dto.SemDateDto;

@Mapper
public interface AttendanceMapper {

	List<String> findAdmin(int semId);

	List<Integer> findAdminId(int semId);

	List<String> findDate(int semId);

	List<AttendanceDto> findBySemDate(int semId);

	List<AttendanceDto> findByDate(Date date);

	int findBySemId(Date date);

	SemDateDto findLastSem();

	void dateNow(Date date, int semId);

	void dateNew(Date date);

	void allUpdate(String date);

	void update(int id);

	void delete(Date date);
}