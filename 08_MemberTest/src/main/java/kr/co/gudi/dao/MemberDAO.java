package kr.co.gudi.dao;

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
	
	public MemberDAO() throws SQLException {
		String id = "web_user";
		String pw = "pass";
		String url = "jdbc:mariadb://localhost:3306/mydb";
		String driver = "org.mariadb.jdbc.Driver";
		
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, id, pw);										
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public int join(Map<String, String> param) {
		String sql = "INSERT INTO member(id, pw, name, age, gender, email)"
				+ "VALUES(?, ?, ?, ?, ?, ?)";
		int row = 0;
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);			
			ps.setString(1, param.get("id"));
			ps.setString(2, param.get("pw"));
			ps.setString(3, param.get("name"));
			ps.setInt(4, Integer.parseInt(param.get("age")));
			ps.setString(5, param.get("gender"));
			ps.setString(6, param.get("email"));
			
			row = ps.executeUpdate();
		
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		
		return row;
	}

	public List<MemberDTO> list() {
		List<MemberDTO> list = new ArrayList<MemberDTO>();
		
		String sql = "SELECT id, pw, name, age FROM member";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				MemberDTO dto = new MemberDTO();
				dto.setId(rs.getString("id"));
				dto.setPw(rs.getString("pw"));
				dto.setName(rs.getString("name"));
				dto.setAge(rs.getInt("age"));
				list.add(dto);
			}
			
			rs.close();
			ps.close();
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		
		return list;
	}

	public boolean login(String id, String pw) {
		boolean success = false;
		
		String sql = "SELECT * FROM member WHERE id = ?";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			
			success = rs.next();
			
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return success;
	}

	public List<MemberDTO> detail(String id) {
		
		List<MemberDTO> info = new ArrayList<MemberDTO>();
		String sql = "SELECT * FROM member WHERE id = ?";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				MemberDTO dto = new MemberDTO();
				dto.setId(id);
				dto.setPw(rs.getString("pw"));
				dto.setName(rs.getString("name"));
				dto.setAge(rs.getInt("age"));
				dto.setGender(rs.getString("gender"));
				dto.setEmail(rs.getString("email"));
				info.add(dto);
			}
			
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return info;
	}

	public void del(String id) {
		
		String sql = "DELETE FROM member WHERE id = ?";
		
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			int row = ps.executeUpdate();
			logger.info("삭제된 개수 : " + row);
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
