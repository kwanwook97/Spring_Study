package kr.co.gudi.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
	
	// getClass()는 현재의 클래스(HomeController)를 의미한다.
	// 앞으로는... Controller를 만들때 Logger를 생성해준다.
	Logger logger = LoggerFactory.getLogger(getClass());	
	
	@RequestMapping("/")
	public String home() {
		// Controller와 Model을 여러개 생성하게 되면...
		// 로그가 언제 찍혔는지가 중요하다. 
		// 따라서, System.out.println를 Logger로 대체한다.
		logger.info("home.jsp 요청");
		return "home";
	}
	
	
	// Post 메소드만 받겠다.
	// 이때 맞지 않는 메서드(get등...)로 요청을 보내오면 405에러가 나타난다.
	@RequestMapping(value = "/calc", method = RequestMethod.POST)
	public String calc(Model model, String su1, String su2, String oper) {
		
		logger.info(su1 + oper + su2);
		int isu1 = Integer.parseInt(su1);
		int isu2 = Integer.parseInt(su2);
		int result = 0;
		
		switch(oper) {
		case "+" :
			result = isu1 + isu2;
			break;
		case "-" :
			result = isu1 - isu2;
			break;
		case "*" :
			result = isu1 * isu2;
			break;
		case "/" :
			result = isu1 / isu2;
			break;
		}
		
		model.addAttribute("result", result);		
		return "result";		
	}	
}
