package kr.co.gudi.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

//@Controller
@RestController //이걸 쓰면 해당 클래스 내부의 모든 메서드는 @ResponseBody 효과를 갖게 된다.
public class MainController {
	
	
	@Value("${prof.name}") String name;
	@Value("${db.addr}") String addr;
	@Value("${db.username}") String username;
	@Value("${db.password}") String password;
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@RequestMapping(value="/")
	//@ResponseBody
	public Map<String, String> main(){		
		Map<String, String> map = new HashMap<String, String>();
		map.put("msg","Hello Spring Boot");		
		map.put("name", name);
		map.put("addr", addr);
		map.put("username", username);
		map.put("password", password);		
		return map;
	}
	
	// 1. STS - 내장톰캣
	// 일단 Spring Boot App 실행
	// Run > Run Configuration > Spring Boot App > profile 을 dev 로 선택
	
	// 2. STS - 외장톰캣
	// Server 폴더의 catalina.properties 파일 열기
	// spring.profiles.active=dev

	// 3. 실제 톰캣
	
	// 4. war 직접 실행시
	
	
	
	
	

}




















