package member.data;

import java.util.HashMap;
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
	
	
	//���̵� ��й�ȣ üũ - ������ 1, Ʋ���� 0��ȯ
	public int loginCheck(String id, String pass) {
		
		HashMap<String, String> map = new HashMap<String, String>();
		
		map.put("id", id);
		map.put("pass", pass);
		
		return getSqlSession().selectOne("loginCheckOfMember",map);
	}
	
	
	//num�� ���� �ϳ� ��ȸ
	public MemberDto getMember(String num) {
		
		return getSqlSession().selectOne("SelectOneOfMember",num);
	}
	
	
	//��й�ȣ üũ
	public int passCheck(String num, String pass) {
		
		HashMap<String, String> map = new HashMap<String, String>();
		
		map.put("num", num);
		map.put("pass", pass);
		
		return getSqlSession().selectOne("PassCheckOfMember", map);
	}
	
	
	//���� ������Ʈ
	public void updateMember(MemberDto dto) {
		
		getSqlSession().update("UpdateOfMember",dto);
	}
	
	
	//���� ����
	public void deleteMember(String num) {
		
		getSqlSession().delete("DeleteOfMember",num);
	}
}
