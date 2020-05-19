package com.sofCap.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.Principal;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

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
			List<ClubDto> user_clubs = clubService.findByUserId(user.getId());
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
	public String r_content(Model model, @RequestParam("id") int id,Principal principal, HttpServletResponse response) throws IOException {
		BoardDto board = boardService.findOne(id);
		model.addAttribute("board",board);
		nav_list(model);
		nav_user(model, principal);

		Date now = new Date();
		Date start = board.getStart_date();
		Date end = board.getEnd_date();

		if(now.after(start) && now.after(end)) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('마감되었습니다.'); history.go(-1);</script>");
			out.flush();
			return "redirect:recruit";
		}
		if(now.before(start) && now.before(start)) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('모집 예정입니다.'); history.go(-1);</script>");
			out.flush();
			return "redirect:recruit";
		}
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
