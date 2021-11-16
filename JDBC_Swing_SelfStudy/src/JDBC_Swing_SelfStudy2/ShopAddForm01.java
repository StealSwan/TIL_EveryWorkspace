package JDBC_Swing_SelfStudy2;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Component;
import java.awt.FileDialog;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class ShopAddForm01 extends JFrame implements ActionListener{

	//�߰� ��ư�� ���� ��, â ������ �����ϱ�
	
	//���� �Է��� textfield
	JTextField tfSang, tfSu, tfDan;
	
	//�󺧵�
	JLabel lblphoto, lbl1, lbl2, lbl3, lbl4;
	
	//�̹��� �̸��� �� ����
	String imageName;
	
	//��ư
	JButton btnImage, btnInsert;
	
	//ĵ���� ȣ���� ���� ������ ����Ŭ������ ��ü ����
	PhotoDraw photoDraw = new PhotoDraw();
	
	//Insert �޼��� ȣ��
	ShopDBModel01 dbModel01 = new ShopDBModel01();
	
	
	
	/////////////////////////////////////////////////
	//�⺻ ����
	public ShopAddForm01() {
		
		super("Shop");
		
		this.setBounds(800, 300, 400, 400);
		this.getContentPane().setBackground(Color.white);
		initDesign();
		//this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	
	//////////////////////////////////////////////////
	//�⺻ ������
	public void initDesign() {
		
		//�⺻ ���̾ƿ�
		this.setLayout(null);
		
		//�� ����
		lbl1 = new JLabel("��ǰ��");
		lbl2 = new JLabel("��������");
		lbl3 = new JLabel("����");
		lbl4 = new JLabel("�ܰ�");
		lblphoto = new JLabel("�̹�����");
		
		//ũ�� ����
		lbl1.setBounds(10, 10, 50, 30);
		lbl2.setBounds(10, 60, 50, 30);
		lbl3.setBounds(10, 150, 50, 30);
		lbl4.setBounds(10, 200, 50, 30);
		lblphoto.setBounds(10, 100, 300, 30);
		lblphoto.setBorder(new LineBorder(Color.pink));
		
		//�� �߰�
		this.add(lbl1);
		this.add(lbl2);
		this.add(lbl3);
		this.add(lbl4);
		this.add(lblphoto);
		
		
		//textfield ����
		tfSang = new JTextField();
		tfSu = new JTextField();
		tfDan = new JTextField();
		
		//ũ�⼳��
		tfSang.setBounds(80, 10, 100, 30);
		tfSu.setBounds(80, 150, 70, 30);
		tfDan.setBounds(80, 200, 70, 30);
		
		//textfield �߰�
		this.add(tfSang);
		this.add(tfSu);
		this.add(tfDan);
		
		
		//��ư ����
		btnImage = new JButton("��������");
		btnInsert = new JButton("DB�߰�");
		
		//��ư ��������
		btnImage.setBounds(80, 60, 100, 30);
		btnInsert.setBounds(170, 200, 100, 30);
		
		//��ư ����߰�
		btnImage.addActionListener(this);
		btnInsert.addActionListener(this);
		
		//��ư �߰�
		this.add(btnImage);
		this.add(btnInsert);
		
		
		//�̹��� ��ġ ���
		//ĵ���� ȣ���� ���� ������ ����Ŭ������ ������ ȣ��
		photoDraw.setBounds(250, 10, 70, 70);
		//�̹��� ���
		this.add(photoDraw);
		
	}
	
	
	/////////////////////////////////////////////////
	//ĵ���� ȣ���� ���� ���� Ŭ����
	class PhotoDraw extends Canvas{
		
		//paint �������̵�
		@Override
		public void paint(Graphics g) {
			super.paint(g);
			
			//String ������ �����ߴ� imageName
			//�̹����� ���õǾ��� ���
			if (imageName!=null) {
				
				//���õ� �̹��� �������� ����
				Image image = new ImageIcon(imageName).getImage();
				
				//������ ������ ũ�� ����
				//g�� ��ü���ϴ� draw image���� ���!!
				g.drawImage(image, 10, 10, 60, 70, this);
			}
		}
	}
	
	
	/////////////////////////////////////////////////
	//��ư ���
	@Override
	public void actionPerformed(ActionEvent e) {

		//���� ������Ʈ
		Object ob = e.getSource();
		
		//�� ��ư���� ��� ����
		if (ob==btnImage) {
			
			//���� �����ϴ� ��ü - FileDialog
			//���� ���̾�α� ���� - this ����, "�˾�â �̸�", FileDialog.LOAD!!!!
			FileDialog dlg = new FileDialog(this, "�̹��� ��������", FileDialog.LOAD);
			//â ���̰� �ϱ�
			dlg.setVisible(true);
			
			//��� ������ �޼��� ����
			//���̾�α��� ���� ����� ��
			if (dlg.getDirectory()==null) {
				return;
			}
			
			//�̹����� ���
			imageName = dlg.getDirectory() + dlg.getFile();
			
			//�̹����� �󺧿� ���
			lblphoto.setText(imageName);
			
			//�̹��� ��� �޼���
			photoDraw.repaint();
			
			
		} else if (ob==btnInsert) {
			
			//��θ� �־�����ϱ⿡ DTO Ŭ���� ����
			ShopDTO01 dto01 = new ShopDTO01();
			
			//dto�� 4���� ������ �ֱ�
			//TextField�� �ۼ��� ���� �Է��ϱ�
			dto01.setSangpum(tfSang.getText());
			dto01.setPhoto(imageName);
			dto01.setSu(Integer.parseInt(tfSu.getText())); //int�� ����Ǿ��ֱ⿡ �� ��ȯ
			dto01.setDan(Integer.parseInt(tfDan.getText()));
			
			
			//dbModel ��ü�� insert �޼��� ȣ��
			dbModel01.insertShop(dto01);
			
			//����â �ݱ�
			this.setVisible(false);
			
		}
		
	}
	
	
	public static void main(String[] args) {
		
		new ShopAddForm01();

	}

}
