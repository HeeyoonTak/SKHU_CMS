package com.sofCap.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sofCap.dto.AccountDto;
import com.sofCap.service.AccountService;
import com.sofCap.service.ClubService;

@Controller
@RequestMapping("clubunion")
public class ClubUnionController {
	@Autowired
	AccountService accountService;
	@Autowired
	ClubService clubService;

//	@RequestMapping("account")
//	public List<AccountDto> account() {
//		return accountService.findAll();
//	}

//	@RequestMapping("account")
//	public String account(Model model, @RequestParam(name="club_id",defaultValue="1") int club_id) {
//		List<AccountDto> accounts = accountService.findByClubId(club_id);
//		model.addAttribute("accounts", accounts);
//		model.addAttribute("clubs",clubService.findAll());
//		return "club_union/account";
//	}
	
	@RequestMapping("account")
	public String account(Model model) {
		List<AccountDto> accounts = accountService.findAll();
		model.addAttribute("accounts", accounts);
		model.addAttribute("clubs",clubService.findAll());
		return "club_union/account";
	}
}
