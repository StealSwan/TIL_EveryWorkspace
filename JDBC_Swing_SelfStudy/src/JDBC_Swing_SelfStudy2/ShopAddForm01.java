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

	//추가 버튼을 누를 때, 창 새로이 구현하기
	
	//값을 입력할 textfield
	JTextField tfSang, tfSu, tfDan;
	
	//라벨들
	JLabel lblphoto, lbl1, lbl2, lbl3, lbl4;
	
	//이미지 이름이 들어갈 변수
	String imageName;
	
	//버튼
	JButton btnImage, btnInsert;
	
	//캔버스 호출을 위해 별개의 내부클래스는 객체 생성
	PhotoDraw photoDraw = new PhotoDraw();
	
	//Insert 메서드 호출
	ShopDBModel01 dbModel01 = new ShopDBModel01();
	
	
	
	/////////////////////////////////////////////////
	//기본 세팅
	public ShopAddForm01() {
		
		super("Shop");
		
		this.setBounds(800, 300, 400, 400);
		this.getContentPane().setBackground(Color.white);
		initDesign();
		//this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	
	//////////////////////////////////////////////////
	//기본 디자인
	public void initDesign() {
		
		//기본 레이아웃
		this.setLayout(null);
		
		//라벨 생성
		lbl1 = new JLabel("상품명");
		lbl2 = new JLabel("사진선택");
		lbl3 = new JLabel("수량");
		lbl4 = new JLabel("단가");
		lblphoto = new JLabel("이미지명");
		
		//크기 설정
		lbl1.setBounds(10, 10, 50, 30);
		lbl2.setBounds(10, 60, 50, 30);
		lbl3.setBounds(10, 150, 50, 30);
		lbl4.setBounds(10, 200, 50, 30);
		lblphoto.setBounds(10, 100, 300, 30);
		lblphoto.setBorder(new LineBorder(Color.pink));
		
		//라벨 추가
		this.add(lbl1);
		this.add(lbl2);
		this.add(lbl3);
		this.add(lbl4);
		this.add(lblphoto);
		
		
		//textfield 생성
		tfSang = new JTextField();
		tfSu = new JTextField();
		tfDan = new JTextField();
		
		//크기설정
		tfSang.setBounds(80, 10, 100, 30);
		tfSu.setBounds(80, 150, 70, 30);
		tfDan.setBounds(80, 200, 70, 30);
		
		//textfield 추가
		this.add(tfSang);
		this.add(tfSu);
		this.add(tfDan);
		
		
		//버튼 생성
		btnImage = new JButton("사진선택");
		btnInsert = new JButton("DB추가");
		
		//버튼 범위생성
		btnImage.setBounds(80, 60, 100, 30);
		btnInsert.setBounds(170, 200, 100, 30);
		
		//버튼 기능추가
		btnImage.addActionListener(this);
		btnInsert.addActionListener(this);
		
		//버튼 추가
		this.add(btnImage);
		this.add(btnInsert);
		
		
		//이미지 위치 출력
		//캔버스 호출을 위해 별개의 내부클래스는 위에서 호출
		photoDraw.setBounds(250, 10, 70, 70);
		//이미지 출력
		this.add(photoDraw);
		
	}
	
	
	/////////////////////////////////////////////////
	//캔버스 호출을 위한 내부 클래스
	class PhotoDraw extends Canvas{
		
		//paint 오버라이딩
		@Override
		public void paint(Graphics g) {
			super.paint(g);
			
			//String 값으로 선언했던 imageName
			//이미지가 선택되었을 경우
			if (imageName!=null) {
				
				//선택된 이미지 아이콘을 생성
				Image image = new ImageIcon(imageName).getImage();
				
				//생성한 아이콘 크기 설정
				//g를 객체로하는 draw image임을 명심!!
				g.drawImage(image, 10, 10, 60, 70, this);
			}
		}
	}
	
	
	/////////////////////////////////////////////////
	//버튼 기능
	@Override
	public void actionPerformed(ActionEvent e) {

		//선택 오브젝트
		Object ob = e.getSource();
		
		//각 버튼마다 기능 구현
		if (ob==btnImage) {
			
			//파일 선택하는 객체 - FileDialog
			//파일 다이얼로그 생성 - this 여기, "팝업창 이름", FileDialog.LOAD!!!!
			FileDialog dlg = new FileDialog(this, "이미지 가져오기", FileDialog.LOAD);
			//창 보이게 하기
			dlg.setVisible(true);
			
			//취소 누르면 메서드 종료
			//다이얼로그의 값이 비었을 때
			if (dlg.getDirectory()==null) {
				return;
			}
			
			//이미지명 얻기
			imageName = dlg.getDirectory() + dlg.getFile();
			
			//이미지명 라벨에 출력
			lblphoto.setText(imageName);
			
			//이미지 출력 메서드
			photoDraw.repaint();
			
			
		} else if (ob==btnInsert) {
			
			//경로를 넣어줘야하기에 DTO 클래스 생성
			ShopDTO01 dto01 = new ShopDTO01();
			
			//dto에 4개의 데이터 넣기
			//TextField에 작성된 내용 입력하기
			dto01.setSangpum(tfSang.getText());
			dto01.setPhoto(imageName);
			dto01.setSu(Integer.parseInt(tfSu.getText())); //int로 저장되어있기에 값 변환
			dto01.setDan(Integer.parseInt(tfDan.getText()));
			
			
			//dbModel 객체의 insert 메서드 호출
			dbModel01.insertShop(dto01);
			
			//현재창 닫기
			this.setVisible(false);
			
		}
		
	}
	
	
	public static void main(String[] args) {
		
		new ShopAddForm01();

	}

}
