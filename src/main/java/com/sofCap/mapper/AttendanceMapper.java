package com.sofCap.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.sofCap.dto.AttendanceDto;

@Mapper
public interface AttendanceMapper {

	AttendanceDto findByDate();

	AttendanceDto CountByDate();
}