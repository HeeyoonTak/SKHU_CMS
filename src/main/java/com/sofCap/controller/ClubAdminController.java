package com.sofCap.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("club_admin")
public class ClubAdminController {

	@RequestMapping("notice")
	public String club_notice(Model model, Principal principal) {
		return "club_admin/club_notice";
	}

	@GetMapping("acceptance")
	public String acceptane(Model model, Principal principal) {
		return "club_admin/acceptance";
	}

	@PostMapping(value="acceptance",params="cmd=yes") //id값은 자원자 id값
	public String acceptanceYes(Model model, Principal principal, @RequestParam("id") int id) {
		return "redirect:acceptance";
	}

	@PostMapping(value="acceptance",params="cmd=no")
	public String acceptanceNo(Model model, Principal principal, @RequestParam("id") int id) {
		return "redirect:acceptance";
	}
}
