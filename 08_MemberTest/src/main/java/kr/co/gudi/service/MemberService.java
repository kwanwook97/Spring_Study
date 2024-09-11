package kr.co.gudi.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import kr.co.gudi.dao.MemberDAO;
import kr.co.gudi.dto.MemberDTO;

public class MemberService {

	public String join(Map<String, String> param) throws SQLException {
		
		MemberDAO dao = new MemberDAO();
		String msg = "회원가입이 실패하였습니다.";
		
		int count = dao.join(param);
		if(count > 0) {
			msg = "회원가입이 완료되었습니다.";
		}
		
		return msg;
	}

	public List<MemberDTO> list(){			
						 			
		try {
			MemberDAO dao = new MemberDAO();
			return dao.list();
		} catch (SQLException e) {
			e.printStackTrace();
			return new ArrayList<MemberDTO>();
		}
		
	}

	public boolean login(String id, String pw) throws SQLException {
		boolean success = false;
		
		MemberDAO dao = new MemberDAO();		
		success = dao.login(id, pw);
		
		return success;
	}

	public List<MemberDTO> detail(String id) {
		
		MemberDAO dao;
		List<MemberDTO> info = null;
		
		try {
			dao = new MemberDAO();
			info = dao.detail(id);
			
		} catch (SQLException e) {
			e.printStackTrace();
			info = new ArrayList<MemberDTO>();
		}
		
		return info;
	}

	public void del(String id) {
		MemberDAO dao;
		try {
			dao = new MemberDAO();
			dao.del(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
}
