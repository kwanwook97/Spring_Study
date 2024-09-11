package kr.co.gudi.service;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.gudi.dao.MemberDAO;

@Service
public class MemberService {

	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired MemberDAO dao;
	
	public String join(Map<String, String> param) {
		logger.info("service param : {}", param);
		int row = dao.join(param);
		String msg = "회원가입에 실패했습니다.";
		
		// row가 1개이면 성공
		if(row == 1) {
			msg = "회원가입에 성공했습니다.";
		}
		
		return msg;
	}

}
