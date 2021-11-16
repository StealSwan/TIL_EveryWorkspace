package JDBC_Swing_SelfStudy;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.xml.crypto.Data;

public class SwingStuDB01 extends JFrame implements ActionListener{
	
	//DB����
	DBConn01 db = new DBConn01();
	
	
	Container cp;	//�⺻ �����̳�
	DefaultTableModel model;	//���̺��� ����, ����, ���� ���� �߻��� ��� DefaultTableModel ���
	JTable table;	//Db���� ���̺� ������ �� ���, �ʿ���
	JButton btnAdd, btnDel, btnUpdate;	//�߰�, ����, ������ 3���� ��ư ��ü
	
	//Add ������ �߰�
	AddFrame01 addFrame01 = new AddFrame01();
	
	//Update ������ �߰�
	UpdateFrame01 updateFrame01 = new UpdateFrame01();
	
	
	///////////////////////////////////////////////////////////////
	//JFrame �⺻ ����
	public SwingStuDB01() {
		//����
		super("Swing JDBC Table");
		
		cp=this.getContentPane();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//����
		this.setBounds(200, 100, 500, 400);	//�ٿ��� ���� ����
		cp.setBackground(new Color(255, 255, 200));	//���� ����
		initDesign();	//�⺻ ������ ȣ��
		this.setVisible(true);	//���̰� �ϱ�
	}
	
	
	///////////////////////////////////////////////////////////////
	//â ������ �� ���̺� ����ϱ�
	//Select
	public void showTable() {
		
		//�� ���̺� �ʱ�ȭ�� �⺻
		//��ü�� JDBC�� �̿��ϱ⿡ Jtable�� ��ü�� ���
		//����, ����, ���� ��� �� model�� ��� - �ʱ�ȭ�� model�� ��ü�� ���
		model.setRowCount(0);
		
		//����� ���� Select �޼���� ����
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		//�⺻ sql�� �ۼ� ����� �ʱ�!!
		String sql = "Select * from student";
		
		//�ļ�
		try {
			conn = db.getLocalConnection();	//����ȣ��
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			//���
			while (rs.next()) {
				
				//��¹� �ۼ�
				//���� String���� ��¹� �ۼ����־��ٸ� �̹����� Swing â�� ����� ��
				//JDBC�� Swing ���̺��� �ڷᱸ���� �־��ֱ�
				Vector<String> data = new Vector<String>();
				//��¹� rs���� �ش� ��ü�� ������ ������
				//��� String���� �����ؼ� �ִ´�.
				data.add(rs.getString("num"));
				data.add(rs.getString("name"));
				data.add(rs.getString("ban"));
				data.add(rs.getString("jsp"));
				data.add(rs.getString("jsp"));
				data.add(rs.getString("spring"));
				data.add(rs.getString("total"));
				data.add(rs.getString("average"));
				
				//���� ä���� �ڷᱸ���� �𵨿�(ȭ�鿡) �߰�
				model.addRow(data);

			}
			
		} catch (SQLException e) {
			
		} finally {
			db.dbClose(rs, pstmt, conn);
		}
		
	}
	
	
	///////////////////////////////////////////////////////////////
	//Insert �߰��ϱ�
	public void insertStudent() {
		
		//�⺻
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		//�ؽ�Ʈ �ʵ忡 �Էµ� ���� �޾ƿ;� �Ѵ�.
		String name = addFrame01.tfName.getText();
		String ban = (String) addFrame01.cbBan.getSelectedItem();	//���õ� ������ �� ��������
		String java = addFrame01.tfJava.getText();
		String jsp = addFrame01.tfJsp.getText();
		String spring = addFrame01.tfSpring.getText();
		
		//Total ���� Avg �� �߰�
		int tot = Integer.parseInt(java) + Integer.parseInt(jsp) + Integer.parseInt(spring);
		double avg = tot/3;
		
		
		//spl��
		//Insert into
		String sql = "Insert into student values(seq_test.nextval,?,?,?,?,?,?,?)";
		
		
		//�ļ�
		try {
			conn = db.getLocalConnection();
			pstmt = conn.prepareStatement(sql);
			
			//�� ��ġ
			pstmt.setString(1, name);
			pstmt.setString(2, java);
			pstmt.setString(3, jsp);
			pstmt.setString(4, spring);
			pstmt.setInt(5, tot);
			pstmt.setDouble(6, avg);
			pstmt.setString(7, ban);
			
			//������Ʈ
			pstmt.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.dbClose(pstmt, conn);
		}
		
	}
	
	
	///////////////////////////////////////////////////////////////
	//Update �߰��ϱ�
	public void updateStudent() {
		
		//num�� ���ڰ� ���� �޴� ���
		String num = updateFrame01.num;
		
		//�⺻
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		
		//TextField �Է°� ������ ����
		String name = updateFrame01.tfName.getText();
		String cbban = (String) updateFrame01.cbBan.getSelectedItem();
		String java = updateFrame01.tfJava.getText();
		String jsp = updateFrame01.tfJsp.getText();
		String spring = updateFrame01.tfSpring.getText();
		
		//��갪 ����
		int tot = Integer.parseInt(java) + Integer.parseInt(jsp) + Integer.parseInt(spring);
		double avg = tot/3.0;
		
		//sql�� �ۼ�
		String sql = "update student set name=?, java=?, jsp=?, spring=?, total=?, average=?, ban=? where num = " + num;
				
		
		//�ļ�
		try {
			conn = db.getLocalConnection();
			pstmt = conn.prepareStatement(sql);
			
			//�� �Ҵ�
			pstmt.setString(1, name);
			pstmt.setString(2, java);
			pstmt.setString(3, jsp);
			pstmt.setString(4, spring);
			pstmt.setInt(5, tot);
			pstmt.setDouble(6, avg);
			pstmt.setString(7, cbban);
			
			//������Ʈ
			int a = pstmt.executeUpdate();
			if (a==1) {
				JOptionPane.showMessageDialog(this, "���� �����Ǿ����ϴ�");
				showTable();
			} else {
				JOptionPane.showMessageDialog(this, "���� ��ȣ�Դϴ�");
			}
			
			
		} catch (SQLException e) {
			
		} finally {
			db.dbClose(pstmt, conn);
		}
		
	}
	
	
	///////////////////////////////////////////////////////////////
	//Delete �߰��ϱ�
	//���ڰ����� �Ʒ� num ���� �޴´�
	public void deleteStudent(String num) {
		
		//�⺻
		Connection conn = null;
		Statement stmt = null;
		
		//sql��
		//num�� ���� ���Ǵ� �Ʒ� ��ư Ŭ���� ��ɺ��� �����س���
		String sql = "delete from student where num=" + num;
		
		
		//�ļ�
		try {
			conn = db.getLocalConnection();
			stmt = conn.createStatement();
			
			//������Ʈ
			//int�� �� ���� �� pstmt�� �ν��� �ȵ�
			int a = stmt.executeUpdate(sql);
			if (a==1) {
				//JOptionPane�� �̿��� ���̾�α� ���
				JOptionPane.showMessageDialog(this, "�����Ǿ����ϴ�");
				//���̺� �ʱ�ȭ
				showTable();
			} else {
				JOptionPane.showMessageDialog(this, "���� ��ȣ�Դϴ�");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.dbClose(stmt, conn);
		}
	}

	
	///////////////////////////////////////////////////////////////
	//�⺻ ������
	public void initDesign() {
		
		//����� ����� �̸�ǥ
		String [] title = {"��ȣ", "�̸�", "��", "Java", "Jsp", "Spring", "����", "���"};
		model = new DefaultTableModel(title, 0);	//�� ��ü ���� + �Է��� Ÿ��Ʋ �̸�ǥ�� ��ܿ� �� ���
		table = new JTable(model);	//���̺� �� �߰�
		
		//���̺��� �� �� ��� �߰�
		this.add("Center", new JScrollPane(table));
		
		//�����ϸ� �ٷ� ���̺� ���� �� �ְ��ϱ�
		//���� Ȯ���� ���ؼ� �����κ��� ������� �������� �� ����
		showTable();
		
		
		//�ǳ�
		//�ܼ����� �ֱ�
		JPanel pTop = new JPanel();
		this.add("North",pTop);
		
		
		//��ư �߰�
		btnAdd = new JButton("�߰�");
		btnDel = new JButton("����");
		btnUpdate = new JButton("����");
		
		//��ư�� ��� �߰�
		btnAdd.addActionListener(this);
		btnDel.addActionListener(this);
		btnUpdate.addActionListener(this);
		
		//��ư ȭ�鿡 �߰�
		//�ǳ� ���� �߰�
		pTop.add(btnAdd);
		pTop.add(btnDel);
		pTop.add(btnUpdate);
		
		
		//����, addFrame�� ��� ������
		//�˾� â�� "�߰�" ��ư ������ �� ��� �־��ֱ�
		addFrame01.btnAdd.addActionListener(this);

		
		//����, UpdateFrame�� ��� ������
		//�˾� â�� "����" ��ư ������ �� ��� �־��ֱ�
		updateFrame01.btnMod.addActionListener(this);

	}

	
	////////////////////////////////////////////////////////
	//��ư ��� ����
	@Override
	public void actionPerformed(ActionEvent e) {

		//������ ��ư�� ���� ��� ����
		//Ŭ�� ������Ʈ ����
		Object ob = e.getSource();
		
		if (ob==btnAdd) {
			//AddFrame ���̰� �ϱ�
			addFrame01.setVisible(true);	
		} else if (ob==addFrame01.btnAdd) {
			//Insert �޼���
			insertStudent();
			
			//�߰��� ���̺��ʱ�ȭ
			//�ٽ� ���̺� ������ ��
			this.showTable();
			addFrame01.tfName.setText("");
			addFrame01.tfJava.setText("");
			addFrame01.tfJsp.setText("");
			addFrame01.tfSpring.setText("");

			//���콺 Ŀ��
			addFrame01.tfName.requestFocus();
			
			//�߰��� �������� â ���� �� ������� �ϱ�
			addFrame01.setVisible(false);
			
		} else if (ob==btnDel) {
			//���� ��ư�� ���� ���, ����� ������ ������ �˾�â���� ����� �ϱ�
			String num = JOptionPane.showInputDialog("������ ��ȣ�� �Է��ϼ���");
			deleteStudent(num);
			
		} else if (ob==btnUpdate) {
			
			String num = JOptionPane.showInputDialog("����� �����Ͻðڽ��ϱ�?");
			
			String sql = "Select * from student where num = " + num;
			
			//���⼭ ��ȸ�� ����
			Connection conn = null;
			Statement stmt = null;
			ResultSet rs = null;
			
			//�ļ�
			try {
				conn = db.getLocalConnection();
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql);
				
				//�ϳ��� ����� ��� while�� �ƴ϶� if
				if (rs.next()) {
					
					//�������� ������, UpdateFrame�� �� �Ѱ��ֱ�
					//������ ������ num���� �Ѿ��
					updateFrame01.num=num;
					
					//�������ֱ�
					updateFrame01.tfName.setText(rs.getString("name"));
					updateFrame01.tfJava.setText(rs.getString("java"));
					updateFrame01.tfJsp.setText(rs.getString("jsp"));
					updateFrame01.tfSpring.setText(rs.getString("spring"));
					updateFrame01.cbBan.setSelectedItem(rs.getString("ban"));
					
					//���̰� �ϱ�
					updateFrame01.setVisible(true);
					
				} else {	//�����Ͱ� ���� ���
					JOptionPane.showMessageDialog(this, "���� ��ȣ�Դϴ�");
				}
				
			} catch (SQLException e1) {
				
			} finally {
				db.dbClose(rs, stmt, conn);
			}
			
		} else if (ob==updateFrame01.btnMod) {
			
			//Update �޼���
			updateStudent();
			
			//�߰��� ���̺��ʱ�ȭ
			//�ٽ� ���̺� ������ ��
			this.showTable();
			//�����Ǹ� ���� �״�� �����ְ� �ϰ� ������ �Ʒ� ����
//			addFrame01.tfName.setText("");
//			addFrame01.tfJava.setText("");
//			addFrame01.tfJsp.setText("");
//			addFrame01.tfSpring.setText("");
			
			//�߰��� �������� â ���� �� ������� �ϱ�
			updateFrame01.setVisible(false);
			
		} 
		
		
	}


	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new SwingStuDB01();

	}

}
