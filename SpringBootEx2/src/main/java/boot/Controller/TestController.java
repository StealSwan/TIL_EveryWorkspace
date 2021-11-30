package boot.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TestController {

	@GetMapping("/")
	public ModelAndView list() {
		
		ModelAndView mview = new ModelAndView();
		
		mview.addObject("name","이효리");
		mview.addObject("addr","서울시 강남구 역삼동 쌍용교육센터");
		mview.addObject("hp","010-444-9999");
		
		//WEB-INF/board/list.jsp
		mview.setViewName("list");
		
		return mview;

	}
	
	
	
	//홈 추가
	@GetMapping("/sist/home")
	public String home() {
		
		return "home";
	}
}
