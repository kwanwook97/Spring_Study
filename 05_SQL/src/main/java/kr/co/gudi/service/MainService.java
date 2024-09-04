package kr.co.gudi.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.gudi.dao.MainDAO;

public class MainService {

	Logger logger = LoggerFactory.getLogger(getClass());
	
	public String dbConnect() {
		MainDAO dao = new MainDAO();
		boolean success = dao.dbConnect();
		
		String msg = success?"db연결이 완료되었습니다.":"db연결이 실패했습니다.";
		
		return msg;
	}

	
}
