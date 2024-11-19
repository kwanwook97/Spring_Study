package kr.co.gudi.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import kr.co.gudi.dao.MemberDAO;

@Service
public class MemberService {

	
	@Autowired PasswordEncoder encoder;
	
	private final MemberDAO memberDAO;
	
	public MemberService(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}


	public int join(Map<String, String> param) {	
		param.put("pw", encoder.encode(param.get("pw")));		
		return memberDAO.join(param);
	}


	public boolean login(String id, String pw) {
		
		// 1. id 를 만족하는 pw 를 DB 에서 가져와라
		String enc_pw = memberDAO.login(id);
		
		//2.받아온 pw(plain) 와 DB 에 저장된 pw(encoded) 가 같은지 비교		
		return encoder.matches(pw, enc_pw);
	}


}


















