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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.sofCap.dto.AccountDto;
import com.sofCap.dto.AttendanceDto;
import com.sofCap.dto.BoardDto;
import com.sofCap.dto.ClubDto;
import com.sofCap.dto.FilesDto;
import com.sofCap.dto.SemDateDto;
import com.sofCap.dto.UserClubDto;
import com.sofCap.dto.UserDto;
import com.sofCap.mapper.AccountMapper;
import com.sofCap.mapper.BoardMapper;
import com.sofCap.mapper.ClubMapper;
import com.sofCap.mapper.FileMapper;
import com.sofCap.mapper.SemDateMapper;
import com.sofCap.mapper.UserClubMapper;
import com.sofCap.mapper.UserMapper;
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
@RequestMapping("club_union")
public class ClubUnionController {

	@Autowired
	UserService userService;
	@Autowired
	UserClubMapper userclubMapper;
	@Autowired
	ClubMapper clubMapper;
	@Autowired
	AttendanceService attendanceService;
	@Autowired
	BoardMapper boardMapper;
	@Autowired
	BoardService boardService;
	@Autowired
	AccountService accountService;
	@Autowired
	ClubService clubService;
	@Autowired
	SemDateService semdateService;
	@Autowired
	UserMapper userMapper;
	@Autowired
	FileMapper fileMapper;
	@Autowired
	AccountMapper accountMapper;
	@Autowired
	FileService fileService;
	@Autowired
	SemDateMapper semdateMapper;
	@Autowired
	UserClubService userClubService;
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
	 * jyj_attendance 동아리 연합회 출석체크
	 */
	@RequestMapping("attendance")
	public String attendance(Model model, SemDate semdate, Principal principal) {
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

		model.addAttribute("start", attendanceService.findBySemId(now).getStart_date());
		model.addAttribute("end", attendanceService.findBySemId(now).getEnd_date());
		model.addAttribute("semdate", semdate);
		model.addAttribute("semDate", semdateService.findAll());
		model.addAttribute("selectSemId", semId);
		model.addAttribute("findDate", attendanceService.findDate(semId, 1));
		model.addAttribute("attendance", attendanceService.findBySemDate(semId, 1));
		model.addAttribute("adminUser", attendanceService.findUser(semId, 1));
		nav_list(model);
		nav_user(model, principal);
		return "club_union/attendance";
	}

	/*
	 * 출석체크 값 불러오는 모달 데이터 구현 json 형식으로 데이터 생성하여 화면에 전달 모달 내 input checkbox 값으로 사용
	 */
	@ResponseBody
	@RequestMapping(value = "/update", method = RequestMethod.POST, produces = "application/json; charset=utf8")
	public String updateModal(@RequestParam("find") Date date, Model model) throws Exception {

		// 출석체크 수정 모달창 사용하기 위한 데이터 가공
		List<AttendanceDto> check = attendanceService.findByDate(date, 1);

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
			@RequestParam("date") String date) {

		// 날짜의 모든 동아리의 체크 값을-> check = 0
		attendanceService.allUpdate(date, 1);

		// for문 안에 key만 update -> check = 1
		if (updateck[0] != 0) {
			for (int i = 0; i < updateck.length; i++) {
				attendanceService.update(updateck[i]);
			}
		}
		return "redirect:/club_union/attendance#heading";
	}

	/*
	 * 출석체크 값 삽입 로직 구현
	 */
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String createModal(Model model, @RequestParam("date") Date date, Principal principal) {

		// 현재 학기 id값
		Date now = Date.valueOf(LocalDate.now());
		int sem = attendanceService.findBySemId(now).getId();

		// 현재 학기에 해당하는 경우 - 삽입
		attendanceService.dateNow(date, sem, 1);
		return "redirect:/club_union/attendance#heading";
	}

	/*
	 * 출석체크 값 삭제 로직 구현
	 */
	@RequestMapping("attendance_delete")
	public String delete(Model model, @RequestParam("date") Date date) {
		attendanceService.delete(date, 1);
		return "redirect:/club_union/attendance#heading";
	}

