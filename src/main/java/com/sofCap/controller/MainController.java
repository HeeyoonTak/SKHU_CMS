package com.sofCap.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sofCap.dto.BoardDto;
import com.sofCap.dto.ClubDto;
import com.sofCap.dto.UserDto;
import com.sofCap.mapper.BoardMapper;
import com.sofCap.mapper.ClubMapper;
import com.sofCap.service.UserClubService;
import com.sofCap.service.UserService;

@Controller
@RequestMapping("/")
public class MainController {

	@Autowired
	UserService userService;
	@Autowired
	UserClubService userClubService;
	@Autowired
	BoardMapper boardMapper;
	@Autowired
	ClubMapper clubMapper;

	public void nav_list(Model model) {
		List<ClubDto> clubs = clubMapper.findAll();
		model.addAttribute("clubs", clubs);
	}

	public void nav_user(Model model, Principal principal) {
		if (principal == null)
			return;
		else {
			UserDto user = userService.findByLoginId(principal.getName());
			List<ClubDto> user_clubs = clubMapper.findByUser(user.getName());
			model.addAttribute("user_clubs", user_clubs);
		}
	}

	@RequestMapping("")
	public String main(Model model, Principal principal) {
		List<BoardDto> boards_p = boardMapper.listFive_p();
		List<BoardDto> boards_r = boardMapper.listFive_r();
		nav_list(model);
		nav_user(model, principal);
		model.addAttribute("boards_p", boards_p);
		model.addAttribute("boards_r", boards_r);
		return "guest/main";
	}

	@RequestMapping("login")
	public String login() {
		return "guest/login";
	}

	@RequestMapping("myPage")
	public String myPage(Model model, Principal principal) {
		UserDto user = userService.findByLoginId(principal.getName());
		model.addAttribute("user", user);
		nav_list(model);
		return "guest/myPage";
	}

	@PostMapping("myPage")
	public String myPage(@Valid UserDto user1, BindingResult bindingResult, Model model, Principal principal,
			HttpServletResponse response) throws IOException {
		UserDto user = userService.findByLoginId(principal.getName());
		user.setEmail(user1.getEmail());
		user.setPhone(user1.getPhone());
		user.setPassword(user1.getPassword());
		userService.updateMypage(user);
		if (bindingResult.hasErrors()) {
			model.addAttribute("user", userService.findAll());
			return "guest/register";
		}

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<script>alert('마이페이지 수정 완료.'); history.go(-1);</script>");
		out.flush();
		return "redirect:myPage";
	}

	@RequestMapping("apply_q_form")
	public String applyQForm() {
		return "club_admin/apply_q_form";
	}

	@RequestMapping("attendance")
	public String attendance() {
		return "club_union/attendance";
	}

}
