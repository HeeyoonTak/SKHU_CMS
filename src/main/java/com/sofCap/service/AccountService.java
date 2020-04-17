package com.sofCap.service;

import java.sql.Date;
import java.util.List;

import com.sofCap.dto.AccountDto;
import com.sofCap.model.SemDate;

public interface AccountService {
	List<AccountDto> findAll();
	List<AccountDto> findByClubId(int club_id);
	List<AccountDto> findBySem(SemDate sem_name);
	void update(AccountDto account);
	void insert(AccountDto account);
	void delete(int id);
	Integer getTotal(String sem_name, int club_id, Date date);
}
