package spring.mvc.munje;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

	//시작주소 매핑
	//WEB-INF/quiz/testform.jsp 띄워주기
	@GetMapping("/")
	public String testform() {
		
		return "testform";
	}
	
	
	@PostMapping("/sawoninfo")
	public ModelAndView sawoninfo(@ModelAttribute SawonDto dto) {
		
		ModelAndView mview = new ModelAndView();
		
		//dto 값으로 저장소 선언
		mview.addObject("dto", dto);
		
		//포워드
		mview.setViewName("sawoninfo");
		
		return mview;
	}
}
