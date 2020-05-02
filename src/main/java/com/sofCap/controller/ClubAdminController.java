package com.sofCap.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
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
import org.springframework.web.multipart.MultipartFile;

import com.sofCap.dto.AccountDto;
import com.sofCap.dto.ClubDto;
import com.sofCap.dto.FilesDto;
import com.sofCap.dto.SemDateDto;
import com.sofCap.dto.UserClubDto;
import com.sofCap.dto.UserDto;
import com.sofCap.mapper.AccountMapper;
import com.sofCap.mapper.FileMapper;
import com.sofCap.mapper.SemDateMapper;
import com.sofCap.model.SemDate;
import com.sofCap.service.AccountService;
import com.sofCap.service.ClubService;
import com.sofCap.service.FileService;
import com.sofCap.service.SemDateService;
import com.sofCap.service.UserClubService;
import com.sofCap.service.UserService;

@Controller
@RequestMapping("club_admin")
public class ClubAdminController {

	@Autowired UserService userService;
	@Autowired UserClubService userClubService;
	@Autowired SemDateMapper semdateMapper;
	@Autowired AccountService accountService;
	@Autowired SemDateService semdateService;
	@Autowired ClubService clubService;
	@Autowired AccountMapper accountMapper;
	@Autowired FileMapper fileMapper;
	@Autowired FileService fileService;

	@RequestMapping("notice")
	public String club_notice(Model model, Principal principal) {
		return "club_admin/club_notice";
	}

	/*
	 * 지원자 합불 구현하기
	*/
	@GetMapping("acceptance")
	public String acceptane(Model model, Principal principal) {
		UserDto user = userService.findByLoginId(principal.getName());
		model.addAttribute("user",user);
		return "club_admin/acceptance";
	}

	@PostMapping(value="acceptance",params="cmd=yes") //id값은 지원자 id값
	public String acceptanceYes(Model model, Principal principal,
			@RequestParam("user_id") int user_id, @RequestParam("club_id") int club_id) {
		UserDto user = userService.findByLoginId(principal.getName());
		UserClubDto userClub = userClubService.findByUserId(user_id);
		model.addAttribute("user", user);
		model.addAttribute("userClub", userClub);
		userService.updateRole(user);
		userClubService.insert(userClub);
		return "redirect:acceptance";
	}

	@PostMapping(value="acceptance",params="cmd=no")
	public String acceptanceNo(Model model, Principal principal,
			@RequestParam("user_id") int user_id, @RequestParam("club_id") int club_id) {
		UserDto user = userService.findByLoginId(principal.getName());
		UserClubDto userClub = userClubService.findByUserId(user_id);
		model.addAttribute("user", user);
		model.addAttribute("userClub", userClub);
		userService.updateRole(user);
		userClubService.delete(user_id);
		return "redirect:acceptance";
	}

	/*
	 * LHM_account 동아리 회계
	 */
	String[] account_type = { "중앙지원금", "동아리회비" };

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
	public String account(Model model, SemDate semdate, Principal principal) {
		UserDto user = userService.findByLoginId(principal.getName());
		UserClubDto user_club = userClubService.findByUserId(user.getId());
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

		model.addAttribute("user_club_id", user_club_id);
		model.addAttribute("accounts", accounts);
		model.addAttribute("myClub", myClub);
		model.addAttribute("sems", semdateService.findAll());
		model.addAttribute("semdate", semdate);
		model.addAttribute("account_type", account_type);
		model.addAttribute("totals", totals);
		model.addAttribute("start_date", start_date);
		model.addAttribute("end_date", end_date);
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
		return "redirect:account";
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
	public String delete(Model model, @RequestParam("id") int id) {
		int f_id = accountMapper.findFileId(id);
		accountMapper.delete(id);
		fileMapper.delete(f_id);
		return "redirect:account";
	}
}
