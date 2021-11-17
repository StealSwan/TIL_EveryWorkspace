package spring.day1117.hello;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController {

	@GetMapping("/")
	public String Hello(){
		
		return "hello";
	}
	
	
	//포워드 시키기
	@GetMapping("/apple/list")
	public String apple(Model model) {
		
		//name, msg 보내서 apple에서 출력
		model.addAttribute("name", "홍길동");
		model.addAttribute("msg", "씨가 오늘 점심먹을래?");
		
		return "apple";
	}
	
	
	@GetMapping("/banana/insert")
	public ModelAndView banana() {
		
		//ModelAndView는 request에 저장하기위한 Model과
		//포워드 하기위한 View를 합쳐놓은 클래스입니다
		ModelAndView mview = new ModelAndView();
		
		//request에 저장
		mview.addObject("java", 77);
		mview.addObject("spring", 88);
		
		//포워드할 jsp파일 지정
		mview.setViewName("banana");
		
		return mview;
	}
	
	
	//리퀘스트 - Model 파라미터 & 세션값 HttpSession 파라미터 
	@GetMapping("/orange/delete")
	public String orange(Model model, HttpSession session) {
		
		//request에 저장
		model.addAttribute("name","안수현");
		
		//session에 저장
		session.setAttribute("myid", "admin");
		
		return "orange";
	}
	
	@GetMapping("/shop/detail")
	public String shop() {
		
		return "imgresult";
	}
	
	
	
	//Quiz에서 ModelAndView 방식 추가됨
	//주소가 이렇게 됨
	@GetMapping("/board/add/photo")
	public ModelAndView photo() {
		
		ModelAndView mview = new ModelAndView();
				
		//request에 저장
		mview.addObject("name", "신민아");
		mview.addObject("msg", "갯마을 차차차");
		
		//포워드할 jsp파일 지정 - 이동할 jsp임
		mview.setViewName("photo1");
		
		return mview;
	}
}
