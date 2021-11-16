package spring.day1116.sist;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller	//@Component�� mvc������ @Controller Ŭ���� �ڵ����
public class HomeController {

	//@RequestMapping(value = "/", method = RequestMethod.GET)
	@GetMapping("/")
	public String Hello(Model model) {
		
		//model: request�� ����Ÿ�� �����ϱ� ���� Ŭ����
		//�������� �ڵ��� request.setAttribute�� ����
		model.addAttribute("name", "ȫ�浿");
		model.addAttribute("addr", "����� ���ʱ� �ֿ뱳������");
		
		
		return "result1";	//������/WEB-INF/day1116/result1.jsp
	}
}
