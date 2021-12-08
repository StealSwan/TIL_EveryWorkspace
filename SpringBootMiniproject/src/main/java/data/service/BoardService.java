package data.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import data.dto.BoardDto;
import data.mapper.BoardMapper;

@Service
public class BoardService {

	@Autowired
	BoardMapper mapper;
	
	public int getTotalCount() {
		
		return mapper.getTotalCount();
	}
	
	
	//조회수
	public void updateReadCount(String num) {
		
		mapper.updateReadCount(num);
	}
	
	
	//하나의 num에 해당하는 Data
	public BoardDto getData(String num) {
	
		return mapper.getData(num);
	}
	
	
	//get Max Num
	public int getMaxNum() {
		
		return mapper.getMaxNum();
	}
	
	
	//콘텐트로 이동 - Max Num 알아야함
	public List<BoardDto> getList(int start, int perpage){
		
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		
		map.put("start", start);
		map.put("perpage", perpage);
		
		return mapper.getList(map);
	}
	
	
	//인서트
	public void insertBoard(BoardDto dto) {
	
		mapper.insertBoard(dto);
	}
	
	
	//업데이트
	public void updateBoard(BoardDto dto) {
	
		mapper.updateBoard(dto);
	}
	
	
	//삭제
	public void deleteBoard(String num) {
		
		mapper.deleteBoard(num);
	}
}
