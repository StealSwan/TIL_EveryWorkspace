/**
 * 
 */
package spring.mvc.quiz;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


//!!!!!!!!!!!!!! controller ������ �־��ֱ�
@Controller
public class quizController {

	//����1: ������ ��Ű��
	@GetMapping("/happy")
	public String happy() {
		
		return "happy";
	}
	
	
	//����2: ���� �Բ� ������ ��Ű��
	@GetMapping("/nice/hi")
	public String nice(Model model) {
		
		model.addAttribute("name", "ȫ�浿");
		model.addAttribute("msg", "�ȳ��ϼ���");
		
		return "nice";
	}
}
