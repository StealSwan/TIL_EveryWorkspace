package boot.Controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.sym.Name;

@Controller
public class FormReadController {

	@PostMapping("/sist/read1")
	public ModelAndView readform1(
			@RequestParam String name,
			@RequestParam int java,
			@RequestParam int spring) {
		
		ModelAndView mview = new ModelAndView();
		
		//request에 저장
		mview.addObject("name", name);
		mview.addObject("java", java);
		mview.addObject("spring", spring);
		mview.addObject("tot", java+spring);
		mview.addObject("avg",(java+spring)/2.0);
		
		mview.setViewName("result/result1");
		
		return mview;
	}
	
	
	//read2 - Dto 전달 방식
	@PostMapping("/sist/read2")
	public String formread2(@ModelAttribute PersonDto dto) {
		
		return "result/result2";
	}
	
	
	//read3 - Map형식 전달
	@PostMapping("/sist/read3")
	public ModelAndView formRead3(
			@RequestParam Map<String, String> map) {
		
		ModelAndView model = new ModelAndView();
		
		model.addObject("name", map.get("name"));
		model.addObject("blood", map.get("blood"));
		model.addObject("hp", map.get("hp"));
		
		model.setViewName("result/result3");
		
		return model;
	}
}
