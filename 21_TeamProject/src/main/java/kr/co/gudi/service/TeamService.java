package kr.co.gudi.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.gudi.dto.TeamDTO;
import kr.co.gudi.mapper.TeamMapper;

@Service
public class TeamService {

	@Autowired TeamMapper team_mapper;
	
	Logger logger = LoggerFactory.getLogger(getClass());

	public List<TeamDTO> list() {
		return team_mapper.list();
	}

	public void update(Map<String, String> params) {
		int row = team_mapper.update(params);
		logger.info("row : " + row);
		
	}
}
