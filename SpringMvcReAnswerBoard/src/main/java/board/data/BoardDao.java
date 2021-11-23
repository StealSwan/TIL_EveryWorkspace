package board.data;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;


public class BoardDao extends SqlSessionDaoSupport{

	public int getTotalCount() {
		
		//sql id
		return getSqlSession().selectOne("TotalCountOfBoard");
	}
	
	
	public int getMaxNum() {
		
		return getSqlSession().selectOne("MaxNumOfBoard");
	}
	
	
	//������Ʈ
	public void updateRestep(int regroup, int restep) {
		
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		
		map.put("regroup", regroup);
		map.put("restep", restep);
		
		getSqlSession().update("UpdateStepOfBoard", map);
	}
	
	
	public void insertBoard(BoardDto dto) {
		
		int num= dto.getNum();	//0:����, 1���� ū��: ���
		int regroup = dto.getRegroup();
		int restep = dto.getRestep();
		int relevel = dto.getRelevel();

		//����
		if(num==0){
			regroup=getMaxNum()+1;	//num�� �ִ밪+1
			restep=0;
			relevel=0;
		} else {
			
			//���� �׷��߿��� ���޹��� restep���� ū������ ��� + 1
			this.updateRestep(regroup, restep);
			
			//���޹��� step, level 1�� ����
			restep++;
			relevel++;
		}
		
		//�ٲﰪ���� �ٽ� dto�� ��´�
		dto.setRegroup(regroup);
		dto.setRestep(restep);
		dto.setRelevel(relevel);
		
		//insert
		getSqlSession().insert("insertOfBoard", dto);
	}
	
	
	
	//����¡ó��
	public List<BoardDto> getList(int start, int perpage){
		
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		
		map.put("start", start);
		map.put("perpage", perpage);
		
		return getSqlSession().selectList("SelectPagingOfBoard", map);
	}
	
	
	//��ȸ�� ����
	public void updateReadCount(int num) {
		
		getSqlSession().update("UpdateReadCountOfBoard", num);
	}
	
	
	public BoardDto getData(int num) {
		
		return getSqlSession().selectOne("GetDataOfBoard", num);
	}
	
	
	//id, ��� üũ
	public int getCheckPass(String num, String pass) {
		
		HashMap<String, String> map = new HashMap<String, String>();
		
		map.put("num", num);
		map.put("pass", pass);
		
		return getSqlSession().selectOne("CheckPassEqualOfBoard", map);
	}
	
	
	//���� ������Ʈ
	public void updateBoard(BoardDto dto) {
		
		getSqlSession().update("UpdateOfBoard", dto);
	}
	
	
	//���� ����
	public void deleteBoard(int num) {
		
		getSqlSession().delete("DeleteOfBoard", num);
	}
}
