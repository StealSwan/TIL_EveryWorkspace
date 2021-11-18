package spring.day1118.mouse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
//�Ź� �ߺ��Ǵ� ������ �ѹ��� ������ �������� ���� �ּҰ��� ���ټ��ִ�
//�׷��� �ٸ� ������ ������� ���ϱ⿡ ���������� ����ϴ� ����̴�
@RequestMapping("/board")	
public class BoardController {

	@GetMapping("/form1")
	public String form1() {
		
		return "board/writeform";	//WEB-INF �ڿ� ���� ���� �������Ƿ� ������/jsp ���ϸ� ������
	}
	
	
	@PostMapping("/process1")
	public ModelAndView process1(
			@RequestParam String name,
			@RequestParam String date,
			@RequestParam String gender,
			@RequestParam(name = "pageNum", defaultValue = "1") int currentPage,
			//required = true�� �⺻��, �׸��� ��� ���� �ȳ����� false
			@RequestParam(required = false) String msg)
			
	{
		
		ModelAndView model = new ModelAndView();
		
		model.addObject("name", name);
		model.addObject("date", date);
		model.addObject("gender", gender);
		model.addObject("msg", msg);
		model.addObject("currentPage", currentPage);
		
		//������
		model.setViewName("board/result1");
		
		return model;
	}
	
	
	@GetMapping("/result2")
	public String result2(Model model) {
		
		//���⼭ �̹����� �������� �ִ�
		model.addAttribute("imgsrc", "../image/img1.png");
		model.addAttribute("title", "����߹�");
		
		return "board/result2";
	}
}
