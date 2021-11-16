package JDBC_Swing_SelfStudy2;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

//��ư 4���� ����â�� ������ ���̴�
public class ShopMain01 extends JFrame implements ActionListener{

	//db�𵨿� �ִ� �޼��� ȣ��
	ShopDBModel01 dbModel01 = new ShopDBModel01();
	
	
	//��ư ��ü ����
	JButton btnAdd, btnDel, btnUpdate, btnList; 
	
	
	/////////////////////////////////////////////
	//�⺻ ����
	public ShopMain01() {
		super("Shop");
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setBounds(800, 100, 250, 300);
		this.getContentPane().setBackground(Color.white);
		//������ ���̰� �ϱ�
		initDesign();
		this.setVisible(true);
	}
	
	
	//////////////////////////////////////////////
	//������
	public void initDesign() {
		
		//�⺻ ���̾ƿ� - �׸���� �ο�
		this.setLayout(new GridLayout(2,2)); //2�� 2���� ��ư�� ��ġ
		
		//��ư����
		btnAdd = new JButton("��ǰ�߰�");
		btnDel = new JButton("��ǰ����");
		btnUpdate = new JButton("��ǰ����");
		btnList = new JButton("��ǰ��ȸ");
		
		//GridLayout���� �����߱⿡ setBounds�� ���� �ʿ䰡 ����.
		
		//��ư���
		btnAdd.addActionListener(this);
		btnDel.addActionListener(this);
		btnUpdate.addActionListener(this);
		btnList.addActionListener(this);
		
		//��ư�߰�
		this.add(btnAdd);
		this.add(btnDel);
		this.add(btnUpdate);
		this.add(btnList);

	}
	
	
	//////////////////////////////////////////////
	//��ư��� ���� - �������̵�
	@Override
	public void actionPerformed(ActionEvent e) {
		
		//��ư ���� ��ü ����
		Object ob = e.getSource();
		
		//���ð��� ���� ��� ����
		if (ob==btnAdd) {
			ShopAddForm01 addForm01 = new ShopAddForm01();
		} else if (ob==btnDel) {
			
			//���� ���
			//Ȯ���� �޴� ����
			int ans = JOptionPane.showConfirmDialog(btnDel, "�����Ͻ÷��� [��]�� �����ּ���");
			//System.out.println(ans);��:0,�ƴϿ�:1,���:2
			
			if (ans==0) {
				//���â���� ������ ������ ��ȣ�ޱ� - �̰� String ����
				//btnDel���� �Է¹��� ��ȣ
				String num = JOptionPane.showInputDialog("������ ��ȣ�� �Է��ϼ���");
				
				//String���� ���� num���� int�� ��ȯ�ϱ�
				//modelDB�� delete �޼��忡 num���� �Ѱ��ִ� ���ÿ�, ��Ʈ������ ��ȯ
				//deleteShop���� return�� n���� delcode�� �����
				int delcode = dbModel01.deleteShop(Integer.parseInt(num));
				
				//������ �ȵǼ� 0��ȯ
				if (delcode==0) {
					JOptionPane.showMessageDialog(btnDel, "�ش� ��ȣ�� �������� �ʽ��ϴ�");
				} else {
					JOptionPane.showMessageDialog(btnDel, "������� �Ǿ����ϴ�");
				}
				
			}
			
			
		} else if (ob==btnUpdate) {
			
			//��ȣ �Է� �˸�â
			String num = JOptionPane.showInputDialog("��ȣ�� �Է����ּ���");
			
			//cancle ���� ���
			if (num==null) {
				return; //�˾�â �ݱ�
			}
			
			
			//�߿��� �� ������ ��ȣ�� num���� �Ѱ��ִ� ��
			//DTO ȣ�� ��ü ������ ���ÿ� dbModel�� �ִ� getOnedata�� �Ѱ���
			//�ش� num�� ������ ���� ����� dto�� ���� return��
			ShopDTO01 dto = dbModel01.getOneData(num);
			
			
			//���� dto������ dbModel�� ����ִ� ���� update �ϱ�
			//update ��ü ���� ��, ����
			ShopUpdateForm01 updateForm01 = new ShopUpdateForm01();
			
			
			//��ȣ ���� ������ ���� �ش� ������ �Էµǰ� �ϱ�
			//�ϳ��� �Էµ� dto���� ��ü�� �Ѵ�.
			//�� ���鿡 �ش� ���� �־���
			updateForm01.num=dto.getNum();
			updateForm01.tfSang.setText(dto.getSangpum());
			updateForm01.imageName=dto.getPhoto();
			updateForm01.tfSu.setText(String.valueOf(dto.getSu()));
			updateForm01.tfDan.setText(String.valueOf(dto.getDan()));
			updateForm01.lblphoto.setText(dto.getPhoto());
			
			//������ �˾� ���̰� �ϱ�
			updateForm01.setVisible(true);
			
			
			
		} else if (ob==btnList) {
			ShopListForm01 shopListForm01 = new ShopListForm01();
		}
		
	}
	
	public static void main(String[] args) {

		new ShopMain01();
		
	}

}
