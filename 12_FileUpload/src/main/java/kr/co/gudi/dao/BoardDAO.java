package kr.co.gudi.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.co.gudi.dto.BoardDTO;

@Mapper
public interface BoardDAO {

	List<BoardDTO> list();

	boolean write(Map<String, String> param);

	BoardDTO detail(int idx);

	void del(int idx);

	void update(Map<String, String> param);

	BoardDTO updateForm(int idx);
}
