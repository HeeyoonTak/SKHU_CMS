package com.sofCap.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sofCap.dto.AccountDto;
import com.sofCap.service.AccountService;

@RestController
@RequestMapping("clubunion")
public class ClubUnionController {
	@Autowired AccountService accountService;
	
	@RequestMapping("account")
		public List<AccountDto> account(){
			return accountService.findAll();
		}
	}
	

