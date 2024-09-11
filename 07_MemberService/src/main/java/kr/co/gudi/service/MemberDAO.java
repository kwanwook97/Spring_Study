package kr.co.gudi.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.gudi.dto.MemberDTO;

public class MemberDAO {

	Logger logger = LoggerFactory.getLogger(getClass());	
	
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	public MemberDAO() {
		String id = "web_user";
		String pw = "pass";
		String url = "jdbc:mariadb://localhost:3306/mydb";
		String driver = "org.mariadb.jdbc.Driver";
		
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, id, pw);
		} catch (Exception e) {
			e.printStackTrace();
		}
		

	}

	public int join(Map<String, String> param) throws SQLException {
		
		int row = 0;
		String sql = "INSERT INTO member(id,pw,name,age,gender,email)";
		sql+="VALUES(?,?,?,?,?,?)";
		
		ps = conn.prepareStatement(sql);
		ps.setString(1, param.get("id"));
		ps.setString(2, param.get("pw"));
		ps.setString(3, param.get("name"));
		ps.setInt(4, Integer.parseInt(param.get("age")));
		ps.setString(5, param.get("gender"));
		ps.setString(6, param.get("email"));
		
		row = ps.executeUpdate();	
		ps.close();
		conn.close();
		
		return row;
	}

	public boolean login(String id, String pw) throws SQLException {
		
		String sql = "SELECT id FROM member WHERE id = ? AND pw = ?";
		boolean success = false;
		
		ps = conn.prepareStatement(sql);
		ps.setString(1, id);
		ps.setString(2, pw);
		rs = ps.executeQuery();		
		success = rs.next();
		
		rs.close();
		ps.close();
		conn.close();
		
		return success;
	}

	public List<MemberDTO> list() throws SQLException {
		
		String sql = "SELECT id,name,age FROM member";
		ps = conn.prepareStatement(sql);
		rs = ps.executeQuery();
		
		List<MemberDTO> list = new ArrayList<MemberDTO>();
		
		while(rs.next()) {
			MemberDTO dto = new MemberDTO();
			dto.setId(rs.getString("id"));
			dto.setName(rs.getString("name"));
			dto.setAge(rs.getInt("age"));
			list.add(dto);
		}
		
		rs.close();
		ps.close();
		conn.close();
				
		return list;
	}

	public void del(String id) throws SQLException {
		
		String sql = "DELETE FROM member WHERE id = ?";
		
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, id);
		int row = ps.executeUpdate();
		logger.info("삭제된 개수 : " + row);
		ps.close();
		conn.close();
		
	}

	public MemberDTO detail(String id) throws SQLException {
		String sql = "SELECT * FROM member WHERE id = ?";
		
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, id);
		rs = ps.executeQuery();
		MemberDTO dto = null;		
		
		if(rs.next()) {
			dto = new MemberDTO();
			dto.setId(rs.getString("id"));
			dto.setPw(rs.getString("pw"));
			dto.setName(rs.getString("name"));
			dto.setAge(rs.getInt("age"));
			dto.setGender(rs.getString("gender"));
			dto.setEmail(rs.getString("email"));
		}
		
		rs.close();
		ps.close();
		conn.close();
		
		return dto;
	}

}

















