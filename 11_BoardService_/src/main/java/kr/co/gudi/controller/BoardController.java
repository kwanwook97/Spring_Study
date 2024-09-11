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

import kr.co.gudi.dto.BoardDTO;
import kr.co.gudi.service.BoardService;

@Controller
public class BoardController {

	Logger logger = LoggerFactory.getLogger(getClass());
	
	// DI(의존성 주입) - 느슨한 결합유지 -> 결합도를 낮춰준다.
	// 결합도가 높으면? - 무언가 하나를 바꿀 때 바꿔야 할 소스가 많아진다.
	// (@Service, @Autowired)를 통해 Bean에 등록해놓으면...
	// 빈 부분만 수정하면 된다!!
	@Autowired BoardService board_service;
	
	@RequestMapping("/list")
	public String list(Model model, HttpSession session) {
		
		List<BoardDTO> dto = board_service.list();
		model.addAttribute("list", dto);
		
		// 세션이 없다면... login페이지로 팅기도록 한다.
		String page = "login";
		
		if (session.getAttribute("loginId") == null) {
			model.addAttribute("result", "로그인이 필요한 서비스입니다.");
		}else {
			page = "list";
		}
		
		return page;
	}
	
	@RequestMapping("/detail")
	public String detail(int idx, Model model, HttpSession session) {
		
		String page = "login";
		
		if(session.getAttribute("loginId") == null) {
			model.addAttribute("result", "로그인이 필요한 서비스입니다.");
		}else{
			BoardDTO board = null;
			page = "redirect:/list";
			board = board_service.detail(idx);
			if(board != null) {
				page = "detail";
				model.addAttribute("info", board);
			}
		}
		
		return page;
	}
	
	@RequestMapping("/del")
	public String del(int idx) {
		
		board_service.del(idx);
		return "redirect:/list";
	}
	
	@RequestMapping("/writeForm")
	public String writeForm() {
		
		return "write";
	}

	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String write(Model model, HttpSession session,
			@RequestParam Map<String, String> param) {
		
		logger.info("param : {}", param);
		String page = "login";
		
		if(session.getAttribute("loginId") == null) {
			model.addAttribute("result", "로그인이 필요한 서비스입니다.");
		}else {
			page = "redirect:/list";
			board_service.write(param);
		}
		return page;
	}
	
	@RequestMapping("/updateForm")
	public String updateForm(Model model, int idx) {
		
		BoardDTO board = board_service.updateForm(idx); 
		model.addAttribute("info", board);
		
		return "update";
	}
	
	@RequestMapping("/update")
	public String update(@RequestParam Map<String, String> param) {
		
		logger.info("param : {}", param);

		board_service.update(param);
		
		return "redirect:/detail?idx=" + param.get("idx");
	}
	
}
