package member.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import member.data.MemberDao;
import member.data.MemberDto;

@Controller
public class MemberWrtieController {
	
	@Autowired
	MemberDao dao;
	
	
	//폼으로 이동
	@GetMapping("/member/form")
	public String writeform() {
		
		return "/member/memberform";	//1(폴더명)/2(파일명)
	}
	
	
	//일반 컨트롤러에서 JSON형태로 넘기기 - id 중복체크
	@GetMapping("/member/idcheck")
	public @ResponseBody Map<String, Integer> idCheck(
			@RequestParam String id){ //id값 받아오기
		
		//map은 인터페이스 이므로 생성 X - HashMap으로 함
		Map<String, Integer> map = new HashMap<String, Integer>();
		
		//dao 호출
		int count = dao.getIdCount(id);
		
		//0인지 1인지
		map.put("count", count); 
		
		return map;
	}
	
	
	//insert 시키기 - form 제출 [여기가 action] 
	//return값은 페이지 포워드를 위한 것
	@PostMapping("/member/insert")
	public String insert(@ModelAttribute MemberDto dto) {
		
		//dao 호출해서 insert 작업이 이루어짐
		dao.insertMember(dto);
		
		//포워드
		return "redirect:list";
	}	
}
