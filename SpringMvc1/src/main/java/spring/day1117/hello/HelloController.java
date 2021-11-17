package spring.day1117.hello;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController {

	@GetMapping("/")
	public String Hello(){
		
		return "hello";
	}
	
	
	//������ ��Ű��
	@GetMapping("/apple/list")
	public String apple(Model model) {
		
		//name, msg ������ apple���� ���
		model.addAttribute("name", "ȫ�浿");
		model.addAttribute("msg", "���� ���� ���ɸ�����?");
		
		return "apple";
	}
	
	
	@GetMapping("/banana/insert")
	public ModelAndView banana() {
		
		//ModelAndView�� request�� �����ϱ����� Model��
		//������ �ϱ����� View�� ���ĳ��� Ŭ�����Դϴ�
		ModelAndView mview = new ModelAndView();
		
		//request�� ����
		mview.addObject("java", 77);
		mview.addObject("spring", 88);
		
		//�������� jsp���� ����
		mview.setViewName("banana");
		
		return mview;
	}
	
	
	//������Ʈ - Model �Ķ���� & ���ǰ� HttpSession �Ķ���� 
	@GetMapping("/orange/delete")
	public String orange(Model model, HttpSession session) {
		
		//request�� ����
		model.addAttribute("name","�ȼ���");
		
		//session�� ����
		session.setAttribute("myid", "admin");
		
		return "orange";
	}
	
	@GetMapping("/shop/detail")
	public String shop() {
		
		return "imgresult";
	}
	
	
	
	//Quiz���� ModelAndView ��� �߰���
	//�ּҰ� �̷��� ��
	@GetMapping("/board/add/photo")
	public ModelAndView photo() {
		
		ModelAndView mview = new ModelAndView();
				
		//request�� ����
		mview.addObject("name", "�Źξ�");
		mview.addObject("msg", "������ ������");
		
		//�������� jsp���� ���� - �̵��� jsp��
		mview.setViewName("photo1");
		
		return mview;
	}
}
