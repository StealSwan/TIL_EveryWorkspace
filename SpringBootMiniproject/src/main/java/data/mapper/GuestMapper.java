package data.mapper;

import java.util.HashMap;
import java.util.List;

import data.dto.GuestDto;

public interface GuestMapper {
	
	//전체수
	public int getTotalCount();
   
	//하나의 데이터
	public GuestDto getData(String num);
   
	//리스트 출력
	public List<GuestDto> getList(HashMap<String, Integer> map);

	//입력
	public void insertGuest(GuestDto dto);
	
	//업데이트
	public void updateGuest(GuestDto dto);
	   	   
	//삭제
	public void deleteGuest(String num);
}