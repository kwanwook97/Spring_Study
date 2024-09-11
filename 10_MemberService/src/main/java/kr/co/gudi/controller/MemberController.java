package kr.co.gudi.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.gudi.dto.MemberDTO;
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
		
		return "login";
	}
	
	@RequestMapping(value="/login", method = RequestMethod.POST)
	public String login(String id, String pw, Model model, HttpSession session) {
		String page = "login";
		
		if(service.login(id, pw)) {
			page = "redirect:/list";
			session.setAttribute("loginId", id);
		}else {
			model.addAttribute("result", "아이디 또는 비밀번호를 확인하세요.");
		}
		
		return page; 
	}
	
	@RequestMapping("/list")
	public String list(Model model) {
		
		List<MemberDTO> dto = service.list();
		model.addAttribute("list", dto);
		
		return "list";
	}
	
	
	 @RequestMapping(value="/detail") 
	 public String detail(String id, Model model) {
		 String page = "redirect:/list";
		 MemberDTO dto = service.detail(id);
		
		 if(dto != null) {
			 page = "detail";
			 model.addAttribute("info", dto);
		 }
		 
		 return page; 
	 }
	 
	 @RequestMapping(value="/del") 
	 public String del(String id) {
		 
		 service.del(id);
		 
		 return "redirect:/list";
	 }
	 
	
	
}
