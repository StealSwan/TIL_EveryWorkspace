package spring.mvc.quiz;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FormController {

	//시작할때 data/form으로 매핑 주소가 된 페이지를 열도록 함
	@GetMapping("/data/form")
	public String form1() {
		
		return "form1";
	}
	
	
	//!!!get방식으로 읽어옴 - ModelAndView 사용!!!
	@GetMapping("/data/read1")
	public ModelAndView read1(
			@RequestParam("name") String name,
			@RequestParam int age,	//폼태그의 name과 변수가 같으면 생략가능
			@RequestParam(value = "msg", defaultValue = "Have a Nice Day~!") String msg
			) {
		
		ModelAndView mview = new ModelAndView();
		
		//request에 저장
		mview.addObject("name",name);
		mview.addObject("age", age);
		mview.addObject("msg", msg);
		
		//포워드
		mview.setViewName("procget");
		
		return mview;
	}
	
	
	//!!!포스트방식!!!
	//한글은 web.xml에 넣어주어야 한다
	//!!!ModelAttribute - DTO를 자동적으로 set 해주는 아주편한 기능이다!!!
	@PostMapping("/data/read2")
	public ModelAndView read2(@ModelAttribute TestDto dto) {
		
		ModelAndView mview = new ModelAndView();
		
		//dto 값으로 저장소 선언
		mview.addObject("dto", dto);
		
		//포워드
		mview.setViewName("procpost");
		
		return mview;
	}
	
	
	//Map보내기
	@PostMapping("/data/read3")
	public ModelAndView read3(@RequestParam Map<String, String> map) {
		
		ModelAndView model = new ModelAndView();
		
		String sang = map.get("sang");
		String price = map.get("price");
		
		String data = sang + "의 가격은 " + price + "입니다";
		model.addObject("data", data);
		model.setViewName("procmap");
		
		return model;
	}
	
}
