package kr.co.gudi.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.co.gudi.dto.UserInfo;

// js는 보안이 취약하다.
// 보안상 문제가 많이 생겨서 url이 다를경우 자바스크립트와의 통신을 차단시킨다.
// 해결방법 1 : view페이지가 서버에 있으면 된다.(views폴더 밑에 넣는 것 처럼...)
// 해결방법 2 : 서버에서 허용을 해준다. (@CrossOrigin을 사용한다.)

@CrossOrigin
@Controller
public class ApiController {

	Logger logger = LoggerFactory.getLogger(getClass()); 
	
		/* 우리가 그동안 써왔던 방식은 request객체를 이용한 forward방식이었다.
		 * 그래서 model에 값을 넣고 페이지 이름을 반환하여 특정 페이지로 데이터를 보내는 형태였다.
		 * 
		 * REST방식은 response를 이용한 방식이다.
		 * 그래서 요청을 한 페이지로 돌아가고(페이지 이동안됨.), 보여줄 내용을 그려서 보낸다.
	 */
	
	
	/* 일반적으로 Restful서비스에서는 다음과 같은 method를 사용한다.
	 * GET	  : 특정 데이터를 조회할 때 ... -> url에서 데이터를 찾아오는(조회)하는..?
	 * POST	  : 특정 데이터를 요청할 때 ... -> 클라이언트에서 바로 서버로 전달한 데이터를 요청..?
	 * DELETE : 특정 데이터를 삭제할 때
	 * PUT    : 특정 데이터를 수정할 때
	 * PATCH  : 특정 데이터를 일부 수정할 때
	 */
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	@ResponseBody // 이걸 넣으면 응답을 response로 하게된다.
	public String main() {
		// JSON형식..?
		return "{\"msg\":\"rest api형태의 답변입니다.\"}";
	}

	// 복수개의 메서드를 받는 것도 가능하다!
	@RequestMapping(value = "/map", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	// 자바에서는 꺼낼 때 Map의 Object를 Casting해야하지만... 어차피 다른 곳으로 던지기 때문에 상관없다.
	public Map<String, Object> map() {
		logger.info("요청에 대해서 JSON형태로 보내주기");
		// 위처럼 문자열로 JSON형태를 만들어 보낼 수도 있지만...
		// JAVA에서 json과 비슷한 데이터 타입을 활용하여 보낼 수도있다. (HashMap -> JSON : jackson-core라는 라이브러리가 필요)
		// SpringBoot에는 기본라이브러리가 내장되어있기 때문에 따로 넣어줄 필요는 없다.
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("succes", true);
		map.put("msg", "map형태로 반환성공!");
		
		return map;
	}
	
	// method를 명시하지 않으면 모든 method형태를 다 받는다.
	// 보안상 좋지않기 때문에 중요한 호출은 이렇게 하면안됨.
	@RequestMapping(value = "/object")
	@ResponseBody
	public UserInfo object() {
		
		UserInfo info = new UserInfo();
		info.setId("tester");
		info.setName("Lee");
		info.setAge(55);
		info.setSuccess(true);
		
		return info;
	}
	
	// @RequestMapping(value = "/list", method = RequestMethod.GET)
	// XXXMapping을 지원해준다.
	@GetMapping(value = "/list")
	@ResponseBody
	public List<String> list(){
		
		List<String> list = new ArrayList<String>();
		list.add("하나");
		list.add("둘");
		list.add("셋");
		
		return list;
	}

	
	// get?lec=java&no=1 <- 일반적으로 사용하는 방식으로 매개변수를 가져올 수 있다.
	@GetMapping(value = "/get")
	@ResponseBody
	public String param(String lec, String no) {
		return "{\"msg\":\"" +lec+ "과목의" +no+ "번째 글 내용\"}";
	}

	
	// get/java/1 <- 파라미터가 url에 포함되어있는 경우
	@GetMapping(value = "/get/{lec}/{no}")
	@ResponseBody
	// @PathVariable을 사용하면.. url에서 입력받은 값을 받아올 수 있다.
	public String urlPath(@PathVariable String lec, @PathVariable String no) {
		return "{\"msg\":\"" +lec+ "과목의" +no+ "번째 글 내용\"}";
	}

	
	@GetMapping(value = "/strMap")
	@ResponseBody
	public Map<String, Object> strMap() throws Exception{
		
		// java에서 String을 map형태로 바꿔서 보낼 경우
		String json = "{\"no\":1, \"msg\":\"Map변환완료\", \"name\":\"김지훈\"}";
		// String을 Map으로 바꾸려면 라이브러리가 필요하다.
		ObjectMapper mapper = new ObjectMapper();
		
		// json문자열, 변화하고 싶은 클래스
		// 받는 클래스가 제너릭이 있는데, 변환할 클래스에 제너릭이 없으면 명시하라고 경고가 생긴
		HashMap<String, Object> map = mapper.readValue(json, HashMap.class);
//		HashMap<String, Object> map = mapper.readValue(json, new TypeReference<HashMap<String, Object>>(){});
		
		return map;
	}

	@GetMapping(value="/strObj")
	@ResponseBody
	public UserInfo strObj() throws Exception {
		
		String json = "{\"id\":\"admin\", \"name\":\"김지훈\", \"age\":55, \"success\":true}";
		
		ObjectMapper mapper = new ObjectMapper();
		// 제너릭이 없으면 시비걸지 않는다.
		UserInfo info = mapper.readValue(json, UserInfo.class);
		
		return info;
	}
	
}

















