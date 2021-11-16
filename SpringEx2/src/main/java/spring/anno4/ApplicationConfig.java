package spring.anno4;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Component;

@ImportResource("classpath:annoContext.xml")
@Component
public class ApplicationConfig {

	@Bean //��ϵ� id�� �޼����
	public Student student1() {

		Student stu = new Student();
		stu.setName("��ȣ��");
		stu.setAge(30);
	
		ArrayList<String> hobby = new ArrayList<String>();
		hobby.add("����");
		hobby.add("���ø���");
		hobby.add("����");
		
		stu.setHobby(hobby);
		
		return stu;
	}
	
	@Bean
	public Student student2() {

		Student stu = new Student();
		stu.setName("�̿���");
		stu.setAge(20);
	
		ArrayList<String> hobby = new ArrayList<String>();
		hobby.add("�ڹٰ���");
		hobby.add("����������");
		hobby.add("DB����");
		
		stu.setHobby(hobby);
		
		return stu;
	}
}