package spring.anno2;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MyCar {

	//@Autowired:��Ī�� Tire ������ 2���� ����
	//��Ȯ�� bean�� �̸����� ����
	//@Resource(name = "kumhoTire") - ��ȣŸ�̾�� ����
	@Resource(name = "ktire")
	Tire tire;
	
	public void writeTire() {
		
		System.out.println("**������ Ÿ�̾� �귣���**");
		tire.writeTireName();
	}
	
}