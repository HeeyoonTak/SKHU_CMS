package com.sofCap.service;

import java.util.List;

import com.sofCap.dto.AccountDto;

public interface AccountService {
	List<AccountDto> findAll();
	List<AccountDto> findByClubId(int club_id);


}
