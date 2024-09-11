package kr.co.gudi.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.gudi.dto.MemberDTO;
import kr.co.gudi.service.MemberDAO;

public class MemberService {
	
	Logger logger = LoggerFactory.getLogger(getClass());

	public String join(Map<String, String> param) throws SQLException {
		
		MemberDAO dao = new MemberDAO();
		String msg = "회원가입에 실패 했습니다.";
		if(dao.join(param)== 1) {
			msg = "회원가입에 성공 했습니다.";
		}		
		return msg;
	}

	public boolean login(String id, String pw) {		
		MemberDAO dao = new MemberDAO();		
		try {
			return dao.login(id,pw);
		} catch (SQLException e) {			
			e.printStackTrace();
			return false;
		}
	}

	public List<MemberDTO> list() {		
		MemberDAO dao = new MemberDAO();				
		try {
			return dao.list();
		} catch (SQLException e) {
			e.printStackTrace();
			return new ArrayList<MemberDTO>();
		}
	}

	public void del(String id) {
		MemberDAO dao = new MemberDAO();
		try {
			dao.del(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public MemberDTO detail(String id) {
		
		MemberDTO dto = null;		
		MemberDAO dao = new MemberDAO();
		
		try {
			dto = dao.detail(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		
		return dto;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
