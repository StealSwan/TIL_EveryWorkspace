package spring.anno2;

import org.springframework.stereotype.Component;

@Component
public class KumhoTire implements Tire {

	@Override
	public void writeTireName() {
		// TODO Auto-generated method stub

		System.out.println("금호타이어");
	}

}
