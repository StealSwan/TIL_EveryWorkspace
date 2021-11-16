package spring.anno2;

import org.springframework.stereotype.Component;

@Component("ktire")	//xml에 등록되는 id를 지정하고 싶을때(지정안하면 클래스명...맨앞 한글자 소문자)
public class KoreaTire implements Tire{

	@Override
	public void writeTireName() {
		// TODO Auto-generated method stub
		System.out.println("한국타이어");
	}
}
