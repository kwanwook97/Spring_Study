package kr.co.gudi.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import kr.co.gudi.dto.BoardDTO;
import kr.co.gudi.service.BoardService;

@Controller
public class BoardController {
	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired BoardService boardService;
	
	@RequestMapping(value="/")
	public String listform(Model model) {
		List<BoardDTO> list = boardService.list();
		logger.info("list: "+ list);
		model.addAttribute("list",list);
		return "list";
	}
	
	@RequestMapping(value="/list")
	public String list(Model model) {
		List<BoardDTO> list = boardService.list();
		logger.info("list: "+ list);
		model.addAttribute("list",list);
		return "list";
	}
	
	@RequestMapping(value="/writeForm")
	public String writeForm() {
		return "write";
	}
	
	@RequestMapping(value="/write")
	// 파일을 여러개 받기 위해서는 배열형식[]을 붙여서 받아주어야 한다.
	public String write(MultipartFile[] files, Model model, @RequestParam Map<String,String> param) {
		
		logger.info("file count : " + files.length);
		boardService.write(param, files);
		return "redirect:/list";
	}
	
	@RequestMapping(value="/detail")
	public String detail(String idx, Model model) {
		boardService.detail(idx, model);
		return "detail";
	}
	
	@RequestMapping(value="/del")
	public String del(String idx) {
		logger.info("delete idx : " + idx);
		boardService.del(idx);
		return "redirect:/list";
	}
	
	@RequestMapping("/download")
	public ResponseEntity<Resource> download(String new_filename, String ori_filename) {
		logger.info("download file : " + new_filename);
		logger.info("download file : " + ori_filename);
		return boardService.download(new_filename, ori_filename);
	}

	
	
}
