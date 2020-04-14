package com.sofCap.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sofCap.dto.AccountDto;
import com.sofCap.model.SemDate;
import com.sofCap.service.AccountService;
import com.sofCap.service.ClubService;
import com.sofCap.service.SemDateService;

@Controller
@RequestMapping("club_union")
public class ClubUnionController {
	@Autowired
	AccountService accountService;
	@Autowired
	ClubService clubService;
	@Autowired
	SemDateService semdateService;
	
	String[] account_type = {"중앙지원금", "동아리회비"};

//	@RequestMapping("account")
//	public List<AccountDto> account() {
//		return accountService.findAll();
//	}

	// 버전 1
//	@RequestMapping("account")
//	public String account(Model model, @RequestParam(name="club_id",defaultValue="1") int club_id) {
//		List<AccountDto> accounts = accountService.findByClubId(club_id);
//		model.addAttribute("accounts", accounts);
//		model.addAttribute("clubs",clubService.findAll());
//		return "club_union/account";
//	}

	// 버전 2
//	@RequestMapping("account")
//	public String account(Model model) {
//		List<AccountDto> accounts = accountService.findAll();
//		model.addAttribute("accounts", accounts);
//		model.addAttribute("clubs",clubService.findAll());
//		return "club_union/account";
//	}

	// 버전 3	
	@RequestMapping(value = "account")
	public String account(Model model, SemDate semdate) {
		System.out.print(semdate.getSem_name());
		List<AccountDto> accounts = accountService.findBySem(semdate);
		model.addAttribute("accounts", accounts);
		model.addAttribute("clubs", clubService.findAll());
		model.addAttribute("sems", semdateService.findAll());
		model.addAttribute("semdate", semdate);
		model.addAttribute("account_type", account_type);
		return "club_union/account";
	}

	// (club_id, price, total, remark, file_id, account_type, date
	@RequestMapping(value="account_save", method=RequestMethod.POST)
	public String account_save(Model model, @RequestParam("club_id") int[] club_id,
			@RequestParam("price") int[] price, @RequestParam("remark") String[] remark,
			@RequestParam("file_id") int[] file_id, @RequestParam("account_type") int[] account_type, 
			@RequestParam("date") @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date[] date, SemDate semdate) {
		save(club_id, price, remark, file_id, account_type, date, semdate);
		return account(model, semdate);
	}

	@Transactional
	private void save(int[] club_id, int[] price, String[] remark, int[] file_id, 
			int[] account_type, Date[] date, SemDate semdate)
	{
		for (int i = 0 ; i < club_id.length ; ++i) {
			AccountDto account = new AccountDto();
			account.setClub_id(club_id[i]);
			account.setPrice(price[i]);
			int total = accountService.getTotal(semdate, club_id[i]) + price[i];
			account.setTotal(total);
			System.out.print(total);
			account.setRemark(remark[i]);
			account.setFile_id(file_id[i]);
			account.setAccount_type(account_type[i]);
			account.setDate(date[i]);
			accountService.insert(account);
		}
	}
}
