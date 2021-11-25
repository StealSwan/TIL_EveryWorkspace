package member.data;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

public class MemberDao extends SqlSessionDaoSupport{

	
	//id 중복 체크
	public int getIdCount(String id) {
		
		//MemberSql에서 sql 구문으로 id 파라미터값 넘김
		return getSqlSession().selectOne("IdCheckOfMember",id);
	}
	
	
	//insert - return 타입 없음
	public void insertMember(MemberDto dto) {
		
		//출력은 select 아님
		getSqlSession().insert("InsertOfMember", dto);
	}
	
	
	//select list
	public List<MemberDto> getAllMembers(){
		
		//list로 출력하니까
		return getSqlSession().selectList("ListAllOfMember");
	}
}
