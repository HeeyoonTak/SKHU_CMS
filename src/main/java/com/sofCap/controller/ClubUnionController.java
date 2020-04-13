package com.sofCap.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sofCap.dto.BoardDto;
import com.sofCap.dto.UserDto;
import com.sofCap.mapper.BoardMapper;
import com.sofCap.mapper.UserMapper;


@Controller
@RequestMapping("clubunion")
public class ClubUnionController {
	@Autowired
	BoardMapper boardMapper;
	@Autowired
	UserMapper userMapper;

	@RequestMapping("notice")
	public String union_notice(Model model, Principal principal) {
		UserDto user = userMapper.findByLoginId(principal.getName());
		List<BoardDto> boards = boardMapper.findAll_n();
		model.addAttribute("user",user);
		model.addAttribute("boards",boards);
		return "club_union/union_notice";
	}
}