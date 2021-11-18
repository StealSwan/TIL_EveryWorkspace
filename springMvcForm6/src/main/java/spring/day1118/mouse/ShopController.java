package spring.day1118.mouse;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import spring.shop.model.shopDto;

@Controller
public class ShopController {

	@GetMapping("/shop/list")
	public String shop() {
		
		return "shop/list";
	}
	
	
	@GetMapping("/shop/form2")
	public String form2() {
		
		return "shop/shopform";
	}
	
	
	@PostMapping("/shop/process2")
	public String process2(
			@ModelAttribute shopDto dto) {
			//model shopDto에 저장
			//만약 원하는 이름있으면 @ModelAttribute("name")
		
			//ModelAndView로 mview.addObject("dto",dto) 이런식으로 하지 않았기때문에 dto로 안됨
		
		return "shop/shopresult";
	}
	
	
	///////////5번째
	
	@GetMapping("/shop/form3")
	public String form3() {
		
		return "shop/mapform";
	}
	
	
	@PostMapping("/shop/process3")
	public ModelAndView process3(
			@RequestParam Map<String, String> map) {
		
		ModelAndView model = new ModelAndView();
		model.addObject("name",map.get("name"));
		model.addObject("java",map.get("java"));
		model.addObject("jquery",map.get("jquery"));
		model.addObject("spring",map.get("spring"));
		
		model.setViewName("shop/mapresult");
		return model;
	}
}
