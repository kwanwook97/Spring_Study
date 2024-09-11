package kr.co.gudi.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.co.gudi.dto.MemberDTO;

@Mapper
public interface MemberDAO {

	int join(Map<String, String> param);

	String login(String id, String pw);

	List<MemberDTO> list();

	MemberDTO detail(String id);

	int del(String id);
	
	// HikariPool-1 : Spring Boot에서 지원해주는 커넥션 풀
	// ConnectionPool : 커넥션을 미리 만들어 놓았다가 빌려주고 받는개념.
}
