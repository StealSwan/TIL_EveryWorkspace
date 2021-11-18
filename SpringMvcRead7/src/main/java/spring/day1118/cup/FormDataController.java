package spring.day1118.cup;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import spring.day1118.data.ShopDto;

@Controller
@RequestMapping("/shop")
public class FormDataController {

	@GetMapping("/form1")
	public String form1()
	{
		return "day1118/form1";
	}
	@PostMapping("/read1")
	public ModelAndView read1(
			@RequestParam String name,
			@RequestParam String gender,
			@RequestParam String addr
			)
	{
		ModelAndView model=new ModelAndView();
		model.addObject("name", name);
		model.addObject("gender",gender);
		model.addObject("addr", addr);
		
		model.setViewName("day1118/result1");
		return model;
	}
	
	@GetMapping("/form2")
	public String form2()
	{
		return "day1118/form2";
	}
	@PostMapping("/read2")
	public String read2(@ModelAttribute ShopDto dto)
	{
		//@ModelAttribute는 폼의 데이터 읽어서 dto에 넣고 model에저장
		return "day1118/result2";
	}
	
	@GetMapping("/form3")
	public String form3()
	{
		return "day1118/form3";
	}
	@PostMapping("/read3")
	public ModelAndView read3(
			@RequestParam Map<String , String> map
			//폼태그의 name이 key값 ,입력값이 value값
			)
	{
		ModelAndView mview=new ModelAndView();
		
		mview.addObject("sang", map.get("sang"));
		mview.addObject("color", map.get("color"));
		mview.addObject("price", map.get("price"));
		mview.addObject("image", map.get("image"));
		
		mview.setViewName("day1118/result3");
		return mview;
	}
}
