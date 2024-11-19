package kr.co.gudi.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.co.gudi.dto.TeamDTO;

@Mapper
public interface TeamMapper {

	List<TeamDTO> list();

	int update(Map<String, String> params);

}
