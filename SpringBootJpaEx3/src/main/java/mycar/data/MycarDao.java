package mycar.data;

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
}
