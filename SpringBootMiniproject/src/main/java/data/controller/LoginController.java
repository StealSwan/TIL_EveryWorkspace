package data.controller;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import data.mapper.MemberMapper;

@Controller
public class LoginController {

	@Autowired
	MemberMapper mapper;
	
	//로그인 메인창
	@GetMapping("/login/main")
	public String loginForm(HttpSession session, Model model) {
		
		//폼에서 아이디를 얻어줌
		String myid = (String)session.getAttribute("myid");
		
		//로그인 상태인지 아닌지 체크
		String loginok = (String)session.getAttribute("loginok");
		
		
		if(loginok==null)
			return "/login/loginform";
		
		else {
			
			//로그인중일때는 로그인한 이름 저장
			String name = mapper.getName(myid);
			model.addAttribute("name",name);
		}
			
		return "/login/logoutform";
	}
	
	
	//로그인 프로세스
	@PostMapping("/login/loginprocess")
	public String loginProcess(
			@RequestParam(required = false) String cbsave,
			@RequestParam String id,
			@RequestParam String pass,
			HttpSession session) {
		
		HashMap<String, String> map = new HashMap<String, String>();
		
		map.put("id",id);
		map.put("pass", pass);
		
		int check = mapper.login(map);
		
		//로그인 성공시
		if (check==1) {
			
			session.setAttribute("myid", id);
			session.setAttribute("loginok", "yes");
			session.setAttribute("saveok", cbsave);
			
			//체크했을때 on, 안하면 null
			return "redirect:main";

		} else {
			
			return "/member/passfail";
		}
		
	}
	
	
	//로그아웃
	@GetMapping("/login/logoutprocess")
	public String logout(HttpSession session) {
		
		session.removeAttribute("loginok");
		
		return "redirect:main";
	}
}
