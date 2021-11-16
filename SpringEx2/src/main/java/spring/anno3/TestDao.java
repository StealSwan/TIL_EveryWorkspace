package spring.anno3;

import org.springframework.stereotype.Component;

@Component
public class TestDao {

	public void insert(String name) {
		
		System.out.println(name + "님 db에 추가완료");
	}
	
	
	public void delete(String num) {
		
		System.out.println(num+"번 데이타 삭제완료!!");
	}
	
	public void select(String name) {
		
		System.out.println(name+"님은 sist에 교육받는 학생입니다");
	}
}
