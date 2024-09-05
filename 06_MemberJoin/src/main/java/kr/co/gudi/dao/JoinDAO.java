package kr.co.gudi.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JoinDAO {

	Connection conn = null;
	PreparedStatement ps = null;
	
	public JoinDAO() {//Contructor

		String id = "web_user";
		String pw = "pass";
		String url = "jdbc:mariadb://localhost:3306/mydb";
		String driver = "org.mariadb.jdbc.Driver";
		
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, id, pw);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}	
	
	public int join(String id, String pw, String name, int su, 
			String gender, String email) throws SQLException {
		
		int row = 0;
		
		String sql = "INSERT INTO member(id,pw,name,age,gender,email)";
		sql+="VALUES(?,?,?,?,?,?)";
		
		ps = conn.prepareStatement(sql);
		ps.setString(1, id);
		ps.setString(2, pw);
		ps.setString(3, name);
		ps.setInt(4, su);
		ps.setString(5, gender);
		ps.setString(6, email);
		
		row = ps.executeUpdate();
		
		ps.close();
		conn.close();

		return row;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
}
