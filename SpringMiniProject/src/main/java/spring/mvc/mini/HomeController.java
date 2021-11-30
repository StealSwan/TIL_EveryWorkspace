package spring.mvc.mini;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	//home 설정 - index에서 가장먼저 시작해서 home으로 옴 - layout/main으로 감
	@GetMapping({"/","/home"})
	public String mainView() {
		
		//home.tiles에서 지정 - 안쓰이는 하나하나씩
		//return "home.tiles";
		
		//보편적인 와일드 카드 스타일
		return "/layout/main";
	}
	
	
	//메일 보내기
	@GetMapping("/mail/send")
	public String mailform() {
		
		return "/mail/form";
	}
}
