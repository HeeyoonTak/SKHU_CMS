package com.sofCap.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sofCap.dto.BoardDto;
import com.sofCap.dto.ClubDto;
import com.sofCap.dto.UserDto;
import com.sofCap.mapper.ClubMapper;
import com.sofCap.service.BoardService;
import com.sofCap.service.ClubService;
import com.sofCap.service.UserService;

@Controller
@RequestMapping("/")
public class BoardController {
	@Autowired
	ClubService clubService;
	@Autowired
	BoardService boardService;
	@Autowired
	UserService userService;
	@Autowired
	ClubMapper clubMapper;

	public void nav_list(Model model) {
		List<ClubDto> clubs = clubService.findAll();
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

	@RequestMapping("list-content")
	public String list_content(Model model, @RequestParam("id") int club_id, Principal principal) {
		ClubDto club = clubService.findById(club_id);
		List<BoardDto> boards = boardService.findByClubId_p(club_id);
		model.addAttribute("club", club);
		model.addAttribute("boards",boards);
		nav_list(model);
		nav_user(model, principal);
		return "guest/list-content";
	}

	@RequestMapping("p_content")
	public String p_content(Model model, @RequestParam("id") int id, Principal principal) {
		BoardDto board = boardService.findOne(id);
		model.addAttribute("board",board);
		nav_list(model);
		nav_user(model, principal);
		return "guest/p_content";
	}

	@RequestMapping("r_content")
	public String r_content(Model model, @RequestParam("id") int id,Principal principal) {
		BoardDto board = boardService.findOne(id);
		model.addAttribute("board",board);
		nav_list(model);
		nav_user(model, principal);
		return "guest/r_content";
	}

	@RequestMapping("n_content")
	public String n_content(Model model, @RequestParam("id") int id,Principal principal) {
		BoardDto board = boardService.findOne(id);
		model.addAttribute("board",board);
		nav_list(model);
		nav_user(model, principal);
		return "club_union/n_content";
	}

	@RequestMapping("m_content")
	public String m_content(Model model, @RequestParam("id") int id, Principal principal) {
		BoardDto board = boardService.findOne(id);
		model.addAttribute("board",board);
		nav_list(model);
		nav_user(model, principal);
		return "club_union/m_content";
	}

	@RequestMapping("publicity")
	public String publicity(Model model, Principal principal) {
		List<BoardDto> boards = boardService.findAll_p();
		model.addAttribute("boards",boards);
		nav_list(model);
		nav_user(model, principal);
		return "guest/publicity";
	}

	@RequestMapping("recruit")
	public String recruit(Model model, Principal principal) {
		List<BoardDto> boards = boardService.findAll_r();
		model.addAttribute("boards",boards);
		nav_list(model);
		nav_user(model, principal);
		return "guest/recruit";
	}

	@RequestMapping("notice")
	public String union_notice(Model model, Principal principal) {
		List<BoardDto> boards = boardService.findAll_n();
		model.addAttribute("boards",boards);
		nav_list(model);
		nav_user(model, principal);
		return "club_union/union_notice";
	}

	@RequestMapping("minutes")
	public String union_minutes(Model model, Principal principal) {
		List<BoardDto> boards = boardService.findAll_m();
		model.addAttribute("boards",boards);
		nav_list(model);
		nav_user(model, principal);
		return "club_union/union_minutes";
	}
}
