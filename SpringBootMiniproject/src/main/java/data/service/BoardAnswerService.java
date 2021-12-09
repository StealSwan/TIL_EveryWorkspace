package data.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import data.dto.BoardAnswerDto;
import data.mapper.BoardAnswerMapper;

@Service
public class BoardAnswerService {

	@Autowired
	BoardAnswerMapper mapper;
	
	//삽입
	public void insertAnswer(BoardAnswerDto dto){
		
		mapper.insertAnswer(dto);
	}
	
	
	//게시물에 대한 전체 출력
	public List<BoardAnswerDto> getAllAnswers(String num){
		
		return mapper.getAllAnswers(num);
	}
	
	//내가 쓴 댓글
	public BoardAnswerDto getAnswer(String idx) {
		
		return mapper.getAnswer(idx);
	}
	
	
	//수정
	public void updateAnswer(BoardAnswerDto dto) {
		
		mapper.updateAnswer(dto);
	}
	
	
	//삭제
	public void deleteAnswer(String idx) {
		
		mapper.deleteAnswer(idx);
	}
}
