package com.sofCap.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("club_admin")
public class ClubAdminController {

	@RequestMapping("notice")
	public String club_notice(Model model, Principal principal) {
		return "club_admin/club_notice";
	}

	@RequestMapping("acceptance")
	public String acceptane() {
		return "club_admin/acceptance";
	}
}
