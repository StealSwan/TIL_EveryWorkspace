package board.data;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

public class AnswerDao extends SqlSessionDaoSupport{


	//�Է�
	public void insertAnswer(AnswerDto dto) {
		
		getSqlSession().insert("InsertOfAnswer", dto);
	}
	
	
	//��ȸ
	public List<AnswerDto> getAnswerList(int num){
		
		return getSqlSession().selectList("SelectNumOfAnswer",num);
	}
	
	
	//��й�ȣ üũ
	public int getCheckPass(int idx, String pass) {
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		map.put("idx", idx);
		map.put("pass", pass);
		
		return getSqlSession().selectOne("PassCheckOfAnswer", map);
	}
	
	
	//����
	public void deleteAnswer(int idx) {
		
		getSqlSession().delete("DeleteOfAnswer",idx);
	}
}
