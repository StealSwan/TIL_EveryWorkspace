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
	
	//테이블 호출을 위한 내용들 준비
	
	//DCL 메서드 호출을 위해 모아놓았던 객체 생성
	ShopDBModel01 dbModel01 = new ShopDBModel01();
	
	//입출력을 위한 테이블 기본 모델과 테이블 생성
	DefaultTableModel model;
	JTable table;
	
	//출력을 위해 Vector에 저장된 값 호출 - DTO를 객체로 생성하는 게 아니라, Vector가 DTO를 인자로 받음
	Vector<ShopDTO01> list;
	
	//이미지 받기 & 출력 - 내부클래스
	ImageDraw imageDraw = new ImageDraw();
	String imageName;
	
	//새로고침 버튼
	JButton btnRefresh;
	
	
	//////////////////////////////////////////////
	//기본 세팅
	public ShopListForm01() {
		
		super("상품 전체 목록");	
		this.setBounds(800, 100, 700, 400);
		this.getContentPane().setBackground(Color.white);
		initDesign();
		//this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	
	//////////////////////////////////////////////
	//디자인
	public void initDesign() {
		
		//기본 레이아웃
		this.setLayout(null);

		
		//이미 ShopDBModel에서 getAllSangpum이라는 이름으로
		//Select 메서드를 return값을 Vector로 하는 list를 만들었다.
		//여기에 접속 메서드를 비롯해 각 rs 값이 Vector로 저장되어있다.
		//따라서 이 값을 불러와야한다.
		//이 값이 곧 여기서 쓰일 Vector의 list 값과 같다.
		list = dbModel01.getAllSangPum();
		
		
		//테이블 출력명 출력
		String [] title = {"Num", "상품명", "수량", "단가", "총금액", "구입일"};
		
		//디폴트 모델과 테이블 추가 + JScrollPane
		model = new DefaultTableModel(title, 0); //타이틀 입력과 행추가
		table = new JTable(model);
		JScrollPane js = new JScrollPane(table);
		js.setBounds(10, 10, 500, 260);
		this.add(js);
		
		
		//버튼 추가
		btnRefresh = new JButton("새로고침");
		btnRefresh.setBounds(250, 300, 150, 30);
		btnRefresh.addActionListener(this);
		add(btnRefresh);
		
		//테이블 출력
		showTable();
		
		
		//이미지 추가
		imageDraw.setBounds(510, 50, 170, 220);
		this.add(imageDraw);
		
		
		
		//테이블에 마우스 기능추가
		//테이블 이벤트_익명내부 클래스
		//MouseListener로 하면 오버라이딩할게 너무 많아서 MouseAdapter로 한다.
		table.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				
				//행번호 얻고 해당 행에 해당하는 dto를 꺼내야함 - getter
				int row = table.getSelectedRow();
				
				//list에서 row에 해당하는 photo경로의 dto를 꺼내서 이미지명을 얻는다
				imageName = list.get(row).getPhoto();
				
				//paint 메서드 다시 호출
				imageDraw.repaint();
				
			}
			
		});
	}
	
	
	/////////////////////////////////////////////
	//이제까지 늘 showTable을 별개의 메서드로 출력했다!!!
	//showTable
	public void showTable() {
		
		//테이블을 출력
		//처음엔 모델 초기화
		model.setRowCount(0);
		
		//테이블에 데이터 추가
		//Vector ist에 담긴 값들을 쭉 출력
		//for문 출력
		for (ShopDTO01 dto : list) {
			
			//새로 출력 객체용 Vector 생성 - String형 Vector 생성
			//출력할 것이기에 String형으로 생성
			Vector<String> data = new Vector<String>();
			
			//Vector에 데이터추가
			data.add(dto.getNum());
			data.add(dto.getSangpum());
			data.add(String.valueOf(dto.getSu()));
			data.add(String.valueOf(dto.getDan()));
			data.add(String.valueOf(dto.getSu()*dto.getDan()));
			data.add(String.valueOf(dto.getGuipday()));
			
			//추가된 Vector를 model에 추가
			model.addRow(data);
		}
		
	}
	
	
	/////////////////////////////////////////////
	//Refresh 버튼기능
	@Override
	public void actionPerformed(ActionEvent e) {
		
		//리스트에 있는 상품을 다시 호출
		list = dbModel01.getAllSangPum();
		
		//그걸 기반으로 다시 테이블 출력
		showTable();
	}
	
	
	/////////////////////////////////////////////
	//이미지를 위한 내부 클래스
	class ImageDraw extends Canvas{
		
		//paint 메서드 오버라이딩
		@Override
		public void paint(Graphics g) {
			
			super.paint(g);
			
			//이미지 가지고 오기
			Image image = new ImageIcon(imageName).getImage();
			//이미지 크기 설정
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
