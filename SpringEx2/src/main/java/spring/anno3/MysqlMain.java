package spring.anno3;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MysqlMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ApplicationContext app = new ClassPathXmlApplicationContext("annoContext.xml");
		
		MysqlController cont = (MysqlController)app.getBean("mysqlcont");
		cont.insert("�Ѱ���");
		cont.delete("3");
		cont.select("�輱ȣ");
	}

}
