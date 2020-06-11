package com.sofCap.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sofCap.dto.AccountDto;
import com.sofCap.model.SemDate;

@Mapper
public interface AccountMapper {
	List<AccountDto> findAll();
	List<AccountDto> findByClubIdAndSem(int club_id, String sem_name);
	List<AccountDto> findBySem(SemDate sem_name);
	List<AccountDto> findBySemName(String sem_name);
	void update(AccountDto account);
	void insert(AccountDto account);
	void delete(int id);
	List<AccountDto> getTotalByClubId(String sem_name);
	int findFileId(int id);
}
