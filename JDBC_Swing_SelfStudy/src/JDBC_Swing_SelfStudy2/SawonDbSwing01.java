package JDBC_Swing_SelfStudy2;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import JDBC_Swing_SelfStudy.DBConn01;
import oracle.security.crypto.core.RSA;

public class SawonDbSwing01 extends JFrame implements ActionListener, ItemListener{

	//�⺻ �����̳�
	Container cp;
	
	//���� ��ü
	DBConn01 db = new DBConn01();
	
	//���� ������ ���� �⺻ ���̺� �� ����
	DefaultTableModel model;
	
	//���̺� �ε�� ���̺� ��ü ����
	JTable table;
	
	//���� ��ư ũ�⸸ �Ҵ�
	JRadioButton rb [] = new JRadioButton[6];
	
	//�߰�, ���� ��ư
	JButton btnAdd, btnDel;
	
	
	//////////////////////////////////////////
	//�⺻ ����
	public SawonDbSwing01() {
		
		super("�������");
		
		cp=this.getContentPane();
		cp.setBackground(new Color(255, 255, 200));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(200, 100, 700, 500);
		
		//������ ���̰� �ϱ�
		initDesign();
		//���̺� ������ db�κ��� ������ ��������
		showTable(0);
		this.setVisible(true);
		
	}
	
