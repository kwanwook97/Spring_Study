package kr.co.gudi.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.gudi.dto.MemberDTO;
import kr.co.gudi.service.MemberService;

@Controller
public class MemberController {

	Logger logger = LoggerFactory.getLogger(getClass());
	
	@RequestMapping("/")
	public String main() {
		return "login";
	}
	
	// 회원가입
	@RequestMapping("/joinForm")
	public String joinForm() {
		return "join";
	}
	
	@RequestMapping("/login")
	public String login(String id, String pw, HttpSession session) {
		String page = "login";
		String msg = "";				
		
		MemberService service = new MemberService();
		try {
			// true이면...
			if(service.login(id, pw)) {
				msg = "로그인에 성공했습니다.";
				session.setAttribute("loginId", id);
				
				page = "redirect:/list";				
			}else{
				msg = "로그인에 실패했습니다.";				
			};
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		
		return page;
	}
	
	@RequestMapping(value="/join", method = RequestMethod.POST)
	public String join(@RequestParam Map<String, String> param, Model model) {		
		
		MemberService service = new MemberService();
		String msg = "";
		
		try {
			msg = service.join(param);			
		} catch (Exception e) {
			msg = "올바르지 않은 데이터입니다."; 
			e.printStackTrace();
		}
				
		model.addAttribute("msg", msg);
		return "login";
	}
	
	@RequestMapping("/list")
	public String list(Model model) {
		
		MemberService service = new MemberService();
		List<MemberDTO> list;
		
		try {
			list = service.list();
			model.addAttribute("list", list);
		} catch (Exception e) {
			e.printStackTrace();
		}		
		
		return "list"; 
	}
	
	@RequestMapping("/detail")
	public String detail(Model model, String id) {
		
		MemberService service = new MemberService();
		List<MemberDTO> info = service.detail(id);
		
		model.addAttribute("info", info);
		return "detail";
	}
	
	@RequestMapping("/del")
	public String del(Model model, String id) {
		
		MemberService service = new MemberService();
		service.del(id);
		
		return "redirect:/list";
	}
	
	@RequestMapping("/logout")
	public String logout(Model model, HttpSession session) {
		// login된 id의 session값을 제거한다.
		session.removeAttribute("loginId");		
		model.addAttribute("result", "로그아웃 되었습니다.");
		return "login";
	}
	
	
}
