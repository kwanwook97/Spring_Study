package kr.co.gudi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

	@RequestMapping(value="/")
	public String home(Model model) {		
		String msg = "홈 페이지에 어서오세요.";
		model.addAttribute("msg", msg);					
		return "home";
	}
	
	@RequestMapping(value="/main")
	public String main(Model model) {		
		String msg = "메인 페이지에 어서오세요.";
		model.addAttribute("msg", msg);					
		return "main";
	}
	
	@RequestMapping(value="/index")
	public String index(Model model) {	
		String msg = "인덱스 페이지에 어서오세요.";
		model.addAttribute("msg", msg);					
		return "index";
	}	
	
}
