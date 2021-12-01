package myshop.data;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DaoSupport;
import org.springframework.stereotype.Repository;

import mycar.data.MycarInter;

@Repository
public class MyshopDao {

	//인터페이스 등록
	@Autowired
	MyshopInter shopInter;
	
	
	//insert
	public void insertShop(MyshopDto dto) {
		
		//id타입 유무에 따라 자동으로 insert, update
		shopInter.save(dto);
	}
	
	
	//전체출력
	public List<MyshopDto> getAllDatas(){
		
		return shopInter.findAll();
	}
	
	
	//num에 대한 데이터 반환
	public MyshopDto getData(int num) {
		
		return shopInter.getById(num);
	}
	
	
	//수정
	public void updateShop(MyshopDto dto) {
		
		//dto안에 num이 포함되어 있으면 update로 자동인식
		shopInter.save(dto);
	}
	
	
	//삭제
	public void deleteShop(int num) {
		
		//전체가 아니라 해당 num만 삭제
		shopInter.deleteById(num);
	}
}
