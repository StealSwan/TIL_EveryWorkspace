package spring.mvc.coffee;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

	//1번
	@GetMapping("/")
	public String start() {
		
		//redirect는 mapping 주소
		return "redirect:login/form";	//매핑주소 - 아래 저기로 가라
	}
	
	
	//2번
	//여기로 도착함
	@GetMapping("/login/form")
	public String form() {
		
		return "form";
	}
	
	
	//3차
	@GetMapping("/login/read")
	public String process(Model model, 
			@RequestParam(value="myid") String id,
			@RequestParam (value="mypass") String pass) {
		
		
		model.addAttribute("id", id);
		
		String msg="";
		if(pass.equals("1234"))	
			msg="로그인 성공!!";
		else 
			msg="로그인실패";
			
		model.addAttribute("msg",msg);
		
		return "result";
	}
	
}
