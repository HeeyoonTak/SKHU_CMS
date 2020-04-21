package com.sofCap.mapper;

import java.sql.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sofCap.dto.AttendanceDto;

@Mapper
public interface AttendanceMapper {

	List<String> findAdmin(int semId);

	List<Integer> findAdminId(int semId);

	List<String> findDate(int semId);

	List<AttendanceDto> findBySemDate(int semId);

	List<AttendanceDto> findByDate(Date date);

	int findBySemId(Date date);

	int findLastSem();

	void dateNow(Date date, int semId);

	void dateNew(Date date);

	void insert(@Param("club_id") int club_id,@Param("check") int check,@Param("date") Date date,@Param("user_id") int user_id);

	void allUpdate(String date);

	void update(int id);

	void delete(Date date);
}