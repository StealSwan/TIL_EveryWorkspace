package board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import board.data.BoardDao;
import board.data.BoardDto;

@Controller
public class BoardContentController {

	@Autowired
	BoardDao dao;
	
	
	@GetMapping("/board/content")
	public String content(
			Model model,
			@RequestParam String currentPage,
			@RequestParam int num) {
		
		dao.updateReadCount(num);
		
		BoardDto dto = dao.getData(num);
		
		model.addAttribute("dto",dto);
		model.addAttribute("currentPage",currentPage);
		
		return "/board/content";
		
	}
}
