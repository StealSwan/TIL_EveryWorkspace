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
	
	
	//������ư ������ - actionó�� - num���� �Ķ���ͷ� ����
	//���� ����
	@GetMapping("/member/updateform")
	public ModelAndView updateform(@RequestParam String num) {
		
		ModelAndView mview = new ModelAndView();
		
		MemberDto dto = dao.getMember(num);
		
		mview.addObject("dto", dto);
		
		//������
		mview.setViewName("/member/updateform");
		
		return mview;
	}
	
	
	//���� ������Ʈ - ���üũ ������
	@PostMapping("/member/update")
	public String update(@ModelAttribute MemberDto dto) {
		
		//����� �´��� üũ
		int n = dao.passCheck(dto.getNum(), dto.getPass());
		
		if(n==1) {
			//����� �´°��� ������ ������� �̵�
			dao.updateMember(dto);
			return "redirect:list";
		
		} else {
			
			//����� Ʋ���� passfail
			return "/member/passfail";
		}
	}
}
