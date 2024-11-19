package kr.co.gudi.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MainDAO {

	int login(String id, String pw);

	
	
}
