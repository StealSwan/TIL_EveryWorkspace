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

	//dao 자동주입
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
		
		
		//포워드
		model.setViewName("board/list");

		return model;
	}
	
	
	
	@GetMapping("/writeform")	//매핑주소 - 글쓰기 버튼이랑 맞아야함
	public String addForm() {
		
		return "board/writeform";	//폴더명의 파일이 나와야함
	}
	
	
	@PostMapping("/write")
	public String insert(@ModelAttribute BoardDto dto) {
		
		dao.insertBoard(dto);
		
		//dto를 담아서 다시 list로 돌아가라
		//포워드할 필요가 없다
		return "redirect:list";	//list() 컨트롤러로 다시 이동
	}
}
