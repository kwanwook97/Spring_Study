package kr.co.gudi.service;

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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.co.gudi.dao.Boarddao;
import kr.co.gudi.dto.Bbsdto;
import kr.co.gudi.dto.Filedto;

@Service
public class Boardservice {
	
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Value("${spring.servlet.multipart.location}") private String root;
	
	@Autowired Boarddao board_dao;
	
	public List<Bbsdto> list() {
			logger.info("file root : " + root);
			return board_dao.list();
		}
		
	
		
	public int write(MultipartFile[] files,Map<String, String> param) {
		
		Bbsdto b_dto = new Bbsdto();
		b_dto.setContent(param.get("content"));
		b_dto.setSubject(param.get("subject"));
		b_dto.setUser_name(param.get("user_name"));
		
		if(board_dao.write(b_dto) > 0) {
			int idx = b_dto.getIdx();
			
			fileSave(files, idx);
		}
		
		return 0;
	}



	private void fileSave(MultipartFile[] files, int idx) {
		
		for(MultipartFile file : files) {
			
			try {
				if(!file.isEmpty()) {
					String filename = file.getOriginalFilename();
					String type = file.getContentType();
					
					String ext = filename.substring(filename.lastIndexOf("."));
					String newfilename = UUID.randomUUID().toString()+ext;
					byte[] arr = file.getBytes();
					
					Path path = Paths.get("root/"+newfilename);
					
					Files.write(path, arr);
					board_dao.fileWrite(idx,filename,newfilename);
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}



	public int b_del(String idx) {
		return board_dao.b_del(idx);
		
		
	}



	public Bbsdto b_detail(String idx) {
		
		return board_dao.b_detail(idx);
	}



	public List<Filedto> files(String idx) {
		
		return board_dao.files(idx);
	}



	public ResponseEntity<Resource> download(String filename) {
		Resource resource = new FileSystemResource(root+"/"+filename);
		HttpHeaders header = new HttpHeaders();
		
		// DB에 type을 저장하는 경우 try-catch문을 사용할 필요가 없다!
		try {
			String enc_name = URLEncoder.encode(board_dao.getfilename(filename));
			header.add("", "application/octec-stream");
			header.add("content-type", type); //image/jpg, image/png
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Resource>(resource,header,200);
	}


	public ResponseEntity<Resource> getImg(String fileName) {
		Resource resource = new FileSystemResource(root+"/"+fileName);
		HttpHeaders header = new HttpHeaders();
		
		// DB에 type을 저장하는 경우 try-catch문을 사용할 필요가 없다!
		try {
			String type = Files.probeContentType(Paths.get(root+"/"+fileName));
			header.add("content-type", type); //image/jpg, image/png
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Resource>(resource,header,200);
	}
	
	

	public int bHit(String idx) {
		return board_dao.bHit(idx);
		
	}



	public void update(Map<String, String> params, MultipartFile[] files) {

		// 1. 글 수정작업
		board_dao.update(params);
		
		// 2. 업로드 할 파일이 있다면 업로드
		int idx = Integer.parseInt(params.get("idx"));
		fileSave(files, idx);
	}




	

}	
