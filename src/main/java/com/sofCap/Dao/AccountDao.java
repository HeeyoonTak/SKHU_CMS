package com.sofCap.Dao;

import java.util.List;

import com.sofCap.dto.AccountDto;

public interface AccountDao {
	List<AccountDto> findAll();
}
