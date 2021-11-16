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

	//�⺻
	Container cp;
	
	//�ؽ�Ʈ �Է��� ���� TextField
	JTextField tfName, tfJava, tfJsp, tfSpring;
	
	//�� ������ �� �ִ� �޺��ڽ�
	JComboBox <String> cbBan;
	
	//�߰� ��ư
	JButton btnAdd;
	
	
	////////////////////////////////////////////
	//�⺻
	public AddFrame01() {
		
		super("�߰�â");
		cp=this.getContentPane();
		//���� �޼���� ����
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(250, 150, 250, 300);
		cp.setBackground(new Color(255, 255, 200));
		//������ ���̰� �ϱ�
		initDesign();
		//�ڵ����� ���̰� �ϴ� �͵� ���� - ���߿� ���Ǻη� ���̰� �� ��!!!
		//this.setVisible(true);
		
	}
	
	
	///////////////////////////////////////////
	//������
	public void initDesign() {
		
		//�⺻ ���̾ƿ����� ����
		this.setLayout(null);
		
		//�� �߰�
		JLabel label1 = new JLabel("�̸�");
		JLabel label2 = new JLabel("��");
		JLabel label3 = new JLabel("Java");
		JLabel label4 = new JLabel("Jsp");
		JLabel label5 = new JLabel("Spring");
		
		//�� ���� ����
		label1.setBounds(30, 20, 50, 30);
		label2.setBounds(30, 60, 50, 30);
		label3.setBounds(30, 100, 50, 30);
		label4.setBounds(30, 140, 50, 30);
		label5.setBounds(30, 180, 50, 30);
		
		//�� �߰�
		this.add(label1);
		this.add(label2);
		this.add(label3);
		this.add(label4);
		this.add(label5);
		
		
		//�ؽ�Ʈ �ʵ�
		tfName = new JTextField(4);	//�ؽ�Ʈ �ʵ� �ȿ��� ��� ���� ũ��
		tfJava = new JTextField(3);
		tfJsp = new JTextField(3);
		tfSpring = new JTextField(3);
		
		//�ؽ�Ʈ �ʵ� ������
		tfName.setBounds(100, 20, 100, 25);
		tfJava.setBounds(100, 100, 100, 25);
		tfJsp.setBounds(100, 140, 100, 25);
		tfSpring.setBounds(100, 180, 100, 25);
		
		//�ؽ�Ʈ �ʵ� �߰�
		this.add(tfName);
		this.add(tfJava);
		this.add(tfJsp);
		this.add(tfSpring);
		
		
		//ban�� ǥ���� ComboBox �۾�
		String ban [] = {"a��", "b��", "c��", "d��"};
		cbBan = new JComboBox <String> (ban);
		
		//ban ������
		cbBan.setBounds(100, 60, 100, 25);
		
		//ban �߰�
		this.add(cbBan);
		
		
		//��ư �߰�
		btnAdd = new JButton("�߰�");
		btnAdd.setBounds(85, 225, 70, 20);
		this.add(btnAdd);

		
	}
	
	//Ȯ�ο� ���� �޼���
//	public static void main(String[] args) {
//		new AddFrame01();
//	}
	
}
