package board.data;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

public class BoardDao extends SqlSessionDaoSupport{

	
	//����
	public void insertBoard(BoardDto dto) {
		
		getSqlSession().insert("insertOfBoard", dto);
	}
	
	
	//id�� ���� �̸� ���
	public String searchName(String id) {
		
		return getSqlSession().selectOne("SearchNameOfMember", id);
	}
	
	
	//������ ���� ���ϱ�
	public int getTotalCount() {
		
		return getSqlSession().selectOne("TotalCountOfBoard");
	}
	
	
	//����¡ó��
	public List<BoardDto> getList(int start, int perpage){
		
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		
		map.put("start", start);
		map.put("perpage", perpage);
		
		return getSqlSession().selectList("SelectPagingOfBoard", map);
	}
	
	
	//��ȸ�� 1����
	//���ϰ� ����
	public void updateReadCount(int num) {
		
		getSqlSession().update("UpdateReadCountOfBoard", num);
	}
	
	
	//�ϳ����
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
	
	
	//���� ���
	public List<BoardDto> getAllDatas(){
		
		return getSqlSession().selectList("SelectAllOfBoard");
	}
	
	
	//�̹��� ���
	public List<BoardDto> getPhotoDatas(){
		
		return getSqlSession().selectList("SelectPhotoOfBoard");
	}
}
