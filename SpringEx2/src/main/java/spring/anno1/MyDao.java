package spring.anno1;

import org.springframework.stereotype.Component;

@Component	//자동으로 빈에 등록(id는 클래스명, 단 첫글자는 소문자...mydao).
public class MyDao implements DaoInter {

	@Override
	public void insertData(String str) {
		// TODO Auto-generated method stub
		
		System.out.println("str 데이타를 db에 추가 성공!!!");
	}

	@Override
	public void deleteData(String num) {
		// TODO Auto-generated method stub

		System.out.println("num에 해당하는 데이타 삭제 성공!!!");
	}

}
