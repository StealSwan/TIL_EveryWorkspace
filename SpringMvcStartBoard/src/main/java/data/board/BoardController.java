package data.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
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
}
