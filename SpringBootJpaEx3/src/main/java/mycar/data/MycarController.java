package mycar.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MycarController {

	@Autowired
	MycarDao dao;
	
	@GetMapping("/carform")
	public String form() {
		
		return "caraddform";
	}
}
