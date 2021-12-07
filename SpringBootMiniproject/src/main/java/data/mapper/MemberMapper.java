package data.mapper;

import java.util.HashMap;
import java.util.List;

import data.dto.MemberDto;

public interface MemberMapper {
	
	//삽입
	public void insertMember(MemberDto dto);
	
	//리스트 전체 출력
	public List<MemberDto> getAllMembers();
	
	//Id 체크
	public int getIdCheck(String id);

	//비밀번호확인
	public int getCheckPass(HashMap<String, String> map);
	
	//하나의 값 가져오기
	public MemberDto getMember(String num);
	
	//실제 업데이트
	public void updateMember(MemberDto dto);
	
	//delete
	public void deleteMember(String num);
	
	
	//////////////////////////////
	
	//ID에 따라 이름 얻어오기
	public String getName(String id);
	
	//로그인
	public int login(HashMap<String, String> map);
}
