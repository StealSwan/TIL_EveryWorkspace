package answer.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import board.data.AnswerDao;
import board.data.BoardDao;
import board.data.BoardDto;

@RestController
public class AnswerRestController {

	@Autowired
	AnswerDao adao;
	
	@Autowired
	BoardDao dao;
	
	//���̽����� ��ȯ
	@GetMapping("/board/adelete")
	public HashMap<String, Integer> answerDelete(
			@RequestParam int idx,
			@RequestParam String pass){
		
		int check= adao.getCheckPass(idx, pass);
		
		if(check==1) {
			
			adao.deleteAnswer(idx);
		}
		
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		
		map.put("check", check);	//{"check":1}
		
		return map;
	}
	
	
	//��¸���
	@GetMapping("/rest/list1")
	public List<BoardDto> allList(){
		
		List<BoardDto> list = dao.getAllDatas();
		
		for(BoardDto dto: list) {
			
			//������ �ִٸ�
			if(!dto.getPhoto().equals("no")) {
			
				String [] photos = dto.getPhoto().split(",");
				//������ ù��° �������� �ֱ�
				dto.setPhoto(photos[0]);
			}
		}
		
		return list;
	}
}
