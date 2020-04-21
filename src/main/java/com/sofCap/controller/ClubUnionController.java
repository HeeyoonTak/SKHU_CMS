package com.sofCap.controller;

import java.security.Principal;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sofCap.dto.AccountDto;
import com.sofCap.dto.BoardDto;
import com.sofCap.dto.SemDateDto;
import com.sofCap.dto.UserDto;
import com.sofCap.mapper.BoardMapper;
import com.sofCap.mapper.UserMapper;
import com.sofCap.model.SemDate;
import com.sofCap.service.AccountService;
import com.sofCap.service.AttendanceService;
import com.sofCap.service.ClubService;
import com.sofCap.service.SemDateService;
import com.sofCap.service.UserService;

@Controller
@RequestMapping("club_union")
public class ClubUnionController {

	@Autowired
	UserService userService;
	@Autowired
	AttendanceService attendanceService;

	@RequestMapping("attendance")
	public String attendance(Model model, @RequestParam(value = "semId", defaultValue = "0") int semId) {

		// 화면 select box 사용하기 위한 데이터 가공
		List<SemDateDto> semDate = semdateService.findAll();
		Map<String, String> sems = new HashMap<>();
		for (int i = 0; i < semDate.size(); i++) {
			sems.put(Integer.toString(semDate.get(i).getId()), semDate.get(i).getSem_name());
		}
		model.addAttribute("sems", sems);

		// 처음 화면 실행시 최근 학기 데이터 불러오기
		if (semId == 0) {
			semId = semDate.get(semDate.size() - 1).getId();
		}

		model.addAttribute("selectSemId", semId);
		model.addAttribute("findDate", attendanceService.findDate(semId));
		model.addAttribute("attendance", attendanceService.findBySemDate(semId));
		model.addAttribute("adminUser", attendanceService.findAdmin(semId));

		return "club_union/attendance";
	}

	@RequestMapping("attendance_delete")
	public String delete(Model model, @RequestParam("date") Date date) {
		attendanceService.delete(date);
		return "redirect:attendance";
	}

	@Autowired
	BoardMapper boardMapper;
	@Autowired
	UserMapper userMapper;

	@RequestMapping("notice")
	public String union_notice(Model model, Principal principal) {
		UserDto user = userMapper.findByLoginId(principal.getName());
		List<BoardDto> boards = boardMapper.findAll_n();
		model.addAttribute("user", user);
		model.addAttribute("boards", boards);
		return "club_union/union_notice";
	}

	@RequestMapping("n_content")
	public String n_content(Model model, @RequestParam("id") int id) {
		BoardDto board = boardMapper.findOne(id);
		model.addAttribute("board",board);
		return "club_union/n_content";
	}

	@RequestMapping("n_delete")
	public String n_delete(Model model, @RequestParam("id") int id) {
		boardMapper.delete(id);
		return "redirect:notice";
	}

	@RequestMapping("minutes")
	public String union_minutes(Model model, Principal principal) {
		UserDto user = userMapper.findByLoginId(principal.getName());
		List<BoardDto> boards = boardMapper.findAll_m();
		model.addAttribute("user", user);
		model.addAttribute("boards", boards);
		return "club_union/union_minutes";
	}

	@RequestMapping("m_content")
	public String m_content(Model model, @RequestParam("id") int id) {
		BoardDto board = boardMapper.findOne(id);
		model.addAttribute("board",board);
		return "club_union/m_content";
	}

	@RequestMapping("m_delete")
	public String m_delete(Model model, @RequestParam("id") int id) {
		boardMapper.delete(id);
		return "redirect:minutes";
	}

	@Autowired
	AccountService accountService;
	@Autowired
	ClubService clubService;
	@Autowired
	SemDateService semdateService;

//	@RequestMapping("account")
//	public List<AccountDto> account() {
//		return accountService.findAll();
//	}

	// 버전 1
//	@RequestMapping("account")
//	public String account(Model model, @RequestParam(name="club_id",defaultValue="1") int club_id) {
//		List<AccountDto> accounts = accountService.findByClubId(club_id);
//		model.addAttribute("accounts", accounts);
//		model.addAttribute("clubs",clubService.findAll());
//		return "club_union/account";
//	}

	// 버전 2
//	@RequestMapping("account")
//	public String account(Model model) {
//		List<AccountDto> accounts = accountService.findAll();
//		model.addAttribute("accounts", accounts);
//		model.addAttribute("clubs",clubService.findAll());
//		return "club_union/account";
//	}

	// 버전 3
	@RequestMapping("account")
	public String account(Model model, SemDate semdate) {
		System.out.print(semdate.getSem_name());
		List<AccountDto> accounts = accountService.findBySem(semdate);
		model.addAttribute("accounts", accounts);
		model.addAttribute("clubs", clubService.findAll());
		model.addAttribute("sems", semdateService.findAll());
		model.addAttribute("semdate", semdate);
		return "club_union/account";
	}

	@RequestMapping("club_list")
	public String list(Model model) {
		List<UserDto> users = userMapper.findAll();
		model.addAttribute("users", users);
		return "club_union/club_manage";
	}

	@RequestMapping(value = "club_create", method = RequestMethod.GET)
	public String create(Model model) {
		UserDto user = new UserDto();
		model.addAttribute("user", user);
		return "club_union/club_create";
	}

	@RequestMapping(value = "club_create", method = RequestMethod.POST)
	public String create(Model model, UserDto user) {
		userMapper.insert(user);
		return "redirect:club_list";
	}

	@RequestMapping(value = "club_edit", method = RequestMethod.GET)
	public String edit(Model model, @RequestParam("id") int id) {
		UserDto user = userMapper.findOne(id);
		model.addAttribute("user", user);
		return "club_union/club_update";
	}

	@RequestMapping(value = "club_edit", method = RequestMethod.POST)
	public String edit(Model model, UserDto user) {
		userMapper.update(user);
		return "redirect:club_list";
	}

	@RequestMapping("club_delete")
	public String delete(Model model, @RequestParam("id") int id) {
		userMapper.delete(id);
		return "redirect:club_list";
	}
}
