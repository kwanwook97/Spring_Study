package kr.co.gudi.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.gudi.dto.MemberDTO;
import kr.co.gudi.service.MemberService;

@Controller
public class MemberController {

	Logger logger = LoggerFactory.getLogger(getClass());
	
	private final MemberService memberService;
	
	// 생성자 주입 활용.
	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}

	@RequestMapping(value = {"/", "/login.go"})
	public String login() {
		return "login";
	}
	
	@RequestMapping(value = "/login.do")
	public String memberLogin(HttpSession session, Model model, String id, String pw) {
		String page = "login";
		
		if(memberService.login(id, pw)) {
			model.addAttribute("msg", "로그인 성공!");
			session.setAttribute("loginId", id);
			page = "list";
		}else {
			model.addAttribute("msg", "로그인 실패!");
		}
		
		return page;
	}
	
	@RequestMapping(value = "/join.go")
	public String join() {
		return "join";
	}
	
	@RequestMapping(value = "/join.do")
	public String memberJoin(Model model, @RequestParam Map<String, String> param) {
		String page = "redirect:/login.go";
		
		logger.info("param : {}", param);
		
		int row = memberService.join(param);
		
		if(row > 0) {
			model.addAttribute("msg", "회원가입에 성공!");
		}else {
			model.addAttribute("msg", "회원가입에 실패!");
		}
		
		return page;
	}
	
}
