package kr.co.gudi.service;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.gudi.dao.JoinDAO;

public class JoinService {

	Logger logger = LoggerFactory.getLogger(getClass());
	
	public String join(String id, String pw, String name, String age, 
			String gender, String email) throws SQLException {
		logger.info("join service 접근");
		JoinDAO dao = new JoinDAO();
		String msg = "회원가입에 실패 했습니다.";
		int su = Integer.parseInt(age);
		if(	dao.join(id,pw,name,su,gender,email)==1) {
			msg = "회원 가입에 성공 했습니다.";
		}
				
		return msg;
	}

}






