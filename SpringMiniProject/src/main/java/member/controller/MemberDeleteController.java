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
	
	//로그인한 아이디가 admin이면 삭제후 명단으로
	//admin이 아니면 adminfail로
	@GetMapping("/member/delete")
	public String delete(@RequestParam String num, HttpSession session) {
		
		//세션으로부터 받은 id가 필요함
		String loginId=(String)session.getAttribute("myid");
		
		if(!loginId.equals("admin")) {
			
			return "/member/adminfail";
			
		} else {
			
			dao.deleteMember(num);
			return "redirect:list";
		}
	}
}
