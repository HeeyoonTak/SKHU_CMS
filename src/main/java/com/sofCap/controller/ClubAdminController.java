package com.sofCap.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sofCap.dto.BoardDto;
import com.sofCap.dto.UserClubDto;
import com.sofCap.dto.UserDto;
import com.sofCap.service.BoardService;
import com.sofCap.service.UserClubService;
import com.sofCap.service.UserService;

@Controller
@RequestMapping("club_admin")
public class ClubAdminController {

	@Autowired UserService userService;
	@Autowired UserClubService userClubService;
	@Autowired BoardService boardService;

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

	@RequestMapping("notice")
	public String club_notice(Model model, @RequestParam("club_id") int club_id) {
		List<BoardDto> boards = boardService.findByClubId_n(club_id);
		model.addAttribute("boards", boards);
		return "club_admin/club_notice";
	}

	@RequestMapping("n_content")
	public String n_content(Model model, @RequestParam("id") int id, @RequestParam("club_id") int club_id) {
		BoardDto board = boardService.findOne(id);
		model.addAttribute("board", board);
		return "club_admin/n_content";
	}

	@RequestMapping(value = "n_edit", method = RequestMethod.GET)
	public String n_edit(@RequestParam("id") int id, Model model, BoardDto board) {
		board.setBoard_name_id(3);
		board = boardService.findById(id);
		model.addAttribute("board", board);
		return "club_admin/posting";
	}

	@Transactional
	@RequestMapping(value = "n_edit", method = RequestMethod.POST)
	public String n_edit(BoardDto board, Model model) {
		boardService.update(board);
		return "redirect:n_content?club_id=" + board.getClub_id() + "&id=" + board.getId();
	}

	@RequestMapping(value = "n_create", method = RequestMethod.GET)
	public String n_create(Model model, BoardDto board, @RequestParam("club_id") int club_id) {
		board.setBoard_name_id(3);
		board.setClub_id(club_id);
		System.out.println(board.getClub_id());
		board = new BoardDto();
		model.addAttribute("board", board);
		return "club_admin/posting";
	}

	@Transactional
	@RequestMapping(value = "n_create", method = RequestMethod.POST)
	public String n_create(BoardDto board, Model model, @RequestParam("club_id") int club_id) {
		board.setBoard_name_id(3);
		board.setClub_id(club_id);
		boardService.insert(board);
		return "redirect:n_content?club_id=" + board.getClub_id() + "&id=" + board.getId();
	}
}
