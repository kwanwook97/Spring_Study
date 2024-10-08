package kr.co.gudi.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import kr.co.gudi.service.BoardService;
import kr.co.gudi.vo.BoardVO;

@Controller
public class BoardController {

	@Autowired BoardService board_service;
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@RequestMapping(value = "/list.do")
	public String list(HttpSession session, Model model) {
		String page = "login";
		
		if (session.getAttribute("loginId") != null) {
			List<BoardVO> list = board_service.list();
			model.addAttribute("list", list);
			page = "list";
		}else {
			model.addAttribute("result", "로그인이 필요한 서비스입니다.");
		}
		
		return page;
	}
	
	@RequestMapping(value = "/write.go")
	public String write(HttpSession session, Model model) {
		String page = "login";
		
		if (session.getAttribute("loginId") != null) {
			page = "write";
		}else {
			model.addAttribute("result", "로그인이 필요한 서비스입니다.");
		}
		
		return page;
		
	}
	
	
	@RequestMapping(value = "/write.do", method = RequestMethod.POST)
	public String write(MultipartFile[] files,@RequestParam Map<String, String> params, HttpSession session, Model model) {
		String page = "login";
		
		
		if (session.getAttribute("loginId") != null) {
			// list가 아니라... detail로 이동하도록 만들기 위함..
			page = board_service.write(files, params);
		}else {
			model.addAttribute("result", "로그인이 필요한 서비스입니다.");
		}
		
		return page;
	}
	
	@RequestMapping(value = "/detail.do")
	public String detail(Model model, HttpSession session, String idx) {
		String page = "login";
		logger.info("idx=" + idx);
		
		if (session.getAttribute("loginId") != null) {
			board_service.detail(idx, model);
			page = "detail";
		}else {
			model.addAttribute("result", "로그인이 필요한 서비스입니다.");
		}
		
		return page;
	}
	
	@RequestMapping(value = "/del.do")
	public String del(String idx, HttpSession session, Model model) {
		logger.info("idx= " + idx);
		String page = "login";
		
		if (session.getAttribute("loginId") != null) {
			board_service.del(idx);
			page = "redirect:/list.do";
		}else {
			model.addAttribute("result", "로그인이 필요한 서비스입니다.");
		}
		
		return page;
	}
	
	@RequestMapping(value="/download.do")
	public ResponseEntity<Resource> download(String new_filename, String ori_filename) {		
		
		return board_service.download(new_filename, ori_filename);
	}
	
	
	
}
