package data.mapper;

import java.util.HashMap;
import java.util.List;

import data.dto.BoardDto;

public interface BoardMapper {

	//전체 글 갯수
	public int getTotalCount();
	
	//조회수
	public void updateReadCount(String num);
	
	//하나의 num에 해당하는 Data
	public BoardDto getData(String num);
	
	//콘텐트로 이동 - Max Num 알아야함
	public int getMaxNum();
	
	//리스트 출력
	public List<BoardDto> getList(HashMap<String, Integer> map);
	
	//인서트
	public void insertBoard(BoardDto dto);
}
