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
	
	//�α��� �������� �̵�
	@GetMapping("/login")
	public String login() {
		
		return "/login/loginmain";
	}
	
	
	//�α��� ���� ���� Ȯ�� �� ���� ������ ���� ����
	//���ǵ� �ѱ��
	@PostMapping("/loginprocess")
	public String loginCheck(
			@RequestParam String id,
			@RequestParam String pass,
			HttpSession session) {
		
		int n = dao.loginCheck(id, pass);
		
		if(n==1) {
			//���� ����(2��)
			session.setAttribute("loginok", "yes");
			session.setAttribute("myid", id);
			
			//�������� �����̷�Ʈ
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
