package kr.co.gudi.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.gudi.service.BoardService;

@Controller
public class BoardController {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired BoardService ser;
	
	@RequestMapping("/")
	public String listgo() {
		return "list";
	}
	
	@RequestMapping("/list.ajax")
	@ResponseBody
	public Map<String, Object> list(String page,String cnt){		
		int page_ = Integer.parseInt(page);
		int cnt_ = Integer.parseInt(cnt);		
		return  ser.list(page_,cnt_);
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