	/*
	 * ASY_board 동아리 연합회 공지사항
	 */
	@RequestMapping("notice")
	public String union_notice(Model model,Principal principal) {
		List<BoardDto> boards = boardService.findAll_n();
		model.addAttribute("boards", boards);
		nav_list(model);
		nav_user(model, principal);
		return "club_union/union_notice";
	}

	/* 해당 게시글로 이동 */
	@RequestMapping("n_content")
	public String n_content(Model model, @RequestParam("id") int id, Principal principal) {
		BoardDto board = boardService.findOne(id);
		model.addAttribute("board", board);
		nav_list(model);
		nav_user(model, principal);
		return "club_union/n_content";
	}

	/* 게시글 삭제 로직 구현 */
	@RequestMapping("n_delete")
	public String n_delete(Model model, @RequestParam("id") int id,
			Principal principal, HttpServletResponse response) throws IOException {
		UserDto user = userService.findByLoginId(principal.getName());
		boardService.delete(id);
		if (user.getUser_type().equals("동연")) {
			return "redirect:notice";
	      } else {
	         response.setContentType("text/html; charset=UTF-8");
	         PrintWriter out = response.getWriter();
	         out.println("<script>alert('접근이 제한된 사용자입니다.'); history.go(-1);</script>");
	         out.flush();
	         return "redirect:notice";
	      }
	}

	/* 게시글 수정 로직 구현 */
	@RequestMapping(value = "n_edit", method = RequestMethod.GET)
	public String n_edit(@RequestParam("id") int id, Model model, BoardDto board,
			Principal principal, HttpServletResponse response) throws IOException {
		UserDto user = userService.findByLoginId(principal.getName());
		board.setBoard_name_id(3);
		board.setClub_id(1);
		board = boardService.findById(id);
		model.addAttribute("board", board);
		nav_list(model);
		nav_user(model, principal);
		if (user.getUser_type().equals("동연")) {
			return "club_union/posting";
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
		return "redirect:n_content?id=" + board.getId();
	}

	/* 게시글 삽입 로직 구현 */
	@RequestMapping(value = "n_create", method = RequestMethod.GET)
	public String n_create(Model model, BoardDto board, Principal principal,
			HttpServletResponse response) throws IOException {
		UserDto user = userService.findByLoginId(principal.getName());
		board.setBoard_name_id(3);
		board.setClub_id(1);
		board = new BoardDto();
		model.addAttribute("board", board);
		nav_list(model);
		nav_user(model, principal);
		if (user.getUser_type().equals("동연")) {
			return "club_union/posting";
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
	public String n_create(BoardDto board, Model model) {
		board.setBoard_name_id(3);
		board.setClub_id(1);
		boardService.insert(board);
		return "redirect:n_content?id=" + board.getId();
	}

	/*
	 * ASY_board 동아리 연합회 회의록
	 */
	@RequestMapping("minutes")
	public String union_minutes(Model model, SemDate semdate, Principal principal) {
		if (semdate.getSem_name() == null) {
			Date now = Date.valueOf(LocalDate.now());
			String sem_name = semdateMapper.findByDate(now);
			System.out.println(sem_name);
		}
		String sem_name = semdate.getSem_name();
		List<BoardDto> boards = boardService.findBySem_m(semdate);
		SemDateDto startenddate = semdateService.findStartAndEndDate(sem_name);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String start_date = format.format(startenddate.getStart_date());
		String end_date = format.format(startenddate.getEnd_date());
		model.addAttribute("sems", semdateService.findAll());
		model.addAttribute("semdate", semdate);
		model.addAttribute("start_date", start_date);
		model.addAttribute("end_date", end_date);
		model.addAttribute("boards", boards);
		nav_list(model);
		nav_user(model, principal);
		return "club_union/union_minutes";
	}

	/* 해당 게시글로 이동 */
	@RequestMapping("m_content")
	public String m_content(Model model, @RequestParam("id") int id, Principal principal) {
		BoardDto board = boardService.findOne(id);
		model.addAttribute("board", board);
		nav_list(model);
		nav_user(model, principal);
		return "club_union/m_content";
	}

	/* 게시글 삭제 로직 구현 */
	@RequestMapping("m_delete")
	public String m_delete(Model model, @RequestParam("id") int id,
			Principal principal, HttpServletResponse response) throws IOException {
		UserDto user = userService.findByLoginId(principal.getName());
		boardService.delete(id);
		if (user.getUser_type().equals("동연")) {
			return "redirect:minutes";
	      } else {
	         response.setContentType("text/html; charset=UTF-8");
	         PrintWriter out = response.getWriter();
	         out.println("<script>alert('접근이 제한된 사용자입니다.'); history.go(-1);</script>");
	         out.flush();
	         return "redirect:minutes";
	      }
	}

	/* 게시글 수정 로직 구현 */
	@RequestMapping(value = "m_edit", method = RequestMethod.GET)
	public String m_edit(@RequestParam("id") int id, Model model, BoardDto board,
			Principal principal, HttpServletResponse response) throws IOException {
		UserDto user = userService.findByLoginId(principal.getName());
		board.setBoard_name_id(4);
		board.setClub_id(1);
		board = boardService.findById(id);
		model.addAttribute("board", board);
		nav_list(model);
		nav_user(model, principal);
		if (user.getUser_type().equals("동연")) {
			return "club_union/posting";
	      } else {
	         response.setContentType("text/html; charset=UTF-8");
	         PrintWriter out = response.getWriter();
	         out.println("<script>alert('접근이 제한된 사용자입니다.'); history.go(-1);</script>");
	         out.flush();
	         return "redirect:m_content";
	      }
	}

	@Transactional
	@RequestMapping(value = "m_edit", method = RequestMethod.POST)
	public String m_edit(BoardDto board, Model model) {
		boardService.update(board);
		return "redirect:m_content?id=" + board.getId();
	}

	/* 게시글 삽입 로직 구현 */
	@RequestMapping(value = "m_create", method = RequestMethod.GET)
	public String m_create(Model model, BoardDto board,
			Principal principal, HttpServletResponse response) throws IOException {
		UserDto user = userService.findByLoginId(principal.getName());
		board.setBoard_name_id(4);
		board.setClub_id(1);
		board = new BoardDto();
		model.addAttribute("board", board);
		nav_list(model);
		nav_user(model, principal);
		if (user.getUser_type().equals("동연")) {
			return "club_union/posting";
	      } else {
	         response.setContentType("text/html; charset=UTF-8");
	         PrintWriter out = response.getWriter();
	         out.println("<script>alert('접근이 제한된 사용자입니다.'); history.go(-1);</script>");
	         out.flush();
	         return "redirect:m_content";
	      }
	}

	@Transactional
	@RequestMapping(value = "m_create", method = RequestMethod.POST)
	public String m_create(BoardDto board, Model model) {
		board.setBoard_name_id(4);
		board.setClub_id(1);
		boardService.insert(board);
		return "redirect:m_content?id=" + board.getId();
	}

	@RequestMapping("club_list")
	public String list(Model model,Principal principal) {
		System.out.println(principal.getName());
		if (!principal.getName().equals("clubAs"))
			return "redirect:notice";
		List<UserDto> users = userMapper.findAll();
		model.addAttribute("users", users);
		nav_list(model);
		nav_user(model, principal);
		return "club_union/club_manage";
	}

	@RequestMapping(value = "club_create", method = RequestMethod.GET)
	public String create(Model model, Principal principal) {
		UserDto user = new UserDto();
		model.addAttribute("user", user);
		nav_list(model);
		nav_user(model, principal);
		return "club_union/club_create";
	}

	@RequestMapping(value = "club_create", method = RequestMethod.POST)
	public String create(Model model, UserDto user, Principal principal) {
		if (!principal.getName().equals("clubAs"))
			return "redirect:notice";
		// 1. 클럽 생성
		ClubDto club = new ClubDto();
		club.setClub_name(user.getName());
		club.setClub_type(1);
		clubMapper.insert(club);
		// 2. 유저 생성
		userMapper.insert(user);
		// 3. 유저 클럽 이어주기
		UserClubDto user_club = new UserClubDto();
		club = clubMapper.findByName(user.getName());
		user_club.setUser_id(user.getId());
		user_club.setClub_id(club.getId());
		userclubMapper.insert(user.getId(),club.getId());
		return "redirect:club_list";
	}

	@RequestMapping(value = "club_edit", method = RequestMethod.GET)
	public String edit(Model model, @RequestParam("id") int id, Principal principal) {
		UserDto user = userMapper.findOne(id);
		model.addAttribute("user", user);
		nav_list(model);
		nav_user(model, principal);
		return "club_union/club_update";
	}

	@RequestMapping(value = "club_edit", method = RequestMethod.POST)
	public String edit(Model model, UserDto user) {
		userMapper.update(user);
		return "redirect:club_list";
	}

	@RequestMapping("club_delete")
	public String delete(Model model, @RequestParam("user_id") int user_id, Principal principal) {
		if (!principal.getName().equals("clubAs"))
			return "redirect:notice";
		System.out.println(user_id);
		int club_id = userclubMapper.findByUserId(user_id).get(0).getClub_id(); //수정필요
		String name = clubMapper.findById(club_id).getClub_name();
		userclubMapper.delete(club_id);
		clubMapper.delete(name);
		userMapper.delete(user_id);
		return "redirect:club_list";
	}

	/*
	 * LHM_account 동아리 연합회 회계
	 */
	String[] account_type = { "중앙지원금", "동아리회비" };

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
	/* 학기에 따른 회계 리스트 조회 */
	@RequestMapping(value = "account")
	public String account(Model model, SemDate semdate, Principal principal, HttpServletResponse response) throws IOException {
		UserDto user = userService.findByLoginId(principal.getName());
		UserClubDto user_club = userClubService.findByUserId(user.getId()).get(0); //동아리 관리자는 하나의 club에만 소속되어있기 때문에 get(0)해도 됨
		int user_club_id = user_club.getClub_id();
		ClubDto myClub = clubService.findById(user_club_id);
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
//		System.out.println(format.format(startenddate.getStart_date()));
//		System.out.println(format.format(startenddate.getEnd_date()));
		model.addAttribute("myClub", myClub);
		model.addAttribute("accounts", accounts);
		model.addAttribute("clubs", clubService.findAll());
		model.addAttribute("sems", semdateService.findAll());
		model.addAttribute("semdate", semdate);
		model.addAttribute("account_type", account_type);
		model.addAttribute("totals", totals);
		model.addAttribute("start_date", start_date);
		model.addAttribute("end_date", end_date);
		nav_list(model);
		nav_user(model, principal);
		
		if (user.getUser_type().equals("동아리관리자") || user.getUser_type().equals("동연")) {
			return "club_union/account";
		} else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('접근이 제한된 사용자입니다.'); history.go(-1);</script>");
			out.flush();
			return " ";
		}
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
		return "redirect:account#fh5co-tab-feature-center" + club_id;
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
		return "redirect:account#fh5co-tab-feature-center" + club_id;
	}
	
	/* 저장된 회계 내역 다운로드 */
	@RequestMapping("account/excel/downloadByClub")
	public void downloadByClub(HttpServletResponse response, @RequestParam("club_id") int club_id,
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
	
	/* 저장된 회계 내역 다운로드 */
	@RequestMapping("account/excel/downloadAll")
	public void downloadAll(HttpServletResponse response,@RequestParam("sem_name") String sem_name) throws Exception {
		List<AccountDto> accounts = excelService.findBySem(sem_name);

		Workbook workbook = excelService.createAllClubXLS(accounts);

		Date now = Date.valueOf(LocalDate.now());
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String now_date = format.format(now);
		String club_name = "전체 동아리";
		String fileName = URLEncoder.encode(now_date + " " + club_name + " " + sem_name + " 회계.xls", "UTF-8");

		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ";");
		try (BufferedOutputStream output = new BufferedOutputStream(response.getOutputStream())) {
			workbook.write(output);
		}
	}
}

