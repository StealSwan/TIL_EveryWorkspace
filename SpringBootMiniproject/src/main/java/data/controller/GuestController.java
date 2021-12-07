package data.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GuestController {

	@GetMapping("/guest/list")
	public ModelAndView list() {
		
		ModelAndView mview = new ModelAndView();
		
		mview.setViewName("/guest/list");
		
		return mview;
	}
}
