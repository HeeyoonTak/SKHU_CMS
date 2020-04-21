package com.sofCap.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("club_admin")
public class ClubAdminController {

	@RequestMapping("notice")
	public String club_notice() {
		return "club_admin/club_notice";
	}
}
