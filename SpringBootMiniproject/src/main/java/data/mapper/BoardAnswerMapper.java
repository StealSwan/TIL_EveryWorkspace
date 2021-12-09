package data.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import data.dto.BoardAnswerDto;

@Mapper
public interface BoardAnswerMapper {

	//삽입
	public void insertAnswer(BoardAnswerDto dto);
	
	//게시물에 대한 전체 출력
	public List<BoardAnswerDto> getAllAnswers(String num);
	
	//내가 쓴 댓글
	public BoardAnswerDto getAnswer(String idx);
	
	//수정
	public void updateAnswer(BoardAnswerDto dto);
	
	//삭제
	public void deleteAnswer(String idx); 
}
