package JDBC_Swing_SelfStudy2;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ShopListForm01 extends JFrame implements ActionListener{
	
	//���̺� ȣ���� ���� ����� �غ�
	
	//DCL �޼��� ȣ���� ���� ��Ƴ��Ҵ� ��ü ����
	ShopDBModel01 dbModel01 = new ShopDBModel01();
	
	//������� ���� ���̺� �⺻ �𵨰� ���̺� ����
	DefaultTableModel model;
	JTable table;
	
	//����� ���� Vector�� ����� �� ȣ�� - DTO�� ��ü�� �����ϴ� �� �ƴ϶�, Vector�� DTO�� ���ڷ� ����
	Vector<ShopDTO01> list;
	
	//�̹��� �ޱ� & ��� - ����Ŭ����
	ImageDraw imageDraw = new ImageDraw();
	String imageName;
	
	//���ΰ�ħ ��ư
	JButton btnRefresh;
	
	
	//////////////////////////////////////////////
	//�⺻ ����
	public ShopListForm01() {
		
		super("��ǰ ��ü ���");	
		this.setBounds(800, 100, 700, 400);
		this.getContentPane().setBackground(Color.white);
		initDesign();
		//this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	
	//////////////////////////////////////////////
	//������
	public void initDesign() {
		
		//�⺻ ���̾ƿ�
		this.setLayout(null);

		
		//�̹� ShopDBModel���� getAllSangpum�̶�� �̸�����
		//Select �޼��带 return���� Vector�� �ϴ� list�� �������.
		//���⿡ ���� �޼��带 ����� �� rs ���� Vector�� ����Ǿ��ִ�.
		//���� �� ���� �ҷ��;��Ѵ�.
		//�� ���� �� ���⼭ ���� Vector�� list ���� ����.
		list = dbModel01.getAllSangPum();
		
		
		//���̺� ��¸� ���
		String [] title = {"Num", "��ǰ��", "����", "�ܰ�", "�ѱݾ�", "������"};
		
		//����Ʈ �𵨰� ���̺� �߰� + JScrollPane
		model = new DefaultTableModel(title, 0); //Ÿ��Ʋ �Է°� ���߰�
		table = new JTable(model);
		JScrollPane js = new JScrollPane(table);
		js.setBounds(10, 10, 500, 260);
		this.add(js);
		
		
		//��ư �߰�
		btnRefresh = new JButton("���ΰ�ħ");
		btnRefresh.setBounds(250, 300, 150, 30);
		btnRefresh.addActionListener(this);
		add(btnRefresh);
		
		//���̺� ���
		showTable();
		
		
		//�̹��� �߰�
		imageDraw.setBounds(510, 50, 170, 220);
		this.add(imageDraw);
		
		
		
		//���̺� ���콺 ����߰�
		//���̺� �̺�Ʈ_�͸��� Ŭ����
		//MouseListener�� �ϸ� �������̵��Ұ� �ʹ� ���Ƽ� MouseAdapter�� �Ѵ�.
		table.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				
				//���ȣ ��� �ش� �࿡ �ش��ϴ� dto�� �������� - getter
				int row = table.getSelectedRow();
				
				//list���� row�� �ش��ϴ� photo����� dto�� ������ �̹������� ��´�
				imageName = list.get(row).getPhoto();
				
				//paint �޼��� �ٽ� ȣ��
				imageDraw.repaint();
				
			}
			
		});
	}
	
	
	/////////////////////////////////////////////
	//�������� �� showTable�� ������ �޼���� ����ߴ�!!!
	//showTable
	public void showTable() {
		
		//���̺��� ���
		//ó���� �� �ʱ�ȭ
		model.setRowCount(0);
		
		//���̺� ������ �߰�
		//Vector ist�� ��� ������ �� ���
		//for�� ���
		for (ShopDTO01 dto : list) {
			
			//���� ��� ��ü�� Vector ���� - String�� Vector ����
			//����� ���̱⿡ String������ ����
			Vector<String> data = new Vector<String>();
			
			//Vector�� �������߰�
			data.add(dto.getNum());
			data.add(dto.getSangpum());
			data.add(String.valueOf(dto.getSu()));
			data.add(String.valueOf(dto.getDan()));
			data.add(String.valueOf(dto.getSu()*dto.getDan()));
			data.add(String.valueOf(dto.getGuipday()));
			
			//�߰��� Vector�� model�� �߰�
			model.addRow(data);
		}
		
	}
	
	
	/////////////////////////////////////////////
	//Refresh ��ư���
	@Override
	public void actionPerformed(ActionEvent e) {
		
		//����Ʈ�� �ִ� ��ǰ�� �ٽ� ȣ��
		list = dbModel01.getAllSangPum();
		
		//�װ� ������� �ٽ� ���̺� ���
		showTable();
	}
	
	
	/////////////////////////////////////////////
	//�̹����� ���� ���� Ŭ����
	class ImageDraw extends Canvas{
		
		//paint �޼��� �������̵�
		@Override
		public void paint(Graphics g) {
			
			super.paint(g);
			
			//�̹��� ������ ����
			Image image = new ImageIcon(imageName).getImage();
			//�̹��� ũ�� ����
			g.drawImage(image, 0, 0, 180, 200, this);
		}
		
	}
	
	
	/////////////////////////////////////////////
//	public static void main(String[] args) {
//		
//		new ShopListForm01();
//
//	}

}
