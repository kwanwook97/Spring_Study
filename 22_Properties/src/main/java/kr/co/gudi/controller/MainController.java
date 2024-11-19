package kr.co.gudi.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.gudi.service.MainService;

@Controller
public class MainController {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired MainService mainService;

	// application.properties에서 가져올 설정.
	@Value("${upload.path}") String path;
	@Value("${spring.application.name}") String name;
	@Value("${spring.mvc.view.prefix}") String prefix;
	@Value("${spring.mvc.view.suffix}") String suffix;
	
	@RequestMapping(value = "/")
	public String main() {
		logger.info("upload path : " + path);
		logger.info("upload path : " + name);
		logger.info("upload path : " + prefix);
		logger.info("upload path : " + suffix);
		return "main";
	}
	
	@PostMapping(value = "/login.do")
	public String login(HttpServletRequest req, Model model) {
		
		String page = "main";
		String msg = "아이디 또는 비밀번호를 확인하세요.";

		String id = req.getParameter("id");
		String pw = req.getParameter("pw");
		String ip = req.getRemoteAddr();
		String opt = req.getParameter("option");
		logger.info("id : " + id);
		logger.info("pw : " + pw);
		logger.info("ip : " + ip);
		logger.info("option : " + opt);
		
		if (mainService.login(id, pw, ip, opt)) {
			// 세션을 위에서 받아올 수 도 있지만.. 세션은 내장객체이므로 여기서도 사용할 수 있다.
			HttpSession session = req.getSession();
			session.setAttribute("loginId", id);
			// 관리자or일반유저에 따라 세션을 다르게 주어 다른 페이지를 보여주도록 할 수 있다.
			session.setAttribute("perm", opt);
			msg = "로그인에 성공하였습니다.";
		}
		
		model.addAttribute("msg", msg);
		
		return page;
	}
	
}
