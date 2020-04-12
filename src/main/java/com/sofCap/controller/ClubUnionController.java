package com.sofCap.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sofCap.dto.AttendanceDto;
import com.sofCap.dto.ClubDto;
import com.sofCap.mapper.AttendanceMapper;
import com.sofCap.mapper.ClubMapper;

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
	ClubMapper clubMapper;
	@Autowired
	AttendanceMapper attendanceMapper;

	@RequestMapping("attendance")
	public String attendance(Model model) {
		ClubDto clubs = clubMapper.findClub();
		model.addAttribute("clubs", clubs);

		List<AttendanceDto> checkdate = attendanceMapper.findByDate();
		model.addAttribute("checkdate", checkdate);

		List<AttendanceDto> findAttendances = attendanceMapper.findAttendance();
		model.addAttribute("findAttendances", findAttendances);

		return "club_union/attendance";
	}
}