package com.sofCap.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.sofCap.dto.FilesDto;
import com.sofCap.dto.SemDateDto;
import com.sofCap.dto.UserDto;
import com.sofCap.mapper.AccountMapper;
import com.sofCap.mapper.BoardMapper;
import com.sofCap.mapper.FileMapper;
import com.sofCap.mapper.SemDateMapper;
import com.sofCap.mapper.UserMapper;
import com.sofCap.model.SemDate;
import com.sofCap.service.AccountService;
import com.sofCap.service.AttendanceService;
import com.sofCap.service.BoardService;
import com.sofCap.service.ClubService;
import com.sofCap.service.FileService;
import com.sofCap.service.SemDateService;
import com.sofCap.service.UserService;

@Controller
@RequestMapping("club_union")
public class ClubUnionController {

	@Autowired
	UserService userService;
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
	@Autowired SemDateMapper semdateMapper;

	/*
	 * jyj_attendance 동아리 연합회 출석체크
	 */
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
		model.addAttribute("lastSemUser", attendanceService.findAdmin(attendanceService.findLastSem().getId()));
		model.addAttribute("start", attendanceService.findLastSem().getStart_date());
		model.addAttribute("end", attendanceService.findLastSem().getEnd_date());
		model.addAttribute("semDate", semDate);
		model.addAttribute("selectSemId", semId);
		model.addAttribute("findDate", attendanceService.findDate(semId));
		model.addAttribute("attendance", attendanceService.findBySemDate(semId));
		model.addAttribute("adminUser", attendanceService.findAdmin(semId));

		return "club_union/attendance";
	}

	/*
	 * 출석체크 값 불러오는 모달 데이터 구현 json 형식으로 데이터 생성하여 화면에 전달 모달 내 input checkbox 값으로 사용
	 */
	@ResponseBody
	@RequestMapping(value = "/update", method = RequestMethod.POST, produces = "application/json; charset=utf8")
	public String updateModal(@RequestParam("find") Date date, Model model) throws Exception {

		// 출석체크 수정 모달창 사용하기 위한 데이터 가공
		List<AttendanceDto> check = attendanceService.findByDate(date);

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
		attendanceService.allUpdate(date);

		// for문 안에 key만 update -> check = 1
		if (updateck[0] != 0) {
			for (int i = 0; i < updateck.length; i++) {
				attendanceService.update(updateck[i]);
			}
		}
		return "redirect:/club_union/attendance";
	}

	/*
	 * 출석체크 값 삽입 로직 구현
	 */
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String createModal(Model model, @RequestParam("date") Date date) {

		// 마지막 학기 id값
		int lastSem = attendanceService.findLastSem().getId();

		// 현재 학기에 해당하는 경우 - 삽입
		attendanceService.dateNow(date, lastSem);
		return "redirect:/club_union/attendance";
	}

	/*
	 * 출석체크 값 삭제 로직 구현
	 */
	@RequestMapping("attendance_delete")
	public String delete(Model model, @RequestParam("date") Date date) {
		attendanceService.delete(date);
		return "redirect:attendance";
	}

	@RequestMapping("notice")
	public String union_notice(Model model, Principal principal) {
		UserDto user = userService.findByLoginId(principal.getName());
		List<BoardDto> boards = boardService.findAll_n();
		model.addAttribute("user", user);
		model.addAttribute("boards", boards);
		return "club_union/union_notice";
	}

	@RequestMapping("n_content")
	public String n_content(Model model, @RequestParam("id") int id) {
		BoardDto board = boardService.findOne(id);
		model.addAttribute("board", board);
		return "club_union/n_content";
	}

	@RequestMapping("n_delete")
	public String n_delete(Model model, @RequestParam("id") int id) {
		boardService.delete(id);
		return "redirect:notice";
	}

	@RequestMapping(value = "n_edit", method = RequestMethod.GET)
	public String n_edit(@RequestParam("id") int id, Model model, BoardDto board) {
		board.setBoard_name_id(3);
		board.setClub_id(1);
		board = boardService.findById(id);
		model.addAttribute("board", board);
		return "club_union/posting";
	}

	@Transactional
	@RequestMapping(value = "n_edit", method = RequestMethod.POST)
	public String n_edit(BoardDto board, Model model) {
		boardService.update(board);
		return "redirect:n_content?id=" + board.getId();
	}

	@RequestMapping(value = "n_create", method = RequestMethod.GET)
	public String n_create(Model model, BoardDto board) {
		board.setBoard_name_id(3);
		board.setClub_id(1);
		board = new BoardDto();
		model.addAttribute("board", board);
		return "club_union/posting";
	}

	@Transactional
	@RequestMapping(value = "n_create", method = RequestMethod.POST)
	public String n_create(BoardDto board, Model model) {
		board.setBoard_name_id(3);
		board.setClub_id(1);
		boardService.insert(board);
		return "redirect:n_content?id=" + board.getId();
	}

	@RequestMapping("minutes")
	public String union_minutes(Model model, Principal principal) {
		UserDto user = userService.findByLoginId(principal.getName());
		List<BoardDto> boards = boardService.findAll_m();
		model.addAttribute("user", user);
		model.addAttribute("boards", boards);
		return "club_union/union_minutes";
	}

	@RequestMapping("m_content")
	public String m_content(Model model, @RequestParam("id") int id) {
		BoardDto board = boardService.findOne(id);
		model.addAttribute("board", board);
		return "club_union/m_content";
	}

	@RequestMapping("m_delete")
	public String m_delete(Model model, @RequestParam("id") int id) {
		boardService.delete(id);
		return "redirect:minutes";
	}

	@RequestMapping(value = "m_edit", method = RequestMethod.GET)
	public String m_edit(@RequestParam("id") int id, Model model, BoardDto board) {
		board.setBoard_name_id(4);
		board.setClub_id(1);
		board = boardService.findById(id);
		model.addAttribute("board", board);
		return "club_union/posting";
	}

	@Transactional
	@RequestMapping(value = "m_edit", method = RequestMethod.POST)
	public String m_edit(BoardDto board, Model model) {
		boardService.update(board);
		return "redirect:m_content?id=" + board.getId();
	}

	@RequestMapping(value = "m_create", method = RequestMethod.GET)
	public String m_create(Model model, BoardDto board) {
		board.setBoard_name_id(4);
		board.setClub_id(1);
		board = new BoardDto();
		model.addAttribute("board", board);

		return "club_union/posting";
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
	public String account(Model model, SemDate semdate) {
		System.out.println(semdate.getSem_name());
		if(semdate.getSem_name()==null) {
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
		model.addAttribute("accounts", accounts);
		model.addAttribute("clubs", clubService.findAll());
		model.addAttribute("sems", semdateService.findAll());
		model.addAttribute("semdate", semdate);
		model.addAttribute("account_type", account_type);
		model.addAttribute("totals", totals);
		model.addAttribute("start_date", start_date);
		model.addAttribute("end_date", end_date);
		return "club_union/account";
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
}
