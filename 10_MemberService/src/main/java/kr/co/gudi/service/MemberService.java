package kr.co.gudi.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.gudi.dao.MemberDAO;
import kr.co.gudi.dto.MemberDTO;

@Service
public class MemberService {

	@Autowired MemberDAO dao;
	
	public String join(@RequestParam Map<String, String> param) {
		
		int row = dao.join(param);
		String msg = "회원가입에 실패했습니다.";
		
		// row가 1이면 회원가입 성공.
		if(row == 1) {
			msg = "회원가입에 성공했습니다.";
		}
		
		return msg;
	}

	public boolean login(String id, String pw) {
		
		String loginId =  dao.login(id, pw);
		boolean success = false;
		
		if(loginId != null) {
			success = true;
		}
		
		return success;
	}

	public List<MemberDTO> list() {
		
		return dao.list();
	}
	
	public MemberDTO detail(String id) {
		
		return dao.detail(id);
	}

	public void del(String id) {
		dao.del(id);
	}

	

}
