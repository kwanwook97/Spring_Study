package kr.co.gudi.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.ui.Model;

import kr.co.gudi.dto.MemberDTO;

@Mapper 
public interface MemberDAO {

	List<MemberDTO> list(Map<String, String> param);
	
	int join(MemberDTO dto);

	List<MemberDTO> multi(List<String> userName);

	MemberDTO detail(String id);

	int update(Map<String, String> params);
	
	
}