	//////////////////////////////////////////
	//JDBC Oracle�� ����
	public void showTable(int select) {
		
		//�� �ʱ�ȭ, ���̺� �۱� - Row ī��Ʈ�� 0���� ������ش�
		model.setRowCount(0);
		
		//JDBC �����ϱ�
		Connection conn = db.getLocalConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		//sql��
		String sql = "";
		
		
		//���� ��ư ���ð��� ���� ���̰� ���� ���� �ٸ��� �ϰ� ���� ��
		if (select==0) {
			sql = "Select ROWNUM no, num id, name, buseo, gender, to_char(pay,'L999,999,999') pay from sawon";
		} else if (select==1) {
			sql = "Select ROWNUM no, num id, name, buseo, gender, to_char(pay,'L999,999,999') pay from sawon where gender = '����'";
		} else if (select==2) {
			sql = "Select ROWNUM no, num id, name, buseo, gender, to_char(pay,'L999,999,999') pay from sawon where gender = '����'";
		} else if (select==3) {
			sql = "Select ROWNUM no, num id, name, buseo, gender, to_char(pay,'L999,999,999') pay from sawon where buseo = '������'";
		} else if (select==4) {
			sql = "Select ROWNUM no, num id, name, buseo, gender, to_char(pay,'L999,999,999') pay from sawon where buseo = 'ȫ����'";
		} else if (select==5) {
			sql = "Select ROWNUM no, num id, name, buseo, gender, to_char(pay,'L999,999,999') pay from sawon where buseo = '������'";
		}
		
		
		//�ļ�
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			//��¹�
			while (rs.next()) {
				
				//���� ��¹��� �־�������, ���̺��� JScrollPane�� �ø� ���̱⿡ �ڷᱸ���� ����ִ� �۾��� �ʿ��ϴ�
				Vector<String> data = new Vector<String>();
				
				//�����͸� �߰� - rs�� ����� ���� Ư�� ������ ������
				//�����ö� ���ڿ��� ������ �͵� �ȴ�
				data.add(rs.getString("no"));
				data.add(rs.getString("id"));
				data.add(rs.getString("name"));
				data.add(rs.getString("buseo"));
				data.add(rs.getString("gender"));
				data.add(rs.getString("pay"));
				
				//����ִ� �𵨿� �߰�
				model.addRow(data);
				
				
				//Select���� execute�� ���� ������ ��������!!
				//pstmt.execute();
				
				
			}
			
		} catch (SQLException e) {
			
		} finally {
			db.dbClose(rs, pstmt, conn);
		}
	}
	
	
	//////////////////////////////////////////
	//�⺻ ������
	public void initDesign() {
		
		//�⺻ ���̾ƿ� ����
		this.setLayout(null);
		
		//���̺� �߰� �۾����� ����
		//table ����� ���� ���� �±� �ۼ�
		String [] title = {"No", "ID", "�����", "�μ���", "����", "�޿�"};
		
		//���̺� �� �߰�
		model = new DefaultTableModel(title, 100);	//title �̶�� �̸��� Vector�� ���̺� �������� ǥ���� ���̰�, �హ�� �󸶳� �߰����� ���� - Vector�� �������̶� �ϴ� 0���� ����
		table = new JTable(model);
		
		//JScrollPane�� ���̺� �߰�
		JScrollPane js = new JScrollPane(table);
		//JScrollPane ũ�� ����
		js.setBounds(10, 100, 670, 350);
		this.add(js);
		
		
		//��ư ����
		btnAdd = new JButton("�߰�");
		btnDel = new JButton("����");
		
		//ũ�� ����
		btnAdd.setBounds(225, 25, 100, 30);
		btnDel.setBounds(375, 25, 100, 30);
		
		//��ư�� �׼� �߰�
		btnAdd.addActionListener(this);
		btnDel.addActionListener(this);
		
		//��ư �߰�
		this.add(btnAdd);
		this.add(btnDel);
		
		
		//���� ��ư �߰�
		//���� ������ ���� ButtonGroup
		ButtonGroup bg = new ButtonGroup();
		
		//��ư����� ���� �迭 ����
		String [] rbTitle = {"��ü", "����", "����", "������", "ȫ����", "������"};
		
		//��ġ�� ���Ǹ� ���� xpos �� ����
		int xpos = 60;
		
		//�ݺ������� ��ư �߰�
		for (int i = 0; i < rbTitle.length; i++) {
			//�������� JRadioButton�� ũ�⸸ ���� �Ͽ�����, ���⼭ ���ÿ� �̸� ����
			rb[i] = new JRadioButton(rbTitle[i], i==0 ? true:false);	//ó�� ���õ� �� ���� �����ڷ� �߰�
			//���� ��ư ��ġ ����
			rb[i].setBounds(xpos, 65, 90, 30);
			//���� ��ư ����
			rb[i].setOpaque(false);
			//���� ��ư ��� �߰� - ItemListener�� �۾�
			rb[i].addItemListener(this);
			//���� ��ư �׷쿡 �߰� - �Ѱ����� ���õǵ���
			bg.add(rb[i]);
			//���� ��ư �߰�
			this.add(rb[i]);
			
			//xpos�� ����
			xpos += 100;
			
		}
		
	}
	
	
	
	/////////////////////////////////////////
	//�������̵�
	//��ư ���
	@Override
	public void actionPerformed(ActionEvent e) {
		
		//Ŭ�� ��ü ����
		Object ob = e.getSource();
		
		//��ư�� ������ â�� ��Ÿ�� ���, �ű⼭�� ���� Oracle�� �����Ǿ�� �ϱ⿡ ���� ������ �ʿ�
		Connection conn = db.getLocalConnection();
		PreparedStatement pstmt = null;
		
		//�۾��� ������ �⺻ sql�� ����
		String sql = "";
		
		
		//� ��ư�� �����Ŀ� ���� ��ɱ���
		if (ob==btnAdd) {
			
			//����â 4�� �˾�
			//�̸�, ����, �μ�, �޿�
			//JOptionPane.showInputDialog�� ����
			String name = JOptionPane.showInputDialog("�̸��� �Է��ϼ���");
			String gender = JOptionPane.showInputDialog("������ �Է��ϼ���");
			String buseo = JOptionPane.showInputDialog("�μ��� �Է��ϼ���");
			String pay = JOptionPane.showInputDialog("�޿��� �Է��ϼ���");
			
			//���� ������ ���� sql�� �ۼ�
			//insert�� �ۼ��� ������
			sql = "insert into sawon values(seq_sawon.nextval, ?, ?, ?, ?)";
			
			
			//�ļ�
			try {
				pstmt = conn.prepareStatement(sql);
				
				//������
				pstmt.setString(1, name);
				pstmt.setString(2, gender);
				pstmt.setString(3, buseo);
				pstmt.setString(4, pay);
				
				//������Ʈ
				pstmt.execute();
				
				//�߰��ϰ��� �߰��� ���̺� "��ü"�� �ٽ� �����ֱ� - ���ڰ� 0�� ����
				this.showTable(0);
				
				//�߰��ϰ� ���� "��ü" ���� ��ư���� �ڵ� ���õǵ��� �ϱ�
				rb[0].setSelected(true);
				
			} catch (SQLException e1) {
				
			} finally {
				db.dbClose(pstmt, conn);
			}
			
			
		} else if (ob==btnDel) {
			
			//������ ���ȣ ����
			int row = table.getSelectedRow(); //�����ϸ� Ư������ ��ȯ
			
			//�ƹ��͵� �������� ���� ���, -1�� ��ȯ
			//���þ����� ��, 
			if (row==-1) {
				
				//��� �޽��� ���
				JOptionPane.showMessageDialog(this, "���� �������ּ���");
				//�޼��� ����������
				return;
			}
			
			//������ ���� id ���� �𵨷κ��� ���� �Ѵ�.
			//�𵨷κ��� ���� ù��° ���� �����´�.
			String num = (String) model.getValueAt(row, 1);
			
			
			//���� id���� �������� sql���� �ۼ�
			//sql�� �ۼ��� ����
			sql = "delete from sawon where num = ?";
			
			
			//sql���� �ۼ��ߴٸ�, �ļ� �۾��� �ʼ���
			try {
				pstmt =conn.prepareStatement(sql);
				
				//���ε�
				pstmt.setString(1, num);
				
				//������Ʈ
				pstmt.execute();
				
				//������ ��, ����� ���̺� "��ü"�� �ҷ�����
				this.showTable(0);
				
				//���� ��ư�� "��ü"�� �����ֱ�
				rb[0].setSelected(true);
				
				
			} catch (SQLException e1) {
				
			} finally {
				db.dbClose(pstmt, conn);
			}
			
			
		}
		
	}
	
	
	/////////////////////////////////////////
	//�������̵�
	//���� ��ư ��� - ItemListener
	@Override
	public void itemStateChanged(ItemEvent e) {
		
		//�� Ŭ���� ��ü ����
		Object ob = e.getSource();
		
		//select�� ����
		int select = 0;
		
		//���� ��ư ���̸�ŭ ���
		for (int i = 0; i < rb.length; i++) {
			
			if (ob==rb[i]) {
				select = i;
			}
			
		} showTable(select); //showTable �޼��� ȣ��
		
	}
	
	
	
	
	public static void main(String[] args) {
		
		new SawonDbSwing01();

	}

}
