package spring.anno3;

import org.springframework.stereotype.Component;

@Component
public class TestDao {

	public void insert(String name) {
		
		System.out.println(name + "�� db�� �߰��Ϸ�");
	}
	
	
	public void delete(String num) {
		
		System.out.println(num+"�� ����Ÿ �����Ϸ�!!");
	}
	
	public void select(String name) {
		
		System.out.println(name+"���� sist�� �����޴� �л��Դϴ�");
	}
}