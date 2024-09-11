package kr.co.gudi.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.co.gudi.dto.BoardDTO;

@Mapper
public interface BoardDAO {

	BoardDTO detail(int idx);
	
	List<BoardDTO> list();

	int del(int idx);

	int write(Map<String, String> param);

	int upHit(int idx);

	int update(Map<String, String> param);

}
