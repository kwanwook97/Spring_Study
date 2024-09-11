package kr.co.gudi.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.gudi.dao.BoardDAO;
import kr.co.gudi.dto.BoardDTO;

@Service
public class BoardService {

	@Autowired BoardDAO board_dao;
	
	Logger logger = LoggerFactory.getLogger(getClass());

	public List<BoardDTO> list() {
		return board_dao.list();
		
	}

	public BoardDTO detail(int idx) {
		board_dao.upHit(idx);
		return board_dao.detail(idx);
	}

	public void del(int idx) {		
		board_dao.del(idx);
	}

	public void write(Map<String, String> param) {
		int row = board_dao.write(param);
		logger.info("insert date : " + row);
		
	}

	public BoardDTO updateForm(int idx) {
		return board_dao.detail(idx);
	}

	public void update(Map<String, String> param) {
		int row = board_dao.update(param);
		logger.info("update row : " + row);
		
	}
	
}
