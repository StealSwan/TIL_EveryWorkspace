package member.data;

import java.util.HashMap;
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
	
	
	//아이디 비밀번호 체크 - 맞으면 1, 틀리면 0반환
	public int loginCheck(String id, String pass) {
		
		HashMap<String, String> map = new HashMap<String, String>();
		
		map.put("id", id);
		map.put("pass", pass);
		
		return getSqlSession().selectOne("loginCheckOfMember",map);
	}
	
	
	//num에 따른 하나 조회
	public MemberDto getMember(String num) {
		
		return getSqlSession().selectOne("SelectOneOfMember",num);
	}
	
	
	//비밀번호 체크
	public int passCheck(String num, String pass) {
		
		HashMap<String, String> map = new HashMap<String, String>();
		
		map.put("num", num);
		map.put("pass", pass);
		
		return getSqlSession().selectOne("PassCheckOfMember", map);
	}
	
	
	//실제 업데이트
	public void updateMember(MemberDto dto) {
		
		getSqlSession().update("UpdateOfMember",dto);
	}
	
	
	//실제 삭제
	public void deleteMember(String num) {
		
		getSqlSession().delete("DeleteOfMember",num);
	}
}
