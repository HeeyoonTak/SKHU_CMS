package com.sofCap.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.Principal;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
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
	public String login() {
		return "guest/login";
	}

	@RequestMapping("myPage")
	public String myPage(Model model, Principal principal) {
		UserDto user = userService.findByLoginId(principal.getName());
		model.addAttribute("user", user);

		return "guest/myPage";
	}

	@PostMapping("myPage")
	public String myPage(UserDto user1, Model model, Principal principal, HttpServletResponse response) throws IOException{
		UserDto user = userService.findByLoginId(principal.getName());
		user.setEmail(user1.getEmail());
		user.setPhone(user1.getPhone());
		user.setPassword(user1.getPassword());
		userService.updateMypage(user);

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<script>alert('마이페이지 수정 완료.'); history.go(-1);</script>");
		out.flush();
		return "redirect:guest/myPage";
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
