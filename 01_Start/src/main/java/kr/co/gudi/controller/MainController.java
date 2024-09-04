package kr.co.gudi.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller // controller라는 표시
public class MainController {

	@RequestMapping(value="/") // "/"라는 요청이 오면 아래 메서드와 매핑시켜라
	public String home() {
		return "home"; // /views/home.jsp
	}
	
	// 자바는 jsp처럼 Request객체를 내장하고 있지 않기에 HttpServletRequest사용.
	@RequestMapping("/message")
	// Model은 forward를 이용해서 특정페이지로 이동시켜주는 녀석.
	public String message(HttpServletRequest req, Model model) {
		String param = req.getParameter("param");
		System.out.println("param : " + param);
		
		// 파라미터를 받아서 view에 보낸다.
		Object msg = "";
		if(param == null) {
			msg = "Invalid Type";
		}else if(param.equals("greeting")){
			msg = "안녕하세요";
		}else {
			msg = new Date();
		}
				
		model.addAttribute("msg", msg); //변수이름, 값		
		return "home";
	}
	
}
