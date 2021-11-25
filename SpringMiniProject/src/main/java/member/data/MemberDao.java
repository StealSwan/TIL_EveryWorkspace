package member.data;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

public class MemberDao extends SqlSessionDaoSupport{

	
	//id �ߺ� üũ
	public int getIdCount(String id) {
		
		//MemberSql���� sql �������� id �Ķ���Ͱ� �ѱ�
		return getSqlSession().selectOne("IdCheckOfMember",id);
	}
	
	
	//insert - return Ÿ�� ����
	public void insertMember(MemberDto dto) {
		
		//����� select �ƴ�
		getSqlSession().insert("InsertOfMember", dto);
	}
	
	
	//select list
	public List<MemberDto> getAllMembers(){
		
		//list�� ����ϴϱ�
		return getSqlSession().selectList("ListAllOfMember");
	}
}
