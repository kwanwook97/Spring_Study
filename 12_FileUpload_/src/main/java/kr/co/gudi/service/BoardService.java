package kr.co.gudi.service;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import ch.qos.logback.core.util.ContentTypeUtil;
import kr.co.gudi.dao.BoardDAO;
import kr.co.gudi.dto.BoardDTO;
import kr.co.gudi.dto.FileDTO;

@Service
public class BoardService {
	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired BoardDAO boardDAO;

	public List<BoardDTO> list() {
		return boardDAO.list();
	}

	
	public void write(Map<String, String> param, MultipartFile[] files) {

		// insert와 동시에 방금 넣은 key값을 가져오는 방법을 Mybatis에서 제공하고 있다.
		// (Generated Key)
		// 1. 파라미터가 DTO여야 한다.
		BoardDTO dto = new BoardDTO();
		dto.setUser_name(param.get("user_name"));
		dto.setSubject(param.get("subject"));
		dto.setContent(param.get("content"));
		
		if(boardDAO.write(dto) > 0) { // 글쓰기
			
			// 3. insert 후 DTO에서 꺼내 쓸 것.
			int idx = dto.getIdx();
			logger.info("방금 insert한 idx : " + idx);
			
			
			// 기존의 IO방식이 아닌 NIO방식을 사용한다.
			for(MultipartFile file : files) { // 파일저장

				try {
					// 이름변경
					// 1. 기존 파일명(img.gif)
					String fileName = file.getOriginalFilename(); 
	 
					// 2. 파일에서 확장자 떼어내기
					// String ext = fileName.split("\\.")[1]; // escape\\문자를 사용하여 확장자를 가져오기!
					String ext = fileName.substring(fileName.lastIndexOf(".")); 
					// 3. 변경할 이름 생성(UUID + 확장자)
					String newFileName = UUID.randomUUID().toString() + ext;
					
					// 4. 바이트 추출
					byte[] arr = file.getBytes();
					
					// 5. 저장경로 지정
					Path path = Paths.get("C:/upload/" + newFileName);
					
					// 6. 파일쓰기
					Files.write(path, arr);
					
					// 7. 파일의 정보를 DB에 INSERT.
					boardDAO.fileWrite(idx, fileName, newFileName);
					
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
		
		
	@Transactional
	public void detail(String idx, Model model) {
		boardDAO.uphit(idx);
		
		BoardDTO bbs = boardDAO.detail(idx);
		List<FileDTO> files = boardDAO.files(idx);
		
		model.addAttribute("info", bbs);
		model.addAttribute("files", files);
	}


	public void del(String idx) {
		// bbs삭제 -> files도 삭제 -> 파일삭제?
		// 파일을 어떻게 삭제할까?....
		
		// 1. idx를 가지고 files의 new_filename을 알아내야함.
		List<FileDTO> fileList = boardDAO.files(idx);
		
		// 2. bbs에 있는 데이터 삭제 -> files의 데이터 삭제 (자동)
		int row = boardDAO.del(idx);
		logger.info("delete row : " + row);
		
		// 3. 삭제성공시 -> file을 삭제. (이건 또... nio가 아닌 io를 사용하네..?)
		if(row > 0) {
			for(FileDTO dto : fileList) {
				File file = new File("C:/upload/" + dto.getNew_filename());
				if(file.exists()) {
					boolean success = file.delete();
					logger.info(dto.getNew_filename() + "delete : " + success);
				}
			}
		}
		
	}


	public ResponseEntity<Resource> download(String new_filename, String ori_filename) {
		
		// Header (보낼 컨텐트 타입, 형태(문자|파일);파일일 경우 파일명)
		HttpHeaders header = new HttpHeaders();

		// 본문 - FileSystem을 이용해 특정위치의 파일을 가져옴.
		Resource res = new FileSystemResource("C:/upload/" + new_filename);  
		
		try {
			// content-type : image, text, binary,...
			// application/octet-stream : binary를 의미한다.
			header.add("content-type", "application/OctetStreamData");
			// content-Disposition : 형태(문자|파일); 파일일 경우 파일명
			// 더블쿼터를 두번쓸 수 없으니... escape문자(\)를 사용한다.
			// 주의사항 : 파일명이 한글인 경우 파일명이 깨지기 때문에 특수한 처리를 해주어야 한다.
			String encode_name = URLEncoder.encode(ori_filename, "UTF-8");
			header.add("content-Disposition", "attachment;filename=\"" + encode_name + "\"");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		// body(본문), header(사전정보), 상태값(200, 400, 404, 405...)
		// HttpStatus.OK를 쓰면.... 정상으로 표시된다..?
		return new ResponseEntity<Resource>(res, header, HttpStatus.OK);
	}	
	
}
