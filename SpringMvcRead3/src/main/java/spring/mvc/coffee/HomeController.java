package spring.mvc.coffee;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

	//1��
	@GetMapping("/")
	public String start() {
		
		//redirect�� mapping �ּ�
		return "redirect:login/form";	//�����ּ� - �Ʒ� ����� ����
	}
	
	
	//2��
	//����� ������
	@GetMapping("/login/form")
	public String form() {
		
		return "form";
	}
	
	
	//3��
	@GetMapping("/login/read")
	public String process(Model model, 
			@RequestParam(value="myid") String id,
			@RequestParam (value="mypass") String pass) {
		
		
		model.addAttribute("id", id);
		
		String msg="";
		if(pass.equals("1234"))	
			msg="�α��� ����!!";
		else 
			msg="�α��ν���";
			
		model.addAttribute("msg",msg);
		
		return "result";
	}
	
}
