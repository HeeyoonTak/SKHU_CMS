package com.sofCap.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sofCap.dto.UserDto;
import com.sofCap.mapper.UserMapper;

@Controller
@RequestMapping("/club_manage")
public class ClubManageController {
	@Autowired
	UserMapper userMapper;

	@RequestMapping("list")
	public String list(Model model) {
		List<UserDto> users = userMapper.findAll();
		model.addAttribute("users", users);
		return "club_union/club_manage";
	}

	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String create(Model model) {
		UserDto user = new UserDto();
		model.addAttribute("user", user);
		return "club_union/club_create";
	}

	@RequestMapping(value = "create", method = RequestMethod.POST)
	public String create(Model model, UserDto user) {
		userMapper.insert(user);
		return "redirect:list";
	}

	@RequestMapping(value = "edit", method = RequestMethod.GET)
	public String edit(Model model, @RequestParam("id") int id) {
		UserDto user = userMapper.findOne(id);
		model.addAttribute("user", user);
		return "club_union/club_update";
	}

	@RequestMapping(value = "edit", method = RequestMethod.POST)
	public String edit(Model model, UserDto user) {
		userMapper.update(user);
		return "redirect:list";
	}

	@RequestMapping("delete")
	public String delete(Model model, @RequestParam("id") int id) {
		userMapper.delete(id);
		return "redirect:list";
	}

}
