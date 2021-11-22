package data.board;

import java.util.List;

import org.apache.taglibs.standard.lang.jstl.test.beans.PublicBean1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BoardController {

	//dao �ڵ�����
	@Autowired
	private BoardDaoInter dao;
	
	
	@GetMapping("/list")
	public ModelAndView list() {
		
		ModelAndView model=new ModelAndView();
		
		//totalCount
		int totalCount = dao.getTotalCount();
		
		//AllDatas
		List<BoardDto> list = dao.getAllDatas();
		
		//totalCount
		model.addObject("totalCount", totalCount);
		
		//AllDatas
		model.addObject("list", list);
		
		
		//������
		model.setViewName("board/list");

		return model;
	}
	
	
	
	@GetMapping("/writeform")	//�����ּ� - �۾��� ��ư�̶� �¾ƾ���
	public String addForm() {
		
		return "board/writeform";	//�������� ������ ���;���
	}
	
	
	@PostMapping("/write")
	public String insert(@ModelAttribute BoardDto dto) {
		
		dao.insertBoard(dto);
		
		//dto�� ��Ƽ� �ٽ� list�� ���ư���
		//�������� �ʿ䰡 ����
		return "redirect:list";	//list() ��Ʈ�ѷ��� �ٽ� �̵�
	}
	
	
	//form ���� ������ �ƴ϶� href �������� �ѱ�
	@GetMapping("/content")
	public ModelAndView content(@RequestParam String num) {
		
		ModelAndView model = new ModelAndView();
		
		BoardDto dto = dao.getData(num);
		model.addObject("dto", dto);
		
		//������
		model.setViewName("board/content");

		return model;
	}
	
	
	//updateform�� ��Ÿ����
	@GetMapping("/updateform")
	public ModelAndView updateform(@RequestParam String num) {
		
		ModelAndView model = new ModelAndView();

		BoardDto dto = dao.getData(num);
		model.addObject("dto", dto);
		
		//������
		model.setViewName("board/updateform");
		
		return model;
	}
	
	
	//���� ������Ʈ �ǰ� �ؾ��� - ó���� �ϸ� ��
	@PostMapping("/update")
	public String update(@ModelAttribute BoardDto dto) {
		
		dao.updateBoard(dto);
		
		return "redirect:list";
	}
	
	
	@GetMapping("/delete")
	public String delete(@RequestParam String num) {
		
		dao.deleteBoard(num);
		return "redirect:list";
	}
}
