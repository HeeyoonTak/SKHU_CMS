package com.sofCap.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sofCap.dto.AttendanceDto;

@Mapper
public interface AttendanceMapper {

	List<AttendanceDto> findByDate();

	AttendanceDto CountByDate();

	List<AttendanceDto> findAttendance();
}