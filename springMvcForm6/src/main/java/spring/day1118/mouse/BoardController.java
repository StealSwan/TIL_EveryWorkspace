package spring.day1118.mouse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
//매번 중복되는 매핑을 한번만 써준후 나머지는 뒤의 주소값만 써줄수있다
//그러나 다른 폴더는 사용하지 못하기에 선택적으로 사용하는 방법이다
@RequestMapping("/board")	
public class BoardController {

	@GetMapping("/form1")
	public String form1() {
		
		return "board/writeform";	//WEB-INF 뒤에 폴더 설정 안했으므로 폴더명/jsp 파일명 구조로
	}
	
	
	@PostMapping("/process1")
	public ModelAndView process1(
			@RequestParam String name,
			@RequestParam String date,
			@RequestParam String gender,
			@RequestParam(name = "pageNum", defaultValue = "1") int currentPage,
			//required = true가 기본값, 항목이 없어도 에러 안나려면 false
			@RequestParam(required = false) String msg)
			
	{
		
		ModelAndView model = new ModelAndView();
		
		model.addObject("name", name);
		model.addObject("date", date);
		model.addObject("gender", gender);
		model.addObject("msg", msg);
		model.addObject("currentPage", currentPage);
		
		//포워드
		model.setViewName("board/result1");
		
		return model;
	}
	
	
	@GetMapping("/result2")
	public String result2(Model model) {
		
		//여기서 이미지를 보낼수도 있다
		model.addAttribute("imgsrc", "../image/img1.png");
		model.addAttribute("title", "전쟁발발");
		
		return "board/result2";
	}
}
