package spring.mvc.reboard;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import board.data.AnswerDao;
import board.data.AnswerDto;
import board.data.BoardDao;
import board.data.BoardDto;

@Controller
public class BoardContentController {

	@Autowired
	BoardDao dao;
	
	@Autowired
	AnswerDao adao;
	
	
	@GetMapping("/board/content")
	public ModelAndView content(
			@RequestParam int num,
			@RequestParam int currentPage) {
		
		ModelAndView mview = new ModelAndView();
		
		dao.updateReadCount(num);
		
		BoardDto dto = dao.getData(num);
		
		mview.addObject("dto", dto);
		mview.addObject("currentPage", currentPage);
		
		
		//num에 해당하는 댓글을 db에서 가져와 보낸다
		List<AnswerDto> alist = adao.getAnswerList(num);
		
		//댓글이 있을때만 보낸다
		mview.addObject("acount",alist.size());
		mview.addObject("alist",alist);
		
		
		mview.setViewName("content");
		
		return mview;
	}
}
