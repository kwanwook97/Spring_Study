package kr.co.gudi.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.gudi.dao.MemberService;
import kr.co.gudi.dto.MemberDTO;

@Controller
public class MemberController {
	
	Logger logger = LoggerFactory.getLogger(getClass());

	
	@RequestMapping(value="/")
	public String loginForm() {
		return "login";
	}
	
	@RequestMapping(value="/joinForm")
	public String joinForm() {
		return "join";
	}
	
	@RequestMapping(value="/join", method = RequestMethod.POST)
	public String join(Model model, 
			@RequestParam Map<String,String> param) {
		logger.info("param : {}",param);
		
		MemberService service = new MemberService();
		String msg = "";
		try {
			msg = service.join(param);
		} catch (SQLException e) {
			msg = "회원가입중 문제가 발생 했습니다.";
			e.printStackTrace();
		}
		model.addAttribute("result", msg);
		
		return "login";
	}
	
	@RequestMapping(value="/login")
	public String login(String id, String pw, Model model, HttpSession session) {
		
		String page = "login"; // 성공/실패에 따라서 가야할 페이지가 달라질 경우...
		logger.info(id+"/"+pw);		
		MemberService service = new MemberService();
		
		// 회원가입된 id로 로그인한 경우에만 list페이지로 이동. 
		if(service.login(id,pw)) {
			// session체크(페이지에서 세션을 요청하고, 세션이 없다면 redirect로 튕겨주는 것.)
			session.setAttribute("loginId", id);
			
			page = "redirect:/list";//sendRedirect 를 사용(특정 controller 호출)
		}else {
			model.addAttribute("result", "아이디 또는 비밀번호를 확인 하세요!");
		}
		
		return page;//jsp 페이지로 보낼때
	}
	
	@RequestMapping(value="/list")
	public String list(Model model) {
		logger.info("list 요청!!!!!");
		MemberService service = new MemberService();
		List<MemberDTO> list = service.list();
		model.addAttribute("list", list);
		
		return "list";
	}
	
	// 삭제에 성공하면, list로 보낸다.
	@RequestMapping("/del")
	public String del(String id) {
		logger.info("삭제할 아이디 : " + id);
		MemberService service = new MemberService();
		service.del(id);
		
		return "redirect:/list";
	}
	
	
	@RequestMapping("/detail")
	public String detail(String id, Model model) {
		String page = "redirect:/list";
		logger.info("상세보기 할 ID : " + id);
		
		MemberService service = new MemberService();
		// SELECT * FROM member WHERE id = ?
		MemberDTO dto = service.detail(id);
		
		if(dto != null) {
			page = "detail"; 
			model.addAttribute("info", dto);
		}				
		
		return page;
	}
	
	@RequestMapping("/logout")
	public String logout(Model model, HttpSession session) {
		// login된 id의 session값을 제거한다.
		session.removeAttribute("loginId");		
		model.addAttribute("result", "로그아웃 되었습니다.");
		return "login";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
