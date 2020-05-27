package com.sofCap.dao;

import java.util.List;

import com.sofCap.dto.AccountDto;
import com.sofCap.model.SemDate;

public interface AccountDao {
	List<AccountDto> findAll();
	List<AccountDto> findByClubIdAndSem(int club_id,String sem_name);
	List<AccountDto> findBySem(SemDate sem_name);
	void update(AccountDto account);
	void insert(AccountDto account);
	void delete(int id);
	List<AccountDto> getTotalByClubId(String sem_name);
}
