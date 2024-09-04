package kr.co.gudi.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.gudi.dao.MainDAO;

public class MainService {

	Logger logger = LoggerFactory.getLogger(getClass());

	public String dbConnect() {
		
		MainDAO dao = new MainDAO();
		// 예외가 발생했으면 false, DB연결이 성공했다면 true값을 가져온다.
		boolean success = dao.dbConnect();		
		return success ? "DB접속에 성공했습니다." : "DB접속에 실패했습니다.";
		
	}	
	
}
