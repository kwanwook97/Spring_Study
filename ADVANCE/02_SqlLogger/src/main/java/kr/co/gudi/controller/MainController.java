package kr.co.gudi.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import kr.co.gudi.dto.MainDTO;
import kr.co.gudi.service.MainService;

@RestController
public class MainController {

	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired MainService mainService;
	
	@RequestMapping(value="/")
	public ModelAndView main() {	
		// ModelAndView : model 처럼 데이터를 넣을수도 있고, View 도 보낼수 있는 클래스객체
		// Model 과 다르게 사용하고 싶은 곳에서 객체화 하여 사용하면 된다.
		ModelAndView mav = new ModelAndView();
		mav.setViewName("list");		
		return mav;
		// return "list"; // <- list 라는 뷰를 찾는게 아니라 list 라는 문자열을 ResponseBody 로 보내게 된다.
	}
	
	
	@RequestMapping(value="/list")
	public Map<String,Object> list(){		
		List<MainDTO> list = mainService.list();		
		//logger.info("리스트 : "+list);		
		Map<String, Object> result = new HashMap<String, Object>();		
		result.put("list", list);		
		return result;
	}
	
	
	@GetMapping("/write.go")
	public ModelAndView write() {
		return new ModelAndView("write");
	}
	
	@PostMapping(value="/write.do")
	public ModelAndView write(@RequestParam Map<String, String> param) {
		logger.info("param : {}",param);
		mainService.write(param);
		return new ModelAndView("redirect:/");
	}
	
	
	
	
}


























