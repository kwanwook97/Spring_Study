package kr.co.gudi.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

// java와 xml을 연결해주는 interface
// 그 역할을 수행한다는 뜻으로 표시를 해주어야 한다.
@Mapper
public interface MemberDAO {
	
	// executeUpdate는 업데이트된 row 개수가나오기 때문에 반환타입을 int로 해준다.
	int join(Map<String, String> param);

}
