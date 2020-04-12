package com.sofCap.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.sofCap.dto.ClubDto;

@Mapper
public interface ClubMapper {

   ClubDto findClub();
   ClubDto findById(int id);
   ClubDto findAll();
}