package com.sofCap.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sofCap.dto.UserClubDto;
import com.sofCap.dto.UserDto;
import com.sofCap.service.UserClubService;
import com.sofCap.service.UserService;

@Controller
@RequestMapping("club_admin")
public class ClubAdminController {

	@Autowired UserService userService;
	@Autowired UserClubService userClubService;

	@RequestMapping("notice")
	public String club_notice(Model model, Principal principal) {
		return "club_admin/club_notice";
	}

	/*
	 * 지원자 합불 구현하기
	*/
	@GetMapping("acceptance")
	public String acceptane(Model model, Principal principal) {
		UserDto user = userService.findByLoginId(principal.getName());
		model.addAttribute("user",user);
		return "club_admin/acceptance";
	}

	@PostMapping(value="acceptance",params="cmd=yes") //id값은 지원자 id값
	public String acceptanceYes(Model model, Principal principal,
			@RequestParam("user_id") int user_id, @RequestParam("club_id") int club_id) {
		UserDto user = userService.findByLoginId(principal.getName());
		UserClubDto userClub = userClubService.findByUserId(user_id);
		model.addAttribute("user", user);
		model.addAttribute("userClub", userClub);
		userService.updateRole(user);
		userClubService.insert(userClub);
		return "redirect:acceptance";
	}

	@PostMapping(value="acceptance",params="cmd=no")
	public String acceptanceNo(Model model, Principal principal,
			@RequestParam("user_id") int user_id, @RequestParam("club_id") int club_id) {
		UserDto user = userService.findByLoginId(principal.getName());
		UserClubDto userClub = userClubService.findByUserId(user_id);
		model.addAttribute("user", user);
		model.addAttribute("userClub", userClub);
		userService.updateRole(user);
		userClubService.delete(user_id);
		return "redirect:acceptance";
	}
}
