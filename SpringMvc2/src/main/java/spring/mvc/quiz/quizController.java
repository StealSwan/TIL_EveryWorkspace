/**
 * 
 */
package spring.mvc.quiz;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


//!!!!!!!!!!!!!! controller 무조건 넣어주기
@Controller
public class quizController {

	//문제1: 포워드 시키기
	@GetMapping("/happy")
	public String happy() {
		
		return "happy";
	}
	
	
	//문제2: 값과 함께 포워드 시키기
	@GetMapping("/nice/hi")
	public String nice(Model model) {
		
		model.addAttribute("name", "홍길동");
		model.addAttribute("msg", "안녕하세요");
		
		return "nice";
	}
}
