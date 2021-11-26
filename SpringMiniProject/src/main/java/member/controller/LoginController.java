package member.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DaoSupport;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import member.data.MemberDao;

@Controller
public class LoginController {
	
	@Autowired
	MemberDao dao;
	
	//로그인 메인으로 이동
	@GetMapping("/login")
	public String login() {
		
		return "/login/loginmain";
	}
	
	
	//로그인 성공 여부 확인 후 어디로 포워드 할지 결정
	//세션도 넘긴다
	@PostMapping("/loginprocess")
	public String loginCheck(
			@RequestParam String id,
			@RequestParam String pass,
			HttpSession session) {
		
		int n = dao.loginCheck(id, pass);
		
		if(n==1) {
			//세션 저장(2개)
			session.setAttribute("loginok", "yes");
			session.setAttribute("myid", id);
			
			//메인으로 리다이렉트
			return "redirect:home";
		
		} else {
			
			return "/login/loginfail";
		}
	}
	
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		
		session.removeAttribute("loginok");
		session.removeAttribute("myid");
		
		return "redirect:home";
	}
}
