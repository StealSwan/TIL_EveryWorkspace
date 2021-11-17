package spring.mvc.quiz;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FormController {

	//�����Ҷ� data/form���� ���� �ּҰ� �� �������� ������ ��
	@GetMapping("/data/form")
	public String form1() {
		
		return "form1";
	}
	
	
	//!!!get������� �о�� - ModelAndView ���!!!
	@GetMapping("/data/read1")
	public ModelAndView read1(
			@RequestParam("name") String name,
			@RequestParam int age,	//���±��� name�� ������ ������ ��������
			@RequestParam(value = "msg", defaultValue = "Have a Nice Day~!") String msg
			) {
		
		ModelAndView mview = new ModelAndView();
		
		//request�� ����
		mview.addObject("name",name);
		mview.addObject("age", age);
		mview.addObject("msg", msg);
		
		//������
		mview.setViewName("procget");
		
		return mview;
	}
	
	
	//!!!����Ʈ���!!!
	//�ѱ��� web.xml�� �־��־�� �Ѵ�
	//!!!ModelAttribute - DTO�� �ڵ������� set ���ִ� �������� ����̴�!!!
	@PostMapping("/data/read2")
	public ModelAndView read2(@ModelAttribute TestDto dto) {
		
		ModelAndView mview = new ModelAndView();
		
		//dto ������ ����� ����
		mview.addObject("dto", dto);
		
		//������
		mview.setViewName("procpost");
		
		return mview;
	}
	
	
	//Map������
	@PostMapping("/data/read3")
	public ModelAndView read3(@RequestParam Map<String, String> map) {
		
		ModelAndView model = new ModelAndView();
		
		String sang = map.get("sang");
		String price = map.get("price");
		
		String data = sang + "�� ������ " + price + "�Դϴ�";
		model.addObject("data", data);
		model.setViewName("procmap");
		
		return model;
	}
	
}
