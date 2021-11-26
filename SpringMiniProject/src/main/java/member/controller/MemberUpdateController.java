package member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import member.data.MemberDao;
import member.data.MemberDto;

@Controller
public class MemberUpdateController {

	@Autowired
	MemberDao dao;
	
	
	//수정버튼 누르면 - action처리 - num값을 파라미터로 받음
	//폼에 세팅
	@GetMapping("/member/updateform")
	public ModelAndView updateform(@RequestParam String num) {
		
		ModelAndView mview = new ModelAndView();
		
		MemberDto dto = dao.getMember(num);
		
		mview.addObject("dto", dto);
		
		//포워드
		mview.setViewName("/member/updateform");
		
		return mview;
	}
	
	
	//실제 업데이트 - 비번체크 맞으면
	@PostMapping("/member/update")
	public String update(@ModelAttribute MemberDto dto) {
		
		//비번이 맞는지 체크
		int n = dao.passCheck(dto.getNum(), dto.getPass());
		
		if(n==1) {
			//비번이 맞는경우는 수정후 목록으로 이동
			dao.updateMember(dto);
			return "redirect:list";
		
		} else {
			
			//비번이 틀리면 passfail
			return "/member/passfail";
		}
	}
}
