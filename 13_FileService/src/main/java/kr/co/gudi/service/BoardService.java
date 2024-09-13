package kr.co.gudi.service;

import java.io.File;
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

import kr.co.gudi.mapper.BoardMapper;
import kr.co.gudi.vo.BoardVO;
import kr.co.gudi.vo.FileVO;

@Service
public class BoardService {

	@Autowired BoardMapper board_mapper;
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	public List<BoardVO> list(){
		return board_mapper.list();
	}

	public String write(MultipartFile[] files, Map<String, String> params) {
		
		String page = "redirect:/list.do";

		BoardVO vo = new BoardVO();
		vo.setUser_name(params.get("user_name"));
		vo.setSubject(params.get("subject"));;
		vo.setContent(params.get("content"));
		
		board_mapper.write(vo); // 글쓰기
		int idx = vo.getIdx(); // 방금 쓴 글의 번호를 추출.
		
		if(idx > 0) { // 파일 업로드 작업 수행
			fileSave(files, idx);
			page = "redirect:/detail.do?idx=" + idx;
		}
		
		return page;
	}
	
	void fileSave(MultipartFile[] files, int idx) {
		for (MultipartFile file : files) {
			// 1. 파일명 추출
			String ori_filename = file.getOriginalFilename();
			
			// 2. 기존 파일의 확장자만 분리
			String ext = ori_filename.substring(ori_filename.lastIndexOf("."));
			
			// 3. 새 파일명 생성
			String new_filename = UUID.randomUUID() + ext;
			
			// 4. 파일저장
			try {
				byte[] arr = file.getBytes();
				Path path = Paths.get("C:/upload/" + new_filename);
				Files.write(path, arr);
				// 5. 저장내용 files 테이블에 insert
				board_mapper.fileWrite(new_filename, ori_filename, idx);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Transactional
	public void detail(String idx, Model model) {
		
		// 1. upHit
		board_mapper.bHit(idx);
		// 2. bbs 데이터 가져오기
		BoardVO vo = board_mapper.detail(idx);
		// 3. FileList가져오기
		List<FileVO> files = board_mapper.fileList(idx);
		model.addAttribute("info", vo);
		model.addAttribute("files", files);
	}

	public void del(String idx) {
		// 부모테이블 member의 게시글을 삭제하면.... 자식테이블 files도 함께 삭제된다. 서버의 파일은 어떻게 삭제할까?
		
		// 1. files메소드를 통해 DTO에 ori_filename과 new_filename값을 가져온다...
		List<FileVO> fileList = board_mapper.files(idx);
		
		// 2. 게시글의 삭제에 성공했다면..!
		int row = board_mapper.del(idx);
		
		if(row > 0) {
		// 3. List의 값들을 for-each문을 통해 하나씩 꺼내온다.. (파일의 경로를 io에 저장한다.)
			for (FileVO dto : fileList) {
				// 삭제할 파일의 경로..
				File file = new File("C:/upload/" + dto.getNew_filename());
				
				// 삭제할 파일이 있다면..
				if(file.exists()) {
					boolean success = file.delete();
					logger.info(dto.getNew_filename() + "delete : " + success);
				}
			}
		}
		
	}

	public ResponseEntity<Resource> download(String new_filename, String ori_filename) {
		// body
		Resource res = new FileSystemResource("C:/upload/" + new_filename);
		
		// header
		HttpHeaders header = new HttpHeaders();
		header.add("content-type", "application/octet-stream");
		try {
			String filename = URLEncoder.encode(ori_filename, "UTF-8");
			header.add("content-Disposition", "attechment;filename=\"" + filename + "\"");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// body, header, status
		return new ResponseEntity<Resource>(res, header, HttpStatus.OK);
	}
}
