package spring.mvc.mini;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	//home ���� - index���� ������� �����ؼ� home���� �� - layout/main���� ��
	@GetMapping({"/","/home"})
	public String mainView() {
		
		//home.tiles���� ���� - �Ⱦ��̴� �ϳ��ϳ���
		//return "home.tiles";
		
		//�������� ���ϵ� ī�� ��Ÿ��
		return "/layout/main";
	}
	
	
	//���� ������
	@GetMapping("/mail/send")
	public String mailform() {
		
		return "/mail/form";
	}
}
