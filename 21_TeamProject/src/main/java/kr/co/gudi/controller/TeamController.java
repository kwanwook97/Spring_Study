package kr.co.gudi.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.gudi.service.TeamService;

@Controller
public class TeamController {

	@Autowired TeamService team_service;
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@RequestMapping(value = "/")
	public String home() {
		return "list";
	}
	
	@GetMapping(value = "/list.ajax")
	@ResponseBody
	public Map<String, Object> list(){
		boolean success = false;
		
		Map<String, Object> list = new HashMap<String, Object>();
		
		list.put("list", team_service.list());
		
		return list;
	}
	
	@RequestMapping(value = "/update/{col}/{val}/{no}.ajax")
	public String update(@PathVariable Map<String, String> params) {
		logger.info("params : " + params);
		team_service.update(params);
		
		return "redirect:/";
	}
	
}
