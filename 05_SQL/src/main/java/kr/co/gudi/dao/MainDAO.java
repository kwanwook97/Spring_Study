package kr.co.gudi.dao;

import java.sql.Connection;
import java.sql.DriverManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainDAO {
	Logger logger = LoggerFactory.getLogger(getClass());
	
	String url = "jdbc:mariadb://localhost:3306/mydb";			
	String id = "web_user";
	String pw = "pass";
	String driver = "org.mariadb.jdbc.Driver";
	
	Connection conn = null;
	boolean success = false;
		
	public boolean dbConnect() {
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, id, pw);
			
			// db명령어 사용.
			logger.info("connection : {}", conn);
			
			// 다 썼으면 반납.
			conn.close();
			success = true;
			
		} catch (Exception e) {			
			e.printStackTrace();
		}
		
		return success;
	}
}
