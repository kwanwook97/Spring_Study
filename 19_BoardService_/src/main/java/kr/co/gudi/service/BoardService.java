package kr.co.gudi.service;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.gudi.dao.BoardDAO;

@Service
public class BoardService {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired BoardDAO dao;
		
	public Map<String, Object> list(int page, int cnt) {
		
		int limit = cnt;
		int offset = (page-1) * cnt;
		int totalpage = dao.count(cnt);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("totalpages", totalpage);
		result.put("currPage", page);
		result.put("list",dao.list(limit,offset));
		
		return result;
	}
	
}
