package answer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import board.data.BoardDao;
import board.data.BoardDto;

@RestController	//restApi Àü¿ë
public class TestRestController {

	@Autowired
	BoardDao dao;
	
	
	@GetMapping("/sist/list2")
	public @ResponseBody List<BoardDto> list(){
		
		return dao.getList(0, 5);
	}
	
	
	@GetMapping("/sist/data2")
	public @ResponseBody BoardDto readdata(@RequestParam int num) {
		
		return dao.getData(num);
	}
}
