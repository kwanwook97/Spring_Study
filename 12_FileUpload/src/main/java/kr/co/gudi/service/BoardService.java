package kr.co.gudi.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.gudi.dao.BoardDAO;
import kr.co.gudi.dto.BoardDTO;

@Service
public class BoardService {

	@Autowired BoardDAO board_dao;
	
	
	public List<BoardDTO> list() {
		return board_dao.list();
	}

	public boolean write(Map<String, String> param) {
		
		return board_dao.write(param);
	}

	public BoardDTO detail(int idx) {
		return board_dao.detail(idx);
	}

	public void del(int idx) {
		board_dao.del(idx);
	}
	
	public void update(Map<String, String> param) {
		board_dao.update(param);
	}

	public BoardDTO updateForm(int idx) {
		return board_dao.updateForm(idx);
	}


}
