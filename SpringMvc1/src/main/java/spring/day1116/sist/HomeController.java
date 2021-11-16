package spring.day1116.sist;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller	//@Component가 mvc에서는 @Controller 클래스 자동등록
public class HomeController {

	//@RequestMapping(value = "/", method = RequestMethod.GET)
	@GetMapping("/")
	public String Hello(Model model) {
		
		//model: request에 데이타를 저장하기 위한 클래스
		//서블릿에서 코딩한 request.setAttribute와 같다
		model.addAttribute("name", "홍길동");
		model.addAttribute("addr", "서울시 서초구 쌍용교육센터");
		
		
		return "result1";	//포워드/WEB-INF/day1116/result1.jsp
	}
}
