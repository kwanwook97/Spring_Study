package kr.co.gudi.dao;

import java.sql.Connection;
import java.sql.DriverManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainDAO {

	Logger logger = LoggerFactory.getLogger(getClass());

	public boolean dbConnect() {

		// 1. 접속에 필요한 정보준비
		// 아이디, 비밀번호, 접속url, class driver		
		String id = "web_user";
		String pw = "pass";		// 
		String url = "jdbc:mariadb://localhost:3306/mydb";
		String driver = "org.mariadb.jdbc.Driver";
			
		// Connection객체는 DB에서 하는 모든 일을 대신 DB에 접속해서 수행해준다.
		Connection conn = null;
		boolean success = false;
		
		// db의 연결여부에 따라 true/false값을 반환한다.
		try {
			// 2. class driver를 등록
			Class.forName(driver);			
			// 3. 드라이버 매니저에게 DB를 가져오도록 시킨다.(Connection)
			conn = DriverManager.getConnection(url, id, pw);
			logger.info("connection : {}", conn);
						
			// connection을 가지고 DB에 해야할 일을 한다. (현재는 아무것도 할일이 없지만...)
			
			// 다 했으면 반납.
			conn.close();
			success = true;
			
		} catch (Exception e) {			
			e.printStackTrace();
		}		
		
		
		return success;
	}
}
