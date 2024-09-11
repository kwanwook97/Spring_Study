package kr.co.gudi.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.gudi.service.MemberService;

@Controller
public class MemberController {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired MemberService service;
	
	@RequestMapping("/")
	public String loginForm() {
		return "login";
	}
	
	@RequestMapping("/joinForm")
	public String joinForm() {
		return "join";
	}
	
	@RequestMapping("/join")
	public String join(@RequestParam Map<String, String> param, Model model) {
		String msg = service.join(param);
		
		model.addAttribute("msg", msg);
		return "login";
	}
	
	@RequestMapping("/login")
	public String login(String id, String pw, Model model, HttpSession session) {
		// 게시판 페이지로 이동
		String page = "login";
		
		if(service.login(id, pw)) {
			page = "redirect:/notice";
			session.setAttribute("loginId", id);
		}else {
			model.addAttribute("result", "로그인 실패");
		}

		return page;
	}	
	
}
