package com.sofCap.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sofCap.dto.AttendanceDto;
import com.sofCap.service.AttendanceService;
import com.sofCap.service.UserService;

@Controller
@RequestMapping("clubunion")
public class ClubUnionController {
//	//@Autowired AccountService accountService;
//
//	@RequestMapping("account")
//		public List<AccountDto> account(){
//			//return accountService.findAll();
//		}
	@Autowired
	UserService userService;
	@Autowired
	AttendanceService attendanceService;

	@RequestMapping("attendance")
	public String attendance(Model model) {

		List<AttendanceDto> attendances = attendanceService.findByDate();
		model.addAttribute("attendances", attendances);

		List<String> findDate = attendanceService.findDate();
		model.addAttribute("findDate", findDate);

		model.addAttribute("users", userService.findAll());

		return "club_union/attendance";
	}
}