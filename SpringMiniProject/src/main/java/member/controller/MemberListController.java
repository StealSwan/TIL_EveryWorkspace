package member.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import member.data.MemberDao;
import member.data.MemberDto;

@Controller
public class MemberListController {
	
	@Autowired
	MemberDao dao;
	
	
	//list로 이동 - 출력할 요소들 담아주기
	@GetMapping("/member/list")
	public ModelAndView list() {
		
		ModelAndView model = new ModelAndView();
		
		//출력 정보들 List로
		List<MemberDto> list = dao.getAllMembers();
		
		//전부 출력한 list들 model Object 형식으로 담는다
		model.addObject("list",list);
		
		//포워드 - return으로 못함 - model 형식 리턴
		model.setViewName("/member/memberlist");
		
		//return값으로는 model
		return model;
	}
	
	
	
}
