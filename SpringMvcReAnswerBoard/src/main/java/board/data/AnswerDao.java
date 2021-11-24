package board.data;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

public class AnswerDao extends SqlSessionDaoSupport{


	//입력
	public void insertAnswer(AnswerDto dto) {
		
		getSqlSession().insert("InsertOfAnswer", dto);
	}
	
	
	//조회
	public List<AnswerDto> getAnswerList(int num){
		
		return getSqlSession().selectList("SelectNumOfAnswer",num);
	}
	
	
	//비밀번호 체크
	public int getCheckPass(int idx, String pass) {
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		map.put("idx", idx);
		map.put("pass", pass);
		
		return getSqlSession().selectOne("PassCheckOfAnswer", map);
	}
	
	
	//삭제
	public void deleteAnswer(int idx) {
		
		getSqlSession().delete("DeleteOfAnswer",idx);
	}
}
