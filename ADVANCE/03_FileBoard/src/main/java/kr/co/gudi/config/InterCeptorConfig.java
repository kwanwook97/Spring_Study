package kr.co.gudi.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import kr.co.gudi.utils.LoginChecker;

// 설정을 위한 클래스라는 표시(이게 없으면 일반 클래스로 인식 한다.)
@Configuration
public class InterCeptorConfig implements WebMvcConfigurer {

	@Autowired LoginChecker checker;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		List<String>excludeList = new ArrayList<String>();
		excludeList.add("/resources/**");//resources 이후 어떤 경로던지
		excludeList.add("/member_*"); // member_ 이후 어떤 값이던지
		excludeList.add("/checkId.*");
		excludeList.add("/*.ajax");
		excludeList.add("/");
		
		
		// registry 에 언제 어떤 일을 할건지 등록		
		registry.addInterceptor(checker) // 인터셉터에서 실행할 클래스
		.addPathPatterns("/**") // 어떤 패턴의 URL 에 대해서 할것인가?
		.excludePathPatterns(excludeList); // 어떤 패턴을 제외시킬것인지?

	}
	
	
	

}
