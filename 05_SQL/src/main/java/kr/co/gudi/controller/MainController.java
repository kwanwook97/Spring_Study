package kr.co.gudi.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.gudi.service.MainService;

@Controller
public class MainController {

	Logger logger = LoggerFactory.getLogger(getClass());
	
	// Controller를 실행하면, main페이지를 통해 DB에 접속하게된다.
	@RequestMapping(value="/")
	public String main() {
		logger.info("main page 요청");
		return "main";
	}
	
	
	@RequestMapping("/dbConnect")
	public String main(Model model) {
		
		logger.info("dbConnect");
		MainService service = new MainService();
		String msg = service.dbConnect();
		model.addAttribute("msg", msg);
		return "main";
	}
}
