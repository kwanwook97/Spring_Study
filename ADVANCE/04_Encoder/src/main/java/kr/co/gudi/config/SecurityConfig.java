package kr.co.gudi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	// 사용할 암호화 클래스 등록 <- 이걸 해 줘야 어느 클래스에서든지 @Autowired 해서 BCryptPasswordEncoder 를 사용 할 수 있다.
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		//스프링 시큐리티 에서 사용하거나 사용하지 않을 기능에 대한 정의
		http.httpBasic().disable();		// 기본 기능을 사용하지 않겠다.
		return http.build();
	}

}
