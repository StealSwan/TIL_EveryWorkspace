package spring.mvc.munje;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

	//�����ּ� ����
	//WEB-INF/quiz/testform.jsp ����ֱ�
	@GetMapping("/")
	public String testform() {
		
		return "testform";
	}
	
	
	@PostMapping("/sawoninfo")
	public ModelAndView sawoninfo(@ModelAttribute SawonDto dto) {
		
		ModelAndView mview = new ModelAndView();
		
		//dto ������ ����� ����
		mview.addObject("dto", dto);
		
		//������
		mview.setViewName("sawoninfo");
		
		return mview;
	}
}
