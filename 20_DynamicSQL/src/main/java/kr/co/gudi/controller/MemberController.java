package kr.co.gudi.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.gudi.dto.MemberDTO;
import kr.co.gudi.service.MemberService;

@Controller
public class MemberController {
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired MemberService memberService;
	
	
	@RequestMapping(value = {"/", "/list.do"})
	public String home(Model model, @RequestParam Map<String, String> param) {
		
		logger.info("param : {}", param);
		List<MemberDTO> list = memberService.list(param);
		model.addAttribute("list", list);
		
		return "list";
	}
	
	@RequestMapping(value = "/join.go")
	public String join() {
		
		return "join";
	}
	
	// 파라미터를 DTO로 받고 싶다면....
	// 1. post로 받아야한다면
	// 2. 보내오는 데이터 타입과, 받는 데이터 타입이 일치해야한다. (그래서 age를 String으로)
	@PostMapping(value = "/join.do")
	public String join(MemberDTO dto, Model model) {
		
		return memberService.join(dto, model);
	}
	
	@PostMapping(value = "/multi.do")
	public String multi(Model model, 
			@RequestParam List<String> userName) {
		
		logger.info("userName : " + userName);
		memberService.multi(userName, model);
		
		return "list";
	}
	
	@RequestMapping(value = "/detail.do")
	public String detail(String id, Model model) {
		
		MemberDTO dto = memberService.detail(id);
		
		model.addAttribute("info", dto);
		
		return "detail";
	}
	
	@PostMapping(value = "/update.ajax")
	@ResponseBody
	public Map<String, Object> update(@RequestParam Map<String, String> params){
		
		logger.info("params : {}", params);
		Map<String, Object> result = memberService.update(params);
		
		return result;
	}
}
