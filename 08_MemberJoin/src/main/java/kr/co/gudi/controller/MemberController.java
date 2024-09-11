package kr.co.gudi.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.gudi.service.MemberService;

@Controller
public class MemberController {

	Logger logger = LoggerFactory.getLogger(getClass());
	
	// 객체를 복사해서 공통영역(static)에 저장하는 방식을 사용.(싱글톤 패턴)
	// 쓸때마다 객체화하지 않기 위함.
	@Autowired MemberService service;
	
	@RequestMapping("/")
	public String loginForm() {
		return "login";
	}
	
	@RequestMapping("/joinForm")
	public String joinForm() {
		return "join";
	}
	
	@RequestMapping(value="join", method = RequestMethod.POST)
	public String join(@RequestParam Map<String, String> param, Model model) {
		logger.info("params : {}", param);
		// 위에서 Autowried를 사용했기 때문에 객체화할 필요가 없다!!
		String msg = service.join(param);
		model.addAttribute("result", msg);
		return "login";
	}
	
}
