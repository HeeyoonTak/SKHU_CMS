package com.sofCap.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sofCap.dto.BoardDto;
import com.sofCap.dto.ClubDto;
import com.sofCap.mapper.BoardMapper;
import com.sofCap.mapper.ClubMapper;

@Controller
@RequestMapping("/")
public class BoardController {
	@Autowired
	ClubMapper clubMapper;
	@Autowired
	BoardMapper boardMapper;

	@RequestMapping("list-content")
	public String listContent(Model model, @RequestParam("id") int club_id) {
		ClubDto club = clubMapper.findById(club_id);
		List<BoardDto> boards = boardMapper.findByClubId_p(club_id);
		model.addAttribute("club", club);
		model.addAttribute("boards",boards);
		return "guest/list-content";
	}

	@RequestMapping("publicity")
	public String publicity(Model model) {
		List<BoardDto> boards = boardMapper.findAll_p();
		model.addAttribute("boards",boards);
		return "guest/publicity";
	}

	@RequestMapping("recruit")
	public String recruit(Model model) {
		List<BoardDto> boards = boardMapper.findAll_r();
		model.addAttribute("boards",boards);
		return "guest/recruit";
	}
}
