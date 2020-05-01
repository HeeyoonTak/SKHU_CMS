package com.sofCap.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sofCap.dto.BoardDto;
import com.sofCap.dto.ClubDto;
import com.sofCap.service.BoardService;
import com.sofCap.service.ClubService;

@Controller
@RequestMapping("/")
public class BoardController {
	@Autowired
	ClubService clubService;
	@Autowired
	BoardService boardService;

	@RequestMapping("list-content")
	public String list_content(Model model, @RequestParam("id") int club_id) {
		ClubDto club = clubService.findById(club_id);
		List<BoardDto> boards = boardService.findByClubId_p(club_id);
		model.addAttribute("club", club);
		model.addAttribute("boards",boards);
		return "guest/list-content";
	}

	@RequestMapping("p_content")
	public String p_content(Model model, @RequestParam("id") int id) {
		BoardDto board = boardService.findOne(id);
		model.addAttribute("board",board);
		return "guest/p_content";
	}

	@RequestMapping("r_content")
	public String r_content(Model model, @RequestParam("id") int id) {
		BoardDto board = boardService.findOne(id);
		model.addAttribute("board",board);
		return "guest/r_content";
	}

	@RequestMapping("n_content")
	public String n_content(Model model, @RequestParam("id") int id) {
		BoardDto board = boardService.findOne(id);
		model.addAttribute("board",board);
		return "club_union/n_content";
	}

	@RequestMapping("m_content")
	public String m_content(Model model, @RequestParam("id") int id) {
		BoardDto board = boardService.findOne(id);
		model.addAttribute("board",board);
		return "club_union/m_content";
	}

	@RequestMapping("publicity")
	public String publicity(Model model) {
		List<BoardDto> boards = boardService.findAll_p();
		model.addAttribute("boards",boards);
		return "guest/publicity";
	}

	@RequestMapping("recruit")
	public String recruit(Model model) {
		List<BoardDto> boards = boardService.findAll_r();
		model.addAttribute("boards",boards);
		return "guest/recruit";
	}

	@RequestMapping("notice")
	public String union_notice(Model model) {
		List<BoardDto> boards = boardService.findAll_n();
		model.addAttribute("boards",boards);
		return "club_union/union_notice";
	}

	@RequestMapping("minutes")
	public String union_minutes(Model model) {
		List<BoardDto> boards = boardService.findAll_m();
		model.addAttribute("boards",boards);
		return "club_union/union_minutes";
	}
}
