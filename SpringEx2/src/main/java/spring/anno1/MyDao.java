package spring.anno1;

import org.springframework.stereotype.Component;

@Component	//�ڵ����� �� ���(id�� Ŭ������, �� ù���ڴ� �ҹ���...mydao).
public class MyDao implements DaoInter {

	@Override
	public void insertData(String str) {
		// TODO Auto-generated method stub
		
		System.out.println("str ����Ÿ�� db�� �߰� ����!!!");
	}

	@Override
	public void deleteData(String num) {
		// TODO Auto-generated method stub

		System.out.println("num�� �ش��ϴ� ����Ÿ ���� ����!!!");
	}

}
