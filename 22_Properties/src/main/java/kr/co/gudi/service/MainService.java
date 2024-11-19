package kr.co.gudi.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import kr.co.gudi.dao.MainDAO;

@Service
// claspath : 실제로 동작되는 프로그램의 경로
@PropertySource("classpath:super_admin.properties")
public class MainService {

	@Autowired MainDAO mainDAO;
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Value("${user.id}") private String user_id;
	@Value("${user.pw}") private String user_pw;
	@Value("${user.ip}") private String user_ip;

	public boolean login(String id, String pw, String ip, String opt) {
		
		boolean success = false;
	
		if(opt.equals("admin")) {
			// super_admin.properties를 이용
			logger.info("user_id : " + user_id);
			logger.info("user_pw : " + user_pw);
			logger.info("user_ip : " + user_ip);
			
			// id, pw, ip가 관리자의 것과 모두 같다면... 로그인에 성공!
			if(id.equals(user_id) && pw.equals(user_pw) && ip.equals(user_ip)) {
				success = true;
			}
			
		}else {
			// DB를 이용해 member 테이블을 이용
			int row = mainDAO.login(id, pw);
			if(row > 0) {
				success = true;
			}
		}
		
		return success;
	}
	
}
