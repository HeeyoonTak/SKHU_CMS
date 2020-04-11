package com.sofCap.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sofCap.dto.AccountDto;

@Mapper
public interface AccountMapper {
	List<AccountDto> findAll();
	List<AccountDto> findByClubId(int club_id);
}
