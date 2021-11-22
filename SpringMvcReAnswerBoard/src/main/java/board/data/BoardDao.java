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
}
