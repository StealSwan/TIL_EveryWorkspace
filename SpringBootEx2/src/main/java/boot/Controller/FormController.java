package boot.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FormController {

	//매핑 주소
	@GetMapping("/sist/form1")
	public String form1() {
		
		return "form/form1";
	}
	
	
	//매핑 주소
	@GetMapping("/sist/form2")
	public String form2() {
		
		return "form/form2";
	}
	
	
	//매핑 주소
	@GetMapping("/sist/form3")
	public String form3() {
		
		return "form/form3";
	}	
}
