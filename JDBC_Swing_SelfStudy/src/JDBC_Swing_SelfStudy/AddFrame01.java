package JDBC_Swing_SelfStudy;

import java.awt.Color;
import java.awt.Container;
import java.awt.TextField;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class AddFrame01 extends JFrame{

	//기본
	Container cp;
	
	//텍스트 입력을 위한 TextField
	JTextField tfName, tfJava, tfJsp, tfSpring;
	
	//반 선택할 수 있는 콤보박스
	JComboBox <String> cbBan;
	
	//추가 버튼
	JButton btnAdd;
	
	
	////////////////////////////////////////////
	//기본
	public AddFrame01() {
		
		super("추가창");
		cp=this.getContentPane();
		//종료 메서드는 없앰
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(250, 150, 250, 300);
		cp.setBackground(new Color(255, 255, 200));
		//디자인 보이게 하기
		initDesign();
		//자동으로 보이게 하는 것도 없앰 - 나중에 조건부로 보이게 할 것!!!
		//this.setVisible(true);
		
	}
	
	
	///////////////////////////////////////////
	//디자인
	public void initDesign() {
		
		//기본 레이아웃으로 설정
		this.setLayout(null);
		
		//라벨 추가
		JLabel label1 = new JLabel("이름");
		JLabel label2 = new JLabel("반");
		JLabel label3 = new JLabel("Java");
		JLabel label4 = new JLabel("Jsp");
		JLabel label5 = new JLabel("Spring");
		
		//라벨 범위 설정
		label1.setBounds(30, 20, 50, 30);
		label2.setBounds(30, 60, 50, 30);
		label3.setBounds(30, 100, 50, 30);
		label4.setBounds(30, 140, 50, 30);
		label5.setBounds(30, 180, 50, 30);
		
		//라벨 추가
		this.add(label1);
		this.add(label2);
		this.add(label3);
		this.add(label4);
		this.add(label5);
		
		
		//텍스트 필드
		tfName = new JTextField(4);	//텍스트 필드 안에서 출력 글자 크기
		tfJava = new JTextField(3);
		tfJsp = new JTextField(3);
		tfSpring = new JTextField(3);
		
		//텍스트 필드 디자인
		tfName.setBounds(100, 20, 100, 25);
		tfJava.setBounds(100, 100, 100, 25);
		tfJsp.setBounds(100, 140, 100, 25);
		tfSpring.setBounds(100, 180, 100, 25);
		
		//텍스트 필드 추가
		this.add(tfName);
		this.add(tfJava);
		this.add(tfJsp);
		this.add(tfSpring);
		
		
		//ban을 표기할 ComboBox 작업
		String ban [] = {"a반", "b반", "c반", "d반"};
		cbBan = new JComboBox <String> (ban);
		
		//ban 디자인
		cbBan.setBounds(100, 60, 100, 25);
		
		//ban 추가
		this.add(cbBan);
		
		
		//버튼 추가
		btnAdd = new JButton("추가");
		btnAdd.setBounds(85, 225, 70, 20);
		this.add(btnAdd);

		
	}
	
	//확인용 메인 메서드
//	public static void main(String[] args) {
//		new AddFrame01();
//	}
	
}
