package JDBC_Swing_SelfStudy2;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

//버튼 4개로 메인창을 구현할 것이다
public class ShopMain01 extends JFrame implements ActionListener{

	//db모델에 있는 메서드 호출
	ShopDBModel01 dbModel01 = new ShopDBModel01();
	
	
	//버튼 객체 설정
	JButton btnAdd, btnDel, btnUpdate, btnList; 
	
	
	/////////////////////////////////////////////
	//기본 세팅
	public ShopMain01() {
		super("Shop");
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setBounds(800, 100, 250, 300);
		this.getContentPane().setBackground(Color.white);
		//디자인 보이게 하기
		initDesign();
		this.setVisible(true);
	}
	
	
	//////////////////////////////////////////////
	//디자인
	public void initDesign() {
		
		//기본 레이아웃 - 그리드로 부여
		this.setLayout(new GridLayout(2,2)); //2행 2열로 버튼을 배치
		
		//버튼생성
		btnAdd = new JButton("상품추가");
		btnDel = new JButton("상품삭제");
		btnUpdate = new JButton("상품수정");
		btnList = new JButton("상품조회");
		
		//GridLayout으로 설정했기에 setBounds를 해줄 필요가 없다.
		
		//버튼기능
		btnAdd.addActionListener(this);
		btnDel.addActionListener(this);
		btnUpdate.addActionListener(this);
		btnList.addActionListener(this);
		
		//버튼추가
		this.add(btnAdd);
		this.add(btnDel);
		this.add(btnUpdate);
		this.add(btnList);

	}
	
	
	//////////////////////////////////////////////
	//버튼기능 구현 - 오버라이딩
	@Override
	public void actionPerformed(ActionEvent e) {
		
		//버튼 선택 객체 생성
		Object ob = e.getSource();
		
		//선택값에 따라 기능 구현
		if (ob==btnAdd) {
			ShopAddForm01 addForm01 = new ShopAddForm01();
		} else if (ob==btnDel) {
			
			//삭제 기능
			//확인을 받는 내용
			int ans = JOptionPane.showConfirmDialog(btnDel, "삭제하시려면 [예]를 눌러주세요");
			//System.out.println(ans);예:0,아니오:1,취소:2
			
			if (ans==0) {
				//출력창에서 삭제할 데이터 번호받기 - 이건 String 값임
				//btnDel에서 입력받을 번호
				String num = JOptionPane.showInputDialog("삭제할 번호를 입력하세요");
				
				//String으로 받은 num값을 int로 변환하기
				//modelDB의 delete 메서드에 num값을 넘겨주는 동시에, 인트형으로 변환
				//deleteShop에서 return된 n값이 delcode에 저장됨
				int delcode = dbModel01.deleteShop(Integer.parseInt(num));
				
				//삭제가 안되서 0반환
				if (delcode==0) {
					JOptionPane.showMessageDialog(btnDel, "해당 번호가 존재하지 않습니다");
				} else {
					JOptionPane.showMessageDialog(btnDel, "정상삭제 되었습니다");
				}
				
			}
			
			
		} else if (ob==btnUpdate) {
			
			//번호 입력 알림창
			String num = JOptionPane.showInputDialog("번호를 입력해주세요");
			
			//cancle 누를 경우
			if (num==null) {
				return; //팝업창 닫기
			}
			
			
			//중요한 건 수정할 번호의 num값을 넘겨주는 것
			//DTO 호출 객체 생성과 동시에 dbModel에 있는 getOnedata에 넘겨줌
			//해당 num의 값으로 단일 저장된 dto의 값을 return함
			ShopDTO01 dto = dbModel01.getOneData(num);
			
			
			//얻은 dto값으로 dbModel에 들어있는 내용 update 하기
			//update 객체 생성 후, 실행
			ShopUpdateForm01 updateForm01 = new ShopUpdateForm01();
			
			
			//번호 열고 수정폼 열면 해당 데이터 입력되게 하기
			//하나가 입력된 dto값을 객체로 한다.
			//각 값들에 해당 값들 넣어줌
			updateForm01.num=dto.getNum();
			updateForm01.tfSang.setText(dto.getSangpum());
			updateForm01.imageName=dto.getPhoto();
			updateForm01.tfSu.setText(String.valueOf(dto.getSu()));
			updateForm01.tfDan.setText(String.valueOf(dto.getDan()));
			updateForm01.lblphoto.setText(dto.getPhoto());
			
			//수정폼 팝업 보이게 하기
			updateForm01.setVisible(true);
			
			
			
		} else if (ob==btnList) {
			ShopListForm01 shopListForm01 = new ShopListForm01();
		}
		
	}
	
	public static void main(String[] args) {

		new ShopMain01();
		
	}

}
