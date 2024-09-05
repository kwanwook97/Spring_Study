package kr.co.gudi.controller;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.gudi.service.JoinService;

@Controller
public class JoinController {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@RequestMapping(value="/")
	public String home() {
		return "join";
	}
	
	@RequestMapping(value="/join", method = RequestMethod.POST)
	public String join(Model model, String id, String pw, String name,
			String age,String gender,String email) {
		logger.info(id+"/"+pw+"/"+name+"/"+age+"/"+gender+"/"+email);
		String msg = "";
		
		JoinService service = new JoinService();
		try {
			msg = service.join(id,pw,name,age,gender,email);
		} catch (SQLException e) {
			msg = "회원가입중 예상치 못한 문제가 일어났습니다.";
			e.printStackTrace();
		}
		model.addAttribute("result", msg);
		return "result";
	}
	
	
	
	
	
			

}
