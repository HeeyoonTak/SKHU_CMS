package com.sofCap.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sofCap.dto.UserDto;
import com.sofCap.service.UserService;

@Controller
@RequestMapping("/")
public class MainController {

	@Autowired UserService userService;

	@RequestMapping("")
	public String main() {
		return "guest/main";
	}

//	@RequestMapping("list-content")
//	public String listcontent(){
//		return "guest/list-content";
//	}

	@RequestMapping("login")
	public String login(Model model, Principal principal) {
		UserDto user = userService.findByLoginId(principal.getName());
		model.addAttribute("user", user);
		return "guest/login";
	}

	@RequestMapping("myPage")
	public String myPage() {

		return "guest/myPage";
	}

	@RequestMapping("apply_q_form")
	public String applyQForm() {
		return "club_admin/apply_q_form";
	}

	@RequestMapping("attendance")
	public String attendance() {
		return "club_union/attendance";
	}

	@RequestMapping("account")
	public String account() {
		return "club_union/account";
	}

//	@RequestMapping("account")
//	public String account() {
//		return "club_union/account";
//	}
}
