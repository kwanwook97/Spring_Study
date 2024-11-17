package kr.co.gudi.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.gudi.service.MemberService;

@Controller
public class MemberController {
	@Autowired MemberService member_service;
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@RequestMapping(value= {"/","/login.go"})
	public String main() {
		return "login";
	}
	
	@PostMapping(value= "/login.ajax")
	@ResponseBody
	public Map<String, Object> login(String id, String pw,HttpSession session) {
		logger.info("컨트롤러가 받아온 값 : " + id + "," + pw);		
		Map<String, Object> result = new HashMap<String, Object>();

		if(member_service.login(id,pw)) {
			result.put("login", true);
			session.setAttribute("loginId", id);
		}else {
			result.put("login", false);
		}

		return result;
	}
	
	@RequestMapping(value= "/join.go")
	public String join() {
		return "join";
	}
	
	@PostMapping(value= "/join.do")
	public String join(@RequestParam Map<String, String> params,Model model) { //id,pw insert
		logger.info("컨트롤러가 받아온 값 : {}",params);
			String msg = "회원가입에 실패했습니다.";
			if(member_service.join(params)) {
				msg = "회원가입에 성공했습니다.";
			}
			model.addAttribute("result", msg);
		return "login";
	}	
	
	@GetMapping(value="/overlay.ajax")
	@ResponseBody
		public Map<String, Object>  overlay(String id){ //전달받은 id값
			logger.info("overlay check1:"+id);
			
			//map에 overlay : 중복되는 id 수 넣기
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("overlay",member_service.overlay(id));
			return map;
	}
	
	@GetMapping(value="/list.go")
	public String list() {
		return "list";
	}
	
	@GetMapping(value="/list.ajax")
	@ResponseBody
	public Map<String, Object>list(HttpSession session){		
		Map<String, Object> map = new HashMap<String, Object>();		
		if(session.getAttribute("loginId") !=null) {
			map.put("login", true);
			List<HashMap<String, Object>> list = member_service.list();
			map.put("list", list);
		}else {
			map.put("login", false);
		}				
		return map;
	}
	
	@RequestMapping(value="/insert.go")
	public String insert() {
		return "insert";
	}
	
	
	@PostMapping(value="/insert.ajax")
	@ResponseBody
	public Map<String, Object>insert(@RequestBody Map<String, Object> param){
		// 받을떄 파라메터는 @RequestBody 로 받아줘야 한다.
		logger.info("param : {}",param);
		
		String name = (String) param.get("name");
		String gender = (String) param.get("gender");
		String birth_date = (String) param.get("birth_date");
		String hire_date = (String) param.get("hire_date");
		logger.info("name :{}, gender:{}, birth_date:{}, hire_date:{}",name,gender,birth_date, hire_date);
		
		List<Map<String, String>> list = (List<Map<String, String>>) param.get("list");
		logger.info("경력사항================");
		for (Map<String, String> map : list) {
			logger.info("회사명 : "+map.get("co_name"));
			logger.info("입사일 : "+map.get("start_date"));
			logger.info("퇴사일 : "+map.get("end_date"));
			logger.info("업무 내용 : "+map.get("desc"));
			System.out.println();
		}
				
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("success", true);		
		return result;
	}
	
	
	
}

























