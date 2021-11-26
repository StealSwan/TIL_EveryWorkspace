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
}
