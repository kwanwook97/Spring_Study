package kr.co.gudi.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import kr.co.gudi.dao.BoardDAO;
import kr.co.gudi.dto.BoardDTO;
import kr.co.gudi.dto.FilesDTO;

@Service
public class BoardService {

	Logger logger = LoggerFactory.getLogger(getClass());
		
	@Autowired BoardDAO bd_dao;

	public List<BoardDTO> list() {
		return bd_dao.list();
	}

	
	@Transactional
	public void detail(String idx, Model model) {
		logger.info(idx);
		BoardDTO det = bd_dao.detail(idx);
		bd_dao.bhit(idx);
		List<FilesDTO> files = bd_dao.files(idx);
		model.addAttribute("info", det);
		model.addAttribute("files", files);
		
	}

	public int write(Map<String, String> param, MultipartFile[] files) {
		
		BoardDTO dto = new BoardDTO();
		dto.setUser_name(param.get("user_name"));
		dto.setSubject(param.get("subject"));
		dto.setContent(param.get("content"));
		// 1. 글 저장
		int row = bd_dao.write(dto);
		logger.info("insert 한 수 : "+row);
		int idx = dto.getIdx();
		logger.info("방금 insert 한 idx : "+idx);
		
		if(idx>0 && row>0) {
			saveFile(files, idx);						
		}
				
		return idx;
		
	}

	private void saveFile(MultipartFile[] files, int idx) {
		
		try {
			for (MultipartFile file : files) {
				/* 파일저장 */
				// 1. 파일명 추출
				String ori_filename = file.getOriginalFilename();
				// 2. 새 파일명 생성 
				String ext = ori_filename.substring(ori_filename.lastIndexOf("."));
				String new_filename = UUID.randomUUID()+ext;			
				// 3. 파일저장		
				Path path = Paths.get("C:/upload/"+new_filename);
				byte[] arr = file.getBytes();
				Files.write(path, arr);			
				
				/* files 테이블에 insert*/		
				bd_dao.fileWrite(idx,ori_filename,new_filename);
				
				
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	
}
























