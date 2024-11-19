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
	
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		// 403 : 권한 없음
		http.httpBasic().disable().csrf().disable();
		
		//CSRF(Cross Site Request Forgery)
		// A 사이트를 가려고 하는데, 실수로 B 사이트로 가게 되었을때... B 에서 A 에게 요청을 보낸다.
		// 그래서 A 의 압장에서는 자신에게 온 요청이 A 의 것인지 확인해야 한다.
		// 그래서 CSRF 토큰 이라는 녀석을 지속적으로 갱신하고, 요청시 이 토큰을 확인하려고 한다.
		// 그런데 요청을 보낼때 이 CSRF 토큰이 없거나 틀리면 권한없음(403) 으로 간주한다.
		
		
		return http.build();
	}

}
