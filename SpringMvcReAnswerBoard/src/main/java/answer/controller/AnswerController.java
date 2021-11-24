package answer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import board.data.AnswerDao;
import board.data.AnswerDto;

@Controller
public class AnswerController {

	@Autowired
	AnswerDao adao;
	
	@PostMapping("/board/ainsert")
	public String answerInsert(
			@ModelAttribute AnswerDto dto,
			@RequestParam String currentPage) {
		
		//db√ﬂ∞°
		adao.insertAnswer(dto);
		
		return "redirect:content?num="+dto.getNum()+"&currentPage="+currentPage;
	}
	
	
	@GetMapping("/rest/list")
	public String list() {
		
		return "list";
	}
}
