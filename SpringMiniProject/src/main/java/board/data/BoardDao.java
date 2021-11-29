package board.data;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

public class BoardDao extends SqlSessionDaoSupport{

	
	//삽입
	public void insertBoard(BoardDto dto) {
		
		getSqlSession().insert("insertOfBoard", dto);
	}
	
	
	//id에 따른 이름 출력
	public String searchName(String id) {
		
		return getSqlSession().selectOne("SearchNameOfMember", id);
	}
	
	
	//데이터 갯수 구하기
	public int getTotalCount() {
		
		return getSqlSession().selectOne("TotalCountOfBoard");
	}
	
	
	//페이징처리
	public List<BoardDto> getList(int start, int perpage){
		
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		
		map.put("start", start);
		map.put("perpage", perpage);
		
		return getSqlSession().selectList("SelectPagingOfBoard", map);
	}
	
	
	//조회수 1증가
	//리턴값 없음
	public void updateReadCount(int num) {
		
		getSqlSession().update("UpdateReadCountOfBoard", num);
	}
	
	
	//하나출력
	public BoardDto getData(int num) {
	
		return getSqlSession().selectOne("SelectOneOfBoard",num);
	}
	
	
	//get Max
	public int getMaxNum() {
		
		return getSqlSession().selectOne("MaxNumOfBoard");
	}
	
	
	//updateBoard
	public void updateBoard(BoardDto dto) {
		
		getSqlSession().update("UpdateOfBoard", dto);
	}
	
	
	//delete
	public void deleteBoard(int num) {
		
		getSqlSession().delete("DeleteOfBoard", num);
	}
	
	
	//전부 출력
	public List<BoardDto> getAllDatas(){
		
		return getSqlSession().selectList("SelectAllOfBoard");
	}
	
	
	//이미지 출력
	public List<BoardDto> getPhotoDatas(){
		
		return getSqlSession().selectList("SelectPhotoOfBoard");
	}
}
