package spring.anno2;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MyCar {

	//@Autowired:매칭할 Tire 종류가 2개라 에러
	//정확한 bean의 이름으로 주입
	//@Resource(name = "kumhoTire") - 금호타이어로 나옴
	@Resource(name = "ktire")
	Tire tire;
	
	public void writeTire() {
		
		System.out.println("**내차의 타이어 브랜드는**");
		tire.writeTireName();
	}
	
}
