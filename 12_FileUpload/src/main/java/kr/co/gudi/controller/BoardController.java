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
	
	@Autowired BoardService board_service;
	Logger logger = LoggerFactory.getLogger(getClass());

	@RequestMapping("/")	
	public String home() {
		
		return "index";
	}
	
	@RequestMapping("/list")
	public String list(Model model) {
		
		logger.info("");
//		session.setAttribute("loginId", "admin");
		
		List<BoardDTO> dto = board_service.list();
		model.addAttribute("list", dto); 
		
		return "list";
	}
	
	
	@RequestMapping("/writeForm")
	public String writeForm() {
		return "write";
	}
	
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String write(Model model,  
			@RequestParam Map<String, String> param) {
		
		logger.info("param : {}", param);

		String page = "write";
		
		// 글쓰기에 성공하면 list페이지로 이동!
		if(board_service.write(param)) {
			page = "redirect:/list";
		}
		
		return page;
	}
	
	@RequestMapping("/detail")
	public String detail(Model model, int idx) {
		String page = "redirect:/list";
	
		BoardDTO dto = board_service.detail(idx);
		
		if(dto != null) {
			page = "detail";
			model.addAttribute("info", dto);
		}
		
		return page;
	}
	
	@RequestMapping("/del")
	public String del(int idx) {
		
		board_service.del(idx);
		return "redirect:/list";
	}
	
	@RequestMapping("/updateForm")
	public String updateForm(Model model, int idx) {
		
		BoardDTO board = board_service.updateForm(idx); 
		model.addAttribute("info", board);
		
		return "update";
	}
	
	@RequestMapping("/update")
	public String update(@RequestParam Map<String, String> param) {
		
		board_service.update(param);
		return "redirect:/list";
	}
}
