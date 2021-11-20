package data.board;

import java.util.List;

public interface BoardDaoInter {

	public int getTotalCount();
	public void insertBoard(BoardDto dto);
	public List<BoardDto> getAllDatas();
}
