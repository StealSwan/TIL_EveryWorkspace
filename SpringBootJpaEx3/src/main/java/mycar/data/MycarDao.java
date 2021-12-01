package mycar.data;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MycarDao {

	@Autowired
	MycarInter carInter;
	
	public void insertCar(MycarDto dto) {
		
		//id 타입 유무에 따라서 자동으로 insert, update
		carInter.save(dto);
	}
	
	
	//전체출력
	public List<MycarDto> getAllDatas(){
		
		return carInter.findAll();
	}
	
	
	//num에 대한 데이터 반환
	public MycarDto getData(Long num) {
		
		return carInter.getById(num);
	}
	
	
	//수정
	public void updateCar(MycarDto dto) {
		
		//dto 안에 num이 포함
		//update로 자동 인식
		carInter.save(dto);
	}
	
	
	//삭제
	public void deleteCar(Long num) {
		
		//전체가 아니라 해당 num만 지울것이기에
		carInter.deleteById(num);
	}
}
