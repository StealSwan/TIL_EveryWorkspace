package member.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import member.data.MemberDao;

@Controller
public class MemberDeleteController {

	@Autowired
	MemberDao dao;
	
	//�α����� ���̵� admin�̸� ������ �������
	//admin�� �ƴϸ� adminfail��
	@GetMapping("/member/delete")
	public String delete(@RequestParam String num, HttpSession session) {
		
		//�������κ��� ���� id�� �ʿ���
		String loginId=(String)session.getAttribute("myid");
		
		if(!loginId.equals("admin")) {
			
			return "/member/adminfail";
			
		} else {
			
			dao.deleteMember(num);
			return "redirect:list";
		}
	}
}
