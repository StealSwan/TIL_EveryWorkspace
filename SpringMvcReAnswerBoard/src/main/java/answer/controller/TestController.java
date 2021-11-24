package answer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import board.data.BoardDao;
import board.data.BoardDto;

@Controller
public class TestController {

	@Autowired
	BoardDao dao;
	
	
	@GetMapping("/sist/list")
	public @ResponseBody List<BoardDto> list(){
		
		return dao.getList(0, 5);
	}
	
	
	@GetMapping("/sist/data")
	public @ResponseBody BoardDto readdata(@RequestParam int num) {
		
		return dao.getData(num);
	}
}
