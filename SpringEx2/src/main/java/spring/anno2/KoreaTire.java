package spring.anno2;

import org.springframework.stereotype.Component;

@Component("ktire")	//xml�� ��ϵǴ� id�� �����ϰ� ������(�������ϸ� Ŭ������...�Ǿ� �ѱ��� �ҹ���)
public class KoreaTire implements Tire{

	@Override
	public void writeTireName() {
		// TODO Auto-generated method stub
		System.out.println("�ѱ�Ÿ�̾�");
	}
}
