package com.sofCap.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {

	@RequestMapping("")
	public String main() {
		return "guest/main";
	}

	@RequestMapping("list-content")
	public String listcontent(){
		return "guest/list-content";
	}

	@RequestMapping("login")
	public String login() {
		return "guest/login";
	}

	@RequestMapping("apply_q_form")
	public String applyQForm() {
		return "club_admin/apply_q_form";
	}

	@RequestMapping("club_admin")
	public String clubAdmin() {
		return "club_union/club_admin";
	}

	@RequestMapping("club_create")
	public String clubCreate() {
		return "club_union/club_create";
	}

	@RequestMapping("club_update")
	public String clubUpdate() {
		return "club_union/club_update";
	}

	@RequestMapping("attendance")
	public String attendance() {
		return "club_union/attendance";
	}

//	@RequestMapping("account")
//	public String account() {
//		return "club_union/account";
//	}
}
