package kr.co.gudi.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import kr.co.gudi.dao.MemberDAO;
import kr.co.gudi.dto.MemberDTO;

@Service
public class MemberService {
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired MemberDAO memberDAO;

	public List<MemberDTO> list(Map<String, String> param) {
		
		return memberDAO.list(param);
	}

	public String join(MemberDTO dto, Model model) {
		
		String page = "join";
		logger.info("email : " + dto.getEmail());
		
		int row = memberDAO.join(dto);
		
		if(row > 0) {
			page = "redirect:/list.do";
		}else {
			model.addAttribute("msg", "회원가입에 실패했습니다.");
		}
		
		return page;
	}

	public void multi(List<String> userName, Model model) {
		List<MemberDTO> list = memberDAO.multi(userName);
		model.addAttribute("list", list);
		
	}

	public MemberDTO detail(String id) {
		return memberDAO.detail(id);
	}

	public Map<String, Object> update(Map<String, String> params) {
		int row = memberDAO.update(params);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", row > 0 ? true : false);
		
		return map;
	}
	
	
	
}
