package spring.anno1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("logic") //id�� logic�� �ȴ�
public class LogicController {

	@Autowired	//dao���� �ڵ����� �����ٶ�
	//@Resource(name="myDao") //��Ȯ�� ���� ���̵�� ���� - ��ȣ���� ������
	DaoInter daoInter;
	
	public LogicController(MyDao dao) {
		
		this.daoInter=dao;
	}
	
	
	//insert
	public void insert(String str) {
		
		daoInter.insertData(str);
	}
	
	
	//delete
	public void delete(String num) {
		
		daoInter.deleteData(num);
	}
	
}