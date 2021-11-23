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
	
	
	//업데이트
	public void updateRestep(int regroup, int restep) {
		
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		
		map.put("regroup", regroup);
		map.put("restep", restep);
		
		getSqlSession().update("UpdateStepOfBoard", map);
	}
	
	
	public void insertBoard(BoardDto dto) {
		
		int num= dto.getNum();	//0:새글, 1보다 큰값: 답글
		int regroup = dto.getRegroup();
		int restep = dto.getRestep();
		int relevel = dto.getRelevel();

		//새글
		if(num==0){
			regroup=getMaxNum()+1;	//num의 최대값+1
			restep=0;
			relevel=0;
		} else {
			
			//같은 그룹중에서 전달받은 restep보다 큰값들은 모두 + 1
			this.updateRestep(regroup, restep);
			
			//전달받은 step, level 1씩 증가
			restep++;
			relevel++;
		}
		
		//바뀐값들을 다시 dto에 담는다
		dto.setRegroup(regroup);
		dto.setRestep(restep);
		dto.setRelevel(relevel);
		
		//insert
		getSqlSession().insert("insertOfBoard", dto);
	}
	
	
	
	//페이징처리
	public List<BoardDto> getList(int start, int perpage){
		
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		
		map.put("start", start);
		map.put("perpage", perpage);
		
		return getSqlSession().selectList("SelectPagingOfBoard", map);
	}
	
	
	//조회수 증가
	public void updateReadCount(int num) {
		
		getSqlSession().update("UpdateReadCountOfBoard", num);
	}
	
	
	public BoardDto getData(int num) {
		
		return getSqlSession().selectOne("GetDataOfBoard", num);
	}
	
	
	//id, 비번 체크
	public int getCheckPass(String num, String pass) {
		
		HashMap<String, String> map = new HashMap<String, String>();
		
		map.put("num", num);
		map.put("pass", pass);
		
		return getSqlSession().selectOne("CheckPassEqualOfBoard", map);
	}
	
	
	//실제 업데이트
	public void updateBoard(BoardDto dto) {
		
		getSqlSession().update("UpdateOfBoard", dto);
	}
	
	
	//실제 삭제
	public void deleteBoard(int num) {
		
		getSqlSession().delete("DeleteOfBoard", num);
	}
}
