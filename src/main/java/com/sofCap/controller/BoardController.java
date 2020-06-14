package com.sofCap.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.security.Principal;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sofCap.dto.ApplyADto;
import com.sofCap.dto.ApplyQDto_mod;
import com.sofCap.dto.BoardDto;
import com.sofCap.dto.ClubDto;
import com.sofCap.dto.FilesDto;
import com.sofCap.dto.UserDto;
import com.sofCap.mapper.BoardMapper;
import com.sofCap.mapper.ClubMapper;
import com.sofCap.mapper.FileMapper;
import com.sofCap.model.Pagination;
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
	@Autowired
	FileMapper fileMapper;
	@Autowired
	BoardMapper boardMapper;

	//네비게이션 동아리 띄우기
	public void nav_list(Model model) {
		List<ClubDto> clubs = clubService.findAll();
		model.addAttribute("clubs", clubs);
	}

	//네비게이션 user 동아리 띄우기
	public void nav_user(Model model, Principal principal) {
		if (principal == null)
			return;
		else {
			UserDto user = userService.findByLoginId(principal.getName());
			List<ClubDto> user_clubs = clubService.findByUserId(user.getId());
			model.addAttribute("user_clubs", user_clubs);
		}
	}

	//각 동아리 소개
	@RequestMapping("list-content")
	public String list_content(Model model, @RequestParam("id") int club_id, Principal principal
			, Pagination pagination) {
		ClubDto club = clubService.findById(club_id);
		List<BoardDto> boards_p = boardService.findByClubId_p(club_id);
		List<BoardDto> boards_r = boardService.findByClubId_r(club_id);
        List<BoardDto> list = boardMapper.findAll_pp(pagination);
        pagination.setRecordCount(boardMapper.count_p());
        model.addAttribute("list", list);

		model.addAttribute("club", club);
		model.addAttribute("boards_p", boards_p);
		model.addAttribute("boards_r", boards_r);
		nav_list(model);
		nav_user(model, principal);
		return "guest/list-content";
	}

	//이미지 불러오기
	@RequestMapping(value = "getClubImage")
	public void getClubImage(HttpServletRequest req, HttpServletResponse res, @RequestParam("id") int id)
			throws IOException {
		res.setContentType("image/jpeg");
		FilesDto file = fileMapper.getClubImage(id);
		byte[] imagefile = file.getData();
		InputStream in1 = new ByteArrayInputStream(imagefile);
		IOUtils.copy(in1, res.getOutputStream());
	}

	//전체 홍보게시물
	@RequestMapping("p_content")
	public String p_content(Model model, @RequestParam("id") int id, Principal principal) {
		BoardDto board = boardService.findOne(id);
		model.addAttribute("board", board);
		model.addAttribute("club_name", boardService.findOneClub(id).getClub_name());
		model.addAttribute("club_content", boardService.findOneClub(id).getClub_content());
		nav_list(model);
		nav_user(model, principal);
		return "guest/p_content";
	}

	//전체 모집게시물
	@RequestMapping("r_content")
	public String r_content(Model model, @RequestParam("id") int id, Principal principal, HttpServletResponse response)
			throws IOException {
		BoardDto board = boardService.findOne(id);
		model.addAttribute("board", board);
		model.addAttribute("club_name", boardService.findOneClub(id).getClub_name());
		model.addAttribute("club_content", boardService.findOneClub(id).getClub_content());
		nav_list(model);
		nav_user(model, principal);

		Date now = new Date();
		Date start = board.getStart_date();
		Date end = board.getEnd_date();

		if (now.after(start) && now.after(end)) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('마감되었습니다.'); history.go(-1);</script>");
			out.flush();
			return "redirect:recruit";
		}
		if (now.before(start) && now.before(start)) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('모집 예정입니다.'); history.go(-1);</script>");
			out.flush();
			return "redirect:recruit";
		}
		return "guest/r_content";
	}

	//동연 공지사항
	@RequestMapping("n_content")
	public String n_content(Model model, @RequestParam("id") int id, Principal principal) {
		BoardDto board = boardService.findOne(id);
		model.addAttribute("board", board);
		nav_list(model);
		nav_user(model, principal);
		return "club_union/n_content";
	}

	//동연 회의록
	@RequestMapping("m_content")
	public String m_content(Model model, @RequestParam("id") int id, Principal principal) {
		BoardDto board = boardService.findOne(id);
		model.addAttribute("board", board);
		nav_list(model);
		nav_user(model, principal);
		return "club_union/m_content";
	}

	//전체 홍보 게시판
	@RequestMapping("publicity")
	public String publicity(Model model, Principal principal, Pagination pagination) {
		List<BoardDto> boards = boardService.findAll_p();
		model.addAttribute("boards", boards);

		List<BoardDto> list = boardMapper.findAll_pp(pagination);
        pagination.setRecordCount(boardMapper.count_p());
        model.addAttribute("list", list);

		nav_list(model);
		nav_user(model, principal);
		return "guest/publicity";
	}

	//전체 모집게시판
	@RequestMapping("recruit")
	public String recruit(Model model, Principal principal) {
		List<BoardDto> boards = boardService.findAll_r();
		model.addAttribute("boards", boards);
		nav_list(model);
		nav_user(model, principal);
		return "guest/recruit";
	}

	@RequestMapping("notice")
	public String union_notice(Model model, Principal principal) {
		List<BoardDto> boards = boardService.findAll_n();
		model.addAttribute("boards", boards);
		nav_list(model);
		nav_user(model, principal);
		return "club_union/union_notice";
	}

	@RequestMapping("minutes")
	public String union_minutes(Model model, Principal principal) {
		List<BoardDto> boards = boardService.findAll_m();
		model.addAttribute("boards", boards);
		nav_list(model);
		nav_user(model, principal);
		return "club_union/union_minutes";
	}

	// 일반 회원이 모집 폼에 지원하기
	@RequestMapping(value = "apply_recruit")
	public String apply_recruit(Model model, @RequestParam("club_id") int club_id, Principal principal,
			HttpServletResponse response) throws IOException {
		if (principal == null) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('로그인 시 지원 가능합니다.'); history.go(-1);</script>");
			out.flush();
			return "redirect:/";
		}
		UserDto user = userService.findByLoginId(principal.getName()); // 현재 로그인한 사용자로 user 정보 획득

		ClubDto club = clubService.findById(club_id);
		List<ApplyQDto_mod> applyQ = clubMapper.findQmodQusetionByClub(club.getId()); // club에 해당되어 있는 질문 리스트 가져오기
		List<ApplyADto> applyAs = clubMapper.findAnswerByClubIdAndUserId(club_id, user.getId());
		ApplyADto[] applyA = new ApplyADto[clubMapper.findAnswerByClubIdAndUserId(club_id, user.getId()).size()];
		for (int i = 0; i < applyAs.size(); i++)
			applyA[i] = applyAs.get(i);
		int applyA_size = applyA.length;
		for (int i = 0; i < applyA.length; i++)
			System.out.println(applyA[i].getId() + " " + applyA[i].getContent());
		System.out.println("대답 개수 : " + applyA_size);
		model.addAttribute("applyA", applyA);
		model.addAttribute("applyA_size", applyA_size);
		int applyQ_size = applyQ.size();
		System.out.println("질문 개수 : " + applyQ_size);
		model.addAttribute("applyQ", applyQ);
		model.addAttribute("applyQ_size", applyQ_size);
		model.addAttribute("club", club);
		model.addAttribute("club_id", club_id);
		nav_list(model);
		nav_user(model, principal);
		return "guest/apply_recruit";
	}

	// 모집 지원 save
	@RequestMapping(value = "apply_a_save", method = RequestMethod.POST)
	public String apply_a_save(Model model, Principal principal, @RequestParam("Qs") int[] questions,
			@RequestParam("answers") String[] answers, @RequestParam("club_id") int club_id) {
		UserDto user = userService.findByLoginId(principal.getName()); // 현재 로그인한 사용자로 user 정보 획득
		saveAnswer(questions, answers, user.getId(), club_id);
		return "redirect:apply_recruit?club_id=" + club_id;
	}

	@Transactional
	private void saveAnswer(int[] Questions, String[] answers, int user_id, int club_id) {
		for (int i = 0; i < answers.length; i++) {
			ApplyADto applyA = new ApplyADto();
			applyA.setApply_q_id(Questions[i]);
			applyA.setContent(answers[i]);
			applyA.setUser_id(user_id);
			applyA.setClub_id(club_id);
			clubMapper.insertA(applyA);
		}
	}

	// 모집 지원 수정
	@RequestMapping("editAnswer")
	public String editAnswer(Model model, Principal principal, @RequestParam("club_id") int club_id,
			@RequestParam("answers") String[] answers) {
		UserDto user = userService.findByLoginId(principal.getName()); // 현재 로그인한 사용자로 user 정보 획득
		List<ApplyADto> applyAs = clubMapper.findAnswerByClubIdAndUserId(club_id, user.getId());
		ApplyADto[] applyA = new ApplyADto[clubMapper.findAnswerByClubIdAndUserId(club_id, user.getId()).size()];
		for (int i = 0; i < applyAs.size(); i++)
			applyA[i] = applyAs.get(i);

		for (int i = 0; i < answers.length; i++) {
			if (!answers[i].equals(""))
				clubMapper.editAnswer(applyA[i].getId(), answers[i]);
		}
		return "redirect:apply_recruit?club_id=" + club_id;
	}

	// 지원 취소
	@RequestMapping("deleteAllAnswer")
	public String deleteAllAnswer(Model model, Principal principal, @RequestParam("club_id") int club_id) {
		UserDto user = userService.findByLoginId(principal.getName()); // 현재 로그인한 사용자로 user 정보 획득
		clubMapper.deleteAnswerByUserId(user.getId(), club_id);
		return "redirect:apply_recruit?club_id=" + club_id;
	}
}