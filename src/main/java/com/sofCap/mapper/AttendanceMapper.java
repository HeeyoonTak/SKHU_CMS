package com.sofCap.mapper;

import java.sql.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sofCap.dto.AttendanceDto;
import com.sofCap.dto.SemDateDto;

@Mapper
public interface AttendanceMapper {

	List<String> findUser(int semId, int clubId);

	List<String> findDate(int semId, int clubId);

	List<AttendanceDto> findBySemDate(int semId, int clubId);

	List<AttendanceDto> findByDate(Date date, int clubId);

	SemDateDto findBySemId(Date date);

	SemDateDto findLastSem();

	void dateNow(Date date, int semId, int clubId);

	void dateNewUnion(Date date);

	void dateNewAdmin(Date date, int clubId);

	void allUpdate(String date, int clubId);

	void update(int id);

	void delete(Date date, int clubId);
}