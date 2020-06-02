package com.sofCap.controller;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.security.Principal;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.sofCap.dto.AccountDto;
import com.sofCap.dto.ApplyADto;
import com.sofCap.dto.ApplyQDto;
import com.sofCap.dto.ApplyQDto_mod;
import com.sofCap.dto.AttendanceDto;
import com.sofCap.dto.BoardDto;
import com.sofCap.dto.ClubDto;
import com.sofCap.dto.FilesDto;
import com.sofCap.dto.SemDateDto;
import com.sofCap.dto.UserClubDto;
import com.sofCap.dto.UserDto;
import com.sofCap.mapper.AccountMapper;
import com.sofCap.mapper.ClubMapper;
import com.sofCap.mapper.FileMapper;
import com.sofCap.mapper.SemDateMapper;
import com.sofCap.model.SemDate;
import com.sofCap.service.AccountService;
import com.sofCap.service.AttendanceService;
import com.sofCap.service.BoardService;
import com.sofCap.service.ClubService;
import com.sofCap.service.ExcelService;
import com.sofCap.service.FileService;
import com.sofCap.service.SemDateService;
import com.sofCap.service.UserClubService;
import com.sofCap.service.UserService;

@Controller
@RequestMapping("club_admin")
public class ClubAdminController {

	@Autowired
	AttendanceService attendanceService;
	@Autowired
	UserService userService;
	@Autowired
	UserClubService userClubService;
	@Autowired
	BoardService boardService;
	@Autowired
	SemDateMapper semdateMapper;
	@Autowired
	AccountService accountService;
	@Autowired
	SemDateService semdateService;
	@Autowired
	ClubService clubService;
	@Autowired
	AccountMapper accountMapper;
	@Autowired
	FileMapper fileMapper;
	@Autowired
	FileService fileService;
	@Autowired
	ClubMapper clubMapper;
	@Autowired
	ExcelService excelService;

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

	/*
	 * 지원자 합불 구현하기
	 */
	@GetMapping("acceptance")
	public String acceptane(Model model, @RequestParam("club_id") int club_id, Principal principal,
			@RequestParam(value = "user_id", defaultValue = "0") int user_id, HttpServletResponse response)
			throws IOException {
		UserDto user = userService.findByLoginId(principal.getName());
		ClubDto club = clubService.findById(club_id);
		List<UserDto> acceptanceYes = userService.findByMember(club_id);
		List<UserDto> acceptanceNo = userService.findByNotMember(club_id);
		List<ApplyQDto> questionList = clubService.findQuestion(club_id);
		List<ApplyADto> answerList = clubService.findAnswer(club_id, user_id);
		List<ApplyADto> answerList1 = clubService.findAnswerByClubId(club_id);
		model.addAttribute("user", user);
		model.addAttribute("club", club);
		model.addAttribute("club_id", club_id);

		model.addAttribute("acceptanceYes", acceptanceYes);
		model.addAttribute("acceptanceNo", acceptanceNo);
		model.addAttribute("questionList", questionList);
		model.addAttribute("answerList", answerList);
		model.addAttribute("answerList1", answerList1);
		model.addAttribute("user_id", user_id);
		nav_list(model);
		nav_user(model, principal);
		System.out.println(user.getUser_type());
		if (user.getUser_type().equals("동아리관리자")) {
			return "club_admin/acceptance";
		} else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('접근이 제한된 사용자입니다.'); history.go(-1);</script>");
			out.flush();
			return "redirect:notice?club_id=" + club_id;
		}
	}

	/* 지원자 개별 합격 */
	@PostMapping(value = "acceptance", params = "cmd=yes")
	public String acceptanceYes(Model model, Principal principal, @RequestParam("user_id") int user_id,
			@RequestParam("club_id") int club_id) {
		UserDto user = userService.findByLoginId(principal.getName());
		ClubDto club = clubService.findById(club_id);
		UserClubDto userClub = userClubService.findByUserId(user_id);
		List<UserDto> acceptanceYes = userService.findByMember(club_id);
		List<UserDto> acceptanceNo = userService.findByNotMember(club_id);
		model.addAttribute("user", user);
		model.addAttribute("club", club);
		model.addAttribute("userClub", userClub);
		model.addAttribute("acceptanceYes", acceptanceYes);
		model.addAttribute("acceptanceNo", acceptanceNo);
		System.out.println("유저 id: " + user_id);
		System.out.println("클럽 id: " + club_id);
		userClubService.insert(user_id, club_id);
		userService.updateNotMemberRole(user_id);
		userService.deleteCandidate(user_id);
		return "redirect:acceptance?club_id=" + club_id;
	}

	/* 합격자 개별 취소 or 기존회원 개별 제명 */ // 영구제명이므로 지원자로 복귀 X
	@PostMapping(value = "acceptance", params = "cmd=no")
	public String acceptanceNo(Model model, Principal principal, @RequestParam("user_id") int user_id,
			@RequestParam("club_id") int club_id) {
		UserDto user = userService.findByLoginId(principal.getName());
		ClubDto club = clubService.findById(club_id);
		UserClubDto userClub = userClubService.findByUserId(user_id);
		List<UserDto> acceptanceYes = userService.findByMember(club_id);
		List<UserDto> acceptanceNo = userService.findByNotMember(club_id);
		model.addAttribute("user", user);
		model.addAttribute("club", club);
		model.addAttribute("userClub", userClub);
		model.addAttribute("acceptanceYes", acceptanceYes);
		model.addAttribute("acceptanceNo", acceptanceNo);
		System.out.println(userClubService.userCount(user_id));
		userClubService.deleteMember(user_id, club_id);
		if(userClubService.userCount(user_id)==0) {
			userService.updateMemberRole(user_id);
		}else {
			System.out.println(userClubService.userCount(user_id));
		}
		return "redirect:acceptance?club_id=" + club_id;
	}

	/* 체크박스로 지원자 합격 */
	@ResponseBody
	@PostMapping(value = "acceptAll")
	public void acceptAll(@RequestParam(value = "chbox2[]", defaultValue = "0") List<Integer> chArr,
			@RequestParam(value = "club_id") int club_id, Model model) throws Exception {
		ClubDto club = clubService.findById(club_id);
		model.addAttribute("club", club);
		for (int i : chArr) {
			System.out.println(i);
			System.out.println(club_id);
			userClubService.insert(i, club_id);
			userService.updateNotMemberRole(i);
			userService.deleteCandidate(i);
		}
	}

	/* 체크박스로 회원 제명 */
	@ResponseBody
	@PostMapping(value = "deleteAll")
	public void deleteAll(@RequestParam(value = "chbox[]", defaultValue = "0") List<Integer> chArr,
			@RequestParam(value = "club_id") int club_id, Model model) throws Exception {
		ClubDto club = clubService.findById(club_id);
		model.addAttribute("club", club);
		for (int i : chArr) {
			userClubService.deleteMember(i, club_id);
			if(userClubService.userCount(i)==0) {
				userService.updateMemberRole(i);
			}else {
				System.out.println(userClubService.userCount(i));
			}
		}
	}

	// Modal
	public void getForm(@RequestParam("club_id") int club_id, @RequestParam("user_id") int user_id, Model model,
			Principal principal) throws IOException {
		UserDto user = userService.findByLoginId(principal.getName());
		ClubDto club = clubService.findById(club_id);
		UserClubDto userClub = userClubService.findByUserId(user_id);
		List<ApplyADto> answerList = clubService.findAnswer(club_id, user_id);
		List<ApplyADto> answerList1 = clubService.findAnswerByClubId(club_id);
		List<ApplyQDto> questionList = clubService.findQuestion(club_id);
		List<UserDto> acceptanceYes = userService.findByMember(club_id);
		List<UserDto> acceptanceNo = userService.findByNotMember(club_id);
		model.addAttribute("user", user);
		model.addAttribute("club", club);
		model.addAttribute("userClub", userClub);
		model.addAttribute("acceptanceYes", acceptanceYes);
		model.addAttribute("acceptanceNo", acceptanceNo);
		model.addAttribute("answerList", answerList);
		model.addAttribute("questionList", questionList);
		model.addAttribute("acceptanceNo", acceptanceNo);
		model.addAttribute("user_id", user_id);
	}

	// 동아리마다 모집 지원 _질문 리스트
	@RequestMapping("apply_q_list")
	public String apply_q_list(Model model, @RequestParam("club_id") int club_id, SemDate semdate, Principal principal,
			HttpServletResponse response) throws IOException {
		UserDto user = userService.findByLoginId(principal.getName()); // 현재 로그인한 사용자로 user 정보 획득
		if (!user.getUser_type().equals("동아리관리자")) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('접근이 제한된 사용자입니다.'); history.go(-1);</script>");
			out.flush();
			return "redirect:notice?club_id=" + club_id;
		}
		UserClubDto userclub = userClubService.findByUserId(user.getId()); // user와 연결된 user_club 정보 획득
		ClubDto club = clubService.findById(userclub.getClub_id()); // user_club로 club 정보 획득
		List<ApplyQDto_mod> applyQ = clubMapper.findQmodQusetionByClub(club.getId()); // club에 해당되어 있는 질문 리스트 가져오기
		if (semdate.getSem_name() == null) {
			Date now = Date.valueOf(LocalDate.now());
			String sem_name = semdateMapper.findByDate(now);
			System.out.println(sem_name);
		}
		model.addAttribute("club", club);
		model.addAttribute("club_id", club.getId());
		model.addAttribute("applyQ", applyQ);
		nav_list(model);
		nav_user(model, principal);
		return "club_admin/apply_q_list";

	}

	// 동아리마다 모집 지원 질문 쓰기
	@RequestMapping(value = "apply_q_save", method = RequestMethod.POST)
	public String apply_q_save(Model model, Principal principal, @RequestParam("question") String[] questions) {
		UserDto user = userService.findByLoginId(principal.getName()); // 현재 로그인한 사용자로 user 정보 획득
		UserClubDto userclub = userClubService.findByUserId(user.getId()); // user와 연결된 user_club 정보 획득
		ClubDto club = clubService.findById(userclub.getClub_id()); // user_club로 club 정보 획득
		saveQusetion(questions, club.getId());
		return "redirect:apply_q_list?club_id=" + club.getId();
	}

	@Transactional
	private void saveQusetion(String[] questions, int club_id) {
		int board_id = 2;
		Date now = Date.valueOf(LocalDate.now());
		String sem_name = semdateMapper.findByDate(now);
		SemDateDto semdate = semdateMapper.findStartAndEndDate(sem_name);
		for (int i = 0; i < questions.length; i++) {
			ApplyQDto applyq = new ApplyQDto();
			applyq.setContent(questions[i]);
			applyq.setBoard_id(board_id);
			applyq.setClub_id(club_id);
			applyq.setSemDate_id(semdate.getId());
			clubMapper.insertQ(applyq);
		}
	}

	// 질문 수정
	@RequestMapping(value = "apply_q_edit", method = RequestMethod.POST)
	public String apply_q_edit(Model model, Principal principal,
			@RequestParam("edited_question") String edited_question, @RequestParam("id") int id) {
		UserDto user = userService.findByLoginId(principal.getName()); // 현재 로그인한 사용자로 user 정보 획득
		UserClubDto userclub = userClubService.findByUserId(user.getId()); // user와 연결된 user_club 정보 획득
		ClubDto club = clubService.findById(userclub.getClub_id()); // user_club로 club 정보 획득
		ApplyQDto edit_Q = clubMapper.QfindById(id);
		edit_Q.setContent(edited_question);
		clubMapper.editQ(edit_Q);

		return "redirect:apply_q_list?club_id=" + club.getId();
	}

	// 모집 질문 삭제
	@RequestMapping("applyQ_delete")
	public String deleteQ(Model model, Principal principal, @RequestParam("id") int id) {
		UserDto user = userService.findByLoginId(principal.getName()); // 현재 로그인한 사용자로 user 정보 획득
		UserClubDto userclub = userClubService.findByUserId(user.getId()); // user와 연결된 user_club 정보 획득
		ClubDto club = clubService.findById(userclub.getClub_id()); // user_club로 club 정보 획득
		clubService.deleteQ(id);
		return "redirect:apply_q_list?club_id=" + club.getId();
	}

	// 질문 삭제 ( 질문에 따른 답변 모두 삭제 )
	@RequestMapping("apply_all_delete")
	public String all_delete(Model model, Principal principal) {
		UserDto user = userService.findByLoginId(principal.getName()); // 현재 로그인한 사용자로 user 정보 획득
		UserClubDto userclub = userClubService.findByUserId(user.getId()); // user와 연결된 user_club 정보 획득
		ClubDto club = clubService.findById(userclub.getClub_id()); // user_club로 club 정보 획득
		List<ApplyADto> applyA_all = clubMapper.findAnswerByClubId(club.getId());
		for (int i = 0; i < applyA_all.size(); i++) {
			clubMapper.deleteA(applyA_all.get(i).getId());
		}
		List<ApplyQDto> applyQ_all = clubMapper.findQuestionByClub(club.getId());
		for (int i = 0; i < applyQ_all.size(); i++) {
			clubMapper.deleteQ(applyQ_all.get(i).getId());
		}
		nav_list(model);
		nav_user(model, principal);
		return "redirect:apply_q_list?club_id=" + club.getId();
	}


	/*
	 * ASY_board 동아리 관리
	 */
	@RequestMapping("club_manage")
	public String club_manage(Model model, @RequestParam("club_id") int club_id, Principal principal,
			HttpServletResponse response) throws IOException {
		model.addAttribute("club_id", club_id);
		UserDto user = userService.findByLoginId(principal.getName());
		nav_list(model);
		nav_user(model, principal);
		if (user.getUser_type().equals("동아리관리자")) {
			return "club_admin/club_manage";
		} else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('접근이 제한된 사용자입니다.'); history.go(-1);</script>");
			out.flush();
			return "redirect:notice?club_id=" + club_id;
		}
	}

	/* 동아리 정보 편집 구현 */
	@RequestMapping(value = "manage", method = RequestMethod.GET)
	public String manage(Model model, @RequestParam("club_id") int club_id, Principal principal,
			HttpServletResponse response) throws IOException {
		UserDto user = userService.findByLoginId(principal.getName());
		ClubDto club = clubService.findById(club_id);
		model.addAttribute("club_id", club_id);
		model.addAttribute("club", club);
		nav_list(model);
		nav_user(model, principal);
		if (user.getUser_type().equals("동아리관리자")) {
			return "club_admin/manage";
		} else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('접근이 제한된 사용자입니다.'); history.go(-1);</script>");
			out.flush();
			return "redirect:notice?club_id=" + club_id;
		}
	}

	@RequestMapping(value = "manage", method = RequestMethod.POST)
	public String manage(Model model, @RequestBody MultipartFile file_id, @RequestParam("club_id") int club_id,
			@RequestParam("content") String content) throws IOException {
		ClubDto club = clubService.findById(club_id);
		if (!file_id.isEmpty()) {
			int f_id = fileService.clubFileUpload(file_id);
			club.setFile_id(f_id);
		}
		club.setContent(content);
		clubService.update(club);
		return "redirect:manage?club_id=" + club_id;
	}

	@RequestMapping(value = "getClubImage")
	public void getClubImage(HttpServletRequest req, HttpServletResponse res, @RequestParam("id") int id)
			throws IOException {
		res.setContentType("image/jpeg");
		FilesDto file = fileMapper.getClubImage(id);
		byte[] imagefile = file.getData();
		InputStream in1 = new ByteArrayInputStream(imagefile);
		IOUtils.copy(in1, res.getOutputStream());
	}

	/*
	 * ASY_board 동아리 공지사항
	 */
	@RequestMapping("notice")
	public String club_notice(Model model, @RequestParam("club_id") int club_id, Principal principal) {
		List<BoardDto> boards = boardService.findByClubId_n(club_id);
		ClubDto club = clubService.findById(club_id);
		model.addAttribute("club", club);
		model.addAttribute("boards", boards);
		model.addAttribute("club_id", club_id);
		nav_list(model);
		nav_user(model, principal);
		return "club_admin/club_notice";
	}

	/* 해당 게시글로 이동 */
	@RequestMapping("n_content")
	public String n_content(Model model, @RequestParam("id") int id, @RequestParam("club_id") int club_id,
			Principal principal) {
		BoardDto board = boardService.findOne(id);
		model.addAttribute("board", board);
		ClubDto club = clubService.findById(club_id);
		model.addAttribute("club", club);
		nav_list(model);
		nav_user(model, principal);
		return "club_admin/n_content";
	}

	/* 게시글 수정 로직 구현 */
	@RequestMapping(value = "n_edit", method = RequestMethod.GET)
	public String n_edit(@RequestParam("id") int id, Model model, BoardDto board,
			Principal principal, HttpServletResponse response) throws IOException {
		UserDto user = userService.findByLoginId(principal.getName());
		board.setBoard_name_id(3);
		board = boardService.findById(id);
		model.addAttribute("board", board);
		nav_list(model);
		nav_user(model, principal);
		if (user.getUser_type().equals("동아리관리자")) {
			return "club_admin/posting";
	      } else {
	         response.setContentType("text/html; charset=UTF-8");
	         PrintWriter out = response.getWriter();
	         out.println("<script>alert('접근이 제한된 사용자입니다.'); history.go(-1);</script>");
	         out.flush();
	         return "redirect:n_content";
	      }
	}

	@Transactional
	@RequestMapping(value = "n_edit", method = RequestMethod.POST)
	public String n_edit(BoardDto board, Model model) {
		boardService.update(board);
		return "redirect:n_content?club_id=" + board.getClub_id() + "&id=" + board.getId();
	}

	/* 게시글 삽입 로직 구현 */
	@RequestMapping(value = "n_create", method = RequestMethod.GET)
	public String n_create(Model model, BoardDto board, @RequestParam("club_id") int club_id,
			Principal principal, HttpServletResponse response) throws IOException {
		UserDto user = userService.findByLoginId(principal.getName());
		board.setBoard_name_id(3);
		board.setClub_id(club_id);
		board = new BoardDto();
		model.addAttribute("board", board);
		ClubDto club = clubService.findById(club_id);
		model.addAttribute("club", club);
		nav_list(model);
		nav_user(model, principal);
		if (user.getUser_type().equals("동아리관리자")) {
			return "club_admin/posting";
	      } else {
	         response.setContentType("text/html; charset=UTF-8");
	         PrintWriter out = response.getWriter();
	         out.println("<script>alert('접근이 제한된 사용자입니다.'); history.go(-1);</script>");
	         out.flush();
	         return "redirect:n_content";
	      }
	}

	@Transactional
	@RequestMapping(value = "n_create", method = RequestMethod.POST)
	public String n_create(BoardDto board, Model model, @RequestParam("club_id") int club_id) {
		board.setBoard_name_id(3);
		board.setClub_id(club_id);
		boardService.insert(board);
		return "redirect:n_content?club_id=" + board.getClub_id() + "&id=" + board.getId();
	}

	/* 게시글 삭제 로직 구현 */
	@RequestMapping("n_delete")
	public String n_delete(Model model, @RequestParam("id") int id,
			Principal principal, HttpServletResponse response) throws IOException {
		UserDto user = userService.findByLoginId(principal.getName());
		BoardDto board = boardService.findById(id);
		boardService.delete(id);
		if (user.getUser_type().equals("동아리관리자")) {
			return "redirect:notice?club_id=" + board.getClub_id();
	      } else {
	         response.setContentType("text/html; charset=UTF-8");
	         PrintWriter out = response.getWriter();
	         out.println("<script>alert('접근이 제한된 사용자입니다.'); history.go(-1);</script>");
	         out.flush();
	         return "redirect:notice?club_id=" + board.getClub_id();
	      }
	}

	/*
	 * ASY_board 동아리 회의록
	 */
	@RequestMapping("minutes")
	public String club_minutes(Model model, SemDate semdate, @RequestParam("club_id") int club_id,
			Principal principal) {
		if (semdate.getSem_name() == null) {
			Date now = Date.valueOf(LocalDate.now());
			String sem_name = semdateMapper.findByDate(now);
			System.out.println(sem_name);
		}
		String sem_name = semdate.getSem_name();
		List<BoardDto> boards = boardService.findBySem_a(sem_name, club_id);
		SemDateDto startenddate = semdateService.findStartAndEndDate(sem_name);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String start_date = format.format(startenddate.getStart_date());
		String end_date = format.format(startenddate.getEnd_date());
		ClubDto club = clubService.findById(club_id);
		model.addAttribute("club", club);
		model.addAttribute("sems", semdateService.findAll());
		model.addAttribute("semdate", semdate);
		model.addAttribute("start_date", start_date);
		model.addAttribute("end_date", end_date);
		model.addAttribute("boards", boards);
		model.addAttribute("club_id", club_id);
		nav_list(model);
		nav_user(model, principal);
		return "club_admin/club_minutes";
	}

	/* 해당 게시글로 이동 */
	@RequestMapping("m_content")
	public String m_content(Model model, @RequestParam("id") int id, @RequestParam("club_id") int club_id,
			Principal principal) {
		BoardDto board = boardService.findOne(id);
		model.addAttribute("board", board);
		nav_list(model);
		nav_user(model, principal);
		return "club_admin/m_content";
	}

	/* 게시글 수정 로직 구현 */
	@RequestMapping(value = "m_edit", method = RequestMethod.GET)
	public String m_edit(@RequestParam("id") int id, Model model, BoardDto board,
			Principal principal, HttpServletResponse response) throws IOException {
		UserDto user = userService.findByLoginId(principal.getName());
		board.setBoard_name_id(4);
		board = boardService.findById(id);
		model.addAttribute("board", board);
		nav_list(model);
		nav_user(model, principal);
		if (user.getUser_type().equals("동아리관리자")) {
			return "club_admin/posting";
	      } else {
	         response.setContentType("text/html; charset=UTF-8");
	         PrintWriter out = response.getWriter();
	         out.println("<script>alert('접근이 제한된 사용자입니다.'); history.go(-1);</script>");
	         out.flush();
	         return "redirect:n_content";
	      }
	}

	@Transactional
	@RequestMapping(value = "m_edit", method = RequestMethod.POST)
	public String m_edit(BoardDto board, Model model) {
		boardService.update(board);
		return "redirect:m_content?club_id=" + board.getClub_id() + "&id=" + board.getId();
	}

	/* 게시글 삽입 로직 구현 */
	@RequestMapping(value = "m_create", method = RequestMethod.GET)
	public String m_create(Model model, BoardDto board, @RequestParam("club_id") int club_id,
			Principal principal, HttpServletResponse response) throws IOException {
		UserDto user = userService.findByLoginId(principal.getName());
		board.setBoard_name_id(4);
		board.setClub_id(club_id);
		board = new BoardDto();
		model.addAttribute("board", board);
		nav_list(model);
		nav_user(model, principal);
		if (user.getUser_type().equals("동아리관리자")) {
			return "club_admin/posting";
	      } else {
	         response.setContentType("text/html; charset=UTF-8");
	         PrintWriter out = response.getWriter();
	         out.println("<script>alert('접근이 제한된 사용자입니다.'); history.go(-1);</script>");
	         out.flush();
	         return "redirect:n_content";
	      }
	}

	@Transactional
	@RequestMapping(value = "m_create", method = RequestMethod.POST)
	public String m_create(BoardDto board, Model model, @RequestParam("club_id") int club_id) {
		board.setBoard_name_id(4);
		board.setClub_id(club_id);
		boardService.insert(board);
		return "redirect:m_content?club_id=" + board.getClub_id() + "&id=" + board.getId();
	}

	/* 게시글 삭제 로직 구현 */
	@RequestMapping("m_delete")
	public String m_delete(Model model, @RequestParam("id") int id,
			Principal principal, HttpServletResponse response) throws IOException {
		UserDto user = userService.findByLoginId(principal.getName());
		BoardDto board = boardService.findById(id);
		boardService.delete(id);
		if (user.getUser_type().equals("동아리관리자")) {
			return "redirect:minutes?club_id=" + board.getClub_id();
	      } else {
	         response.setContentType("text/html; charset=UTF-8");
	         PrintWriter out = response.getWriter();
	         out.println("<script>alert('접근이 제한된 사용자입니다.'); history.go(-1);</script>");
	         out.flush();
	         return "redirect:minutes?club_id=" + board.getClub_id();
	      }
	}

	/*
	 * ASY_board 동아리 홍보게시판
	 */
	@RequestMapping("publicity")
	public String club_publicity(Model model, @RequestParam("club_id") int club_id, Principal principal) {
		List<BoardDto> boards = boardService.findByClubId_p(club_id);
		ClubDto club = clubService.findById(club_id);
		model.addAttribute("club", club);
		model.addAttribute("boards", boards);
		model.addAttribute("club_id", club_id);
		nav_list(model);
		nav_user(model, principal);
		return "club_admin/club_publicity";
	}

	/* 해당 게시글로 이동 */
	@RequestMapping("p_content")
	public String p_content(Model model, @RequestParam("id") int id, @RequestParam("club_id") int club_id,
			Principal principal) {
		BoardDto board = boardService.findOne(id);
		model.addAttribute("board", board);
		nav_list(model);
		nav_user(model, principal);
		return "club_admin/p_content";
	}

	/* 게시글 수정 로직 구현 */
	@RequestMapping(value = "p_edit", method = RequestMethod.GET)
	public String p_edit(@RequestParam("id") int id, Model model, BoardDto board,
			Principal principal, HttpServletResponse response) throws IOException {
		UserDto user = userService.findByLoginId(principal.getName());
		board.setBoard_name_id(1);
		board = boardService.findById(id);
		model.addAttribute("board", board);
		nav_list(model);
		nav_user(model, principal);
		if (user.getUser_type().equals("동아리관리자")) {
			return "club_admin/posting";
	      } else {
	         response.setContentType("text/html; charset=UTF-8");
	         PrintWriter out = response.getWriter();
	         out.println("<script>alert('접근이 제한된 사용자입니다.'); history.go(-1);</script>");
	         out.flush();
	         return "redirect:p_content";
	      }
	}

	@Transactional
	@RequestMapping(value = "p_edit", method = RequestMethod.POST)
	public String p_edit(BoardDto board, Model model) {
		boardService.update(board);
		return "redirect:p_content?club_id=" + board.getClub_id() + "&id=" + board.getId();
	}

	/* 게시글 삽입 로직 구현 */
	@RequestMapping(value = "p_create", method = RequestMethod.GET)
	public String p_create(Model model, BoardDto board, @RequestParam("club_id") int club_id,
			Principal principal, HttpServletResponse response) throws IOException {
		UserDto user = userService.findByLoginId(principal.getName());
		board.setBoard_name_id(1);
		board.setClub_id(club_id);
		board = new BoardDto();
		model.addAttribute("board", board);
		nav_list(model);
		nav_user(model, principal);
		if (user.getUser_type().equals("동아리관리자")) {
			return "club_admin/posting";
	      } else {
	         response.setContentType("text/html; charset=UTF-8");
	         PrintWriter out = response.getWriter();
	         out.println("<script>alert('접근이 제한된 사용자입니다.'); history.go(-1);</script>");
	         out.flush();
	         return "redirect:p_content";
	      }
	}

	@Transactional
	@RequestMapping(value = "p_create", method = RequestMethod.POST)
	public String p_create(BoardDto board, Model model, @RequestParam("club_id") int club_id) {
		board.setBoard_name_id(1);
		board.setClub_id(club_id);
		boardService.insert(board);
		return "redirect:p_content?club_id=" + board.getClub_id() + "&id=" + board.getId();
	}

	/* 게시글 삭제 로직 구현 */
	@RequestMapping("p_delete")
	public String p_delete(Model model, @RequestParam("id") int id,
			Principal principal, HttpServletResponse response) throws IOException {
		UserDto user = userService.findByLoginId(principal.getName());
		BoardDto board = boardService.findById(id);
		boardService.delete(id);
		if (user.getUser_type().equals("동아리관리자")) {
			return "redirect:publicity?club_id=" + board.getClub_id();
	      } else {
	         response.setContentType("text/html; charset=UTF-8");
	         PrintWriter out = response.getWriter();
	         out.println("<script>alert('접근이 제한된 사용자입니다.'); history.go(-1);</script>");
	         out.flush();
	         return "redirect:publicity?club_id=" + board.getClub_id();
	      }

	}

	/*
	 * ASY_board 동아리 모집게시판
	 */
	@RequestMapping("recruit")
	public String club_recruit(Model model, @RequestParam("club_id") int club_id, Principal principal) {
		List<BoardDto> boards = boardService.findByClubId_r(club_id);
		ClubDto club = clubService.findById(club_id);
		model.addAttribute("club", club);
		model.addAttribute("boards", boards);
		model.addAttribute("club_id", club_id);
		nav_list(model);
		nav_user(model, principal);
		return "club_admin/club_recruit";
	}

	/* 해당 게시글로 이동 */
	@RequestMapping("r_content")
	public String r_content(Model model, @RequestParam("id") int id, @RequestParam("club_id") int club_id,
			Principal principal) {
		BoardDto board = boardService.findOne(id);
		model.addAttribute("board", board);
		nav_list(model);
		nav_user(model, principal);
		return "club_admin/r_content";
	}

	/* 게시글 수정 로직 구현 */
	@RequestMapping(value = "r_edit", method = RequestMethod.GET)
	public String r_edit(@RequestParam("id") int id, Model model, BoardDto board,
			Principal principal, HttpServletResponse response) throws IOException {
		UserDto user = userService.findByLoginId(principal.getName());
		board.setBoard_name_id(2);
		board = boardService.findById(id);
		model.addAttribute("board", board);
		nav_list(model);
		nav_user(model, principal);
		if (user.getUser_type().equals("동아리관리자")) {
			return "club_admin/posting";
	      } else {
	         response.setContentType("text/html; charset=UTF-8");
	         PrintWriter out = response.getWriter();
	         out.println("<script>alert('접근이 제한된 사용자입니다.'); history.go(-1);</script>");
	         out.flush();
	         return "redirect:r_content";
	      }
	}

	@Transactional
	@RequestMapping(value = "r_edit", method = RequestMethod.POST)
	public String r_edit(BoardDto board, Model model) {
		boardService.update(board);
		return "redirect:r_content?club_id=" + board.getClub_id() + "&id=" + board.getId();
	}

	/* 게시글 삽입 로직 구현 */
	@RequestMapping(value = "r_create", method = RequestMethod.GET)
	public String r_create(Model model, BoardDto board, @RequestParam("club_id") int club_id,
			Principal principal, HttpServletResponse response) throws IOException {
		UserDto user = userService.findByLoginId(principal.getName());
		board.setBoard_name_id(2);
		board.setClub_id(club_id);
		board = new BoardDto();
		model.addAttribute("board", board);
		nav_list(model);
		nav_user(model, principal);
		if (user.getUser_type().equals("동아리관리자")) {
			return "club_admin/posting";
	      } else {
	         response.setContentType("text/html; charset=UTF-8");
	         PrintWriter out = response.getWriter();
	         out.println("<script>alert('접근이 제한된 사용자입니다.'); history.go(-1);</script>");
	         out.flush();
	         return "redirect:r_content";
	      }
	}

	@Transactional
	@RequestMapping(value = "r_create", method = RequestMethod.POST)
	public String r_create(BoardDto board, Model model, @RequestParam("club_id") int club_id) {
		board.setBoard_name_id(2);
		board.setClub_id(club_id);
		boardService.insert(board);
		return "redirect:r_content?club_id=" + board.getClub_id() + "&id=" + board.getId();
	}

	/* 게시글 삭제 로직 구현 */
	@RequestMapping("r_delete")
	public String r_delete(Model model, @RequestParam("id") int id,
			Principal principal, HttpServletResponse response) throws IOException {
		UserDto user = userService.findByLoginId(principal.getName());
		BoardDto board = boardService.findById(id);
		boardService.delete(id);
		if (user.getUser_type().equals("동아리관리자")) {
			return "redirect:recruit?club_id=" + board.getClub_id();
	      } else {
	         response.setContentType("text/html; charset=UTF-8");
	         PrintWriter out = response.getWriter();
	         out.println("<script>alert('접근이 제한된 사용자입니다.'); history.go(-1);</script>");
	         out.flush();
	         return "redirect:recruit?club_id=" + board.getClub_id();
	      }
	}

	/*
	 * LHM_account 동아리 회계
	 */
	String[] account_type = { "중앙지원금", "동아리회비" };

	/* 학기에 따른 회계 리스트 조회 */
	@RequestMapping(value = "account")
	public String account(Model model, SemDate semdate, Principal principal, @RequestParam("club_id") int club_id) {

		UserDto user = userService.findByLoginId(principal.getName());
		ClubDto club = clubService.findById(club_id);

		System.out.println(semdate.getSem_name());
		if (semdate.getSem_name() == null) {
			Date now = Date.valueOf(LocalDate.now());
			String sem_name = semdateMapper.findByDate(now);
			System.out.println(sem_name);
		}
		String sem_name = semdate.getSem_name();
		List<AccountDto> accounts = accountService.findBySem(semdate);
		List<AccountDto> totals = accountService.getTotalByClubId(sem_name);
		SemDateDto startenddate = semdateService.findStartAndEndDate(sem_name);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String start_date = format.format(startenddate.getStart_date());
		String end_date = format.format(startenddate.getEnd_date());

		model.addAttribute("club_id", club_id);
		model.addAttribute("club", club);
		model.addAttribute("accounts", accounts);
		model.addAttribute("sems", semdateService.findAll());
		model.addAttribute("semdate", semdate);
		model.addAttribute("account_type", account_type);
		model.addAttribute("totals", totals);
		model.addAttribute("start_date", start_date);
		model.addAttribute("end_date", end_date);
		nav_list(model);
		nav_user(model, principal);
		return "club_admin/account";
	}

	/* 회계 내역 입력 */
	@RequestMapping(value = "account_save", method = RequestMethod.POST)
	public String account_save(Model model, @RequestParam("club_id") int club_id, @RequestParam("price") int[] price,
			@RequestParam("remark") String[] remark, @RequestBody MultipartFile[] file,
			@RequestParam("account_type") int[] account_type,
			@RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date[] date, SemDate semdate)
			throws IOException {
		String sem_name = semdate.getSem_name();
		save(club_id, price, remark, file, account_type, date, sem_name);
		return "redirect:account?club_id=" + club_id + "#fh5co-main";
	}

	/* 입력한 회계 내역 저장 트랜잭션 */
	@Transactional
	private void save(int club_id, int[] price, String[] remark, MultipartFile[] file, int[] account_type, Date[] date,
			String sem_name) throws IOException {
		System.out.println("실행시작");
		for (int i = 0; i < price.length; ++i) {
			AccountDto account = new AccountDto();
			account.setClub_id(club_id);
			account.setPrice(price[i]);
//			int total = accountService.getTotalByClubId(sem_name, club_id[i]);
			account.setTotal(0); // total culmn 사용안함
			account.setRemark(remark[i]);
			account.setAccount_type(account_type[i]);
			account.setDate(date[i]);
			if (!file[i].isEmpty()) {
				int f_id = fileService.accountFileUpload(file[i]);
				account.setFile_id(f_id);
			}
			accountService.insert(account);
		}
	}

	/* 첨부된 영수증 이미지 가져오기 */
	@RequestMapping(value = "getImage")
	public void getImage(HttpServletRequest req, HttpServletResponse res, @RequestParam("id") int id)
			throws IOException {
		res.setContentType("image/jpeg");
		FilesDto file = fileMapper.getReceiptImage(id);
		byte[] imagefile = file.getData();
		InputStream in1 = new ByteArrayInputStream(imagefile);
		IOUtils.copy(in1, res.getOutputStream());
	}

	/* 선택한 회계 내역 삭제 */
	@RequestMapping("delete")
	public String delete(Model model, @RequestParam("id") int id, @RequestParam("club_id") int club_id) {
		int f_id = accountMapper.findFileId(id);
		accountMapper.delete(id);
		fileMapper.delete(f_id);
		return "redirect:account?club_id=" + club_id + "#fh5co-main";
	}

	/* 저장된 회계 내역 다운로드 */
	@RequestMapping("account/excel/download")
	public void download(HttpServletResponse response, @RequestParam("club_id") int club_id,
			@RequestParam("sem_name") String sem_name) throws Exception {
		List<AccountDto> accounts = excelService.findByClubIdAndSem(club_id, sem_name);

		Workbook workbook = excelService.createXLS(accounts);

		Date now = Date.valueOf(LocalDate.now());
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String now_date = format.format(now);
		String club_name = clubService.findById(club_id).getClub_name();
		String fileName = URLEncoder.encode(now_date + " " + club_name + " " + sem_name + " 회계.xls", "UTF-8");

		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ";");
		try (BufferedOutputStream output = new BufferedOutputStream(response.getOutputStream())) {
			workbook.write(output);
		}
	}

	/*
	 * jyj_attendance 동아리 출석체크
	 */
	@RequestMapping("attendance")

	public String attendance(Model model, SemDate semdate, Principal principal, @RequestParam("club_id") int club_id) {

		// 로그인 한 유저 정보 추출
		UserDto user = userService.findByLoginId(principal.getName());
		ClubDto club = clubService.findById(club_id);

		// 현재 날짜에 맞는 현재 학기 추출
		Date now = Date.valueOf(LocalDate.now());
		int sem = attendanceService.findBySemId(now).getId();
		model.addAttribute("sem", sem);

		// 학기 리스트 추출 - 현재 학기 이후 값들은 제외
		List<SemDateDto> sems = semdateService.findAll();
		for (int i = 0; i < sems.size(); i++) {
			if (sems.get(i).getStart_date().compareTo(now) == 1)
				sems.remove(i);
		}
		model.addAttribute("sems", sems);

		// 처음 화면 실행시 최근 학기 데이터 불러오기
		int semId = semdate.getId();
		if (semId == 0) {
			semId = sem;
		}

		model.addAttribute("club_id", club_id);
		model.addAttribute("club", club);
		model.addAttribute("start", attendanceService.findBySemId(now).getStart_date());
		model.addAttribute("end", attendanceService.findBySemId(now).getEnd_date());
		model.addAttribute("semdate", semdate);
		model.addAttribute("semDate", semdateService.findAll());
		model.addAttribute("selectSemId", semId);
		model.addAttribute("findDate", attendanceService.findDate(semId, club_id));
		model.addAttribute("attendance", attendanceService.findBySemDate(semId, club_id));
		model.addAttribute("adminUser", attendanceService.findUser(semId, club_id));
		nav_list(model);
		nav_user(model, principal);
		return "club_admin/attendance";
	}

	/*
	 * 출석체크 값 불러오는 모달 데이터 구현 json 형식으로 데이터 생성하여 화면에 전달 모달 내 input checkbox 값으로 사용
	 */
	@ResponseBody
	@RequestMapping(value = "/update", method = RequestMethod.POST, produces = "application/json; charset=utf8")
	public String updateModal(@RequestParam("find") Date date, Model model, @RequestParam("club_id") int club_id)
			throws Exception {

		// 출석체크 수정 모달창 사용하기 위한 데이터 가공
		List<AttendanceDto> check = attendanceService.findByDate(date, club_id);

		JSONObject robj = new JSONObject();
		JSONArray jlist = new JSONArray();
		for (int i = 0; i < check.size(); i++) {
			JSONObject item = new JSONObject();
			item.put("name", "" + check.get(i).getName());
			item.put("check", "" + check.get(i).getCheck());
			item.put("id", "" + check.get(i).getId());
			jlist.put(item);
		}
		robj.put("testlist", jlist);

		return robj.toString();
	}

	/*
	 * 출석체크 값 수정 로직 구현 출석 체크 수정을 위해 해당 날짜값을 전달받아 allUpdate 처리 -> 체크한 해당 id값을 가져와
	 * update
	 */
	@RequestMapping(value = "attendance", method = RequestMethod.POST)
	public String attendanceUpdate(Model model, @RequestParam(value = "updateck", defaultValue = "0") int[] updateck,
			@RequestParam("date") String date, @RequestParam("club_id") int club_id) {

		// 날짜의 모든 동아리의 체크 값을-> check = 0
		attendanceService.allUpdate(date, club_id);

		// for문 안에 key만 update -> check = 1
		if (updateck[0] != 0) {
			for (int i = 0; i < updateck.length; i++) {
				attendanceService.update(updateck[i]);
			}
		}
		return "redirect:attendance?club_id=" + club_id + "#heading";
	}

	/*
	 * 출석체크 값 삽입 로직 구현
	 */
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String createModal(Model model, @RequestParam("date") Date date, @RequestParam("club_id") int club_id) {

		// 현재 학기 id값
		Date now = Date.valueOf(LocalDate.now());
		int sem = attendanceService.findBySemId(now).getId();

		List<AttendanceDto> attendance = attendanceService.findBySemDate(sem, club_id);

		if (attendance.size() == 0) {
			// 새로운 학기에 해당하는 경우 - 삽입
			attendanceService.dateNewAdmin(date, club_id);
		} else {
			// 현재 학기에 해당하는 경우 - 삽입
			attendanceService.dateNow(date, sem, club_id);
		}
		return "redirect:attendance?club_id=" + club_id + "#heading";
	}

	/*
	 * 출석체크 값 삭제 로직 구현
	 */
	@RequestMapping("attendance_delete")
	public String delete(Model model, @RequestParam("date") Date date, @RequestParam("club_id") int club_id) {
		attendanceService.delete(date, club_id);
		return "redirect:attendance?club_id=" + club_id + "#heading";
	}
}
