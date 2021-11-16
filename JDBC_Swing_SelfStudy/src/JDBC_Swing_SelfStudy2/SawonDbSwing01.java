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

	//기본 컨테이너
	Container cp;
	
	//접속 객체
	DBConn01 db = new DBConn01();
	
	//삽입 삭제를 위한 기본 테이블 모델 생성
	DefaultTableModel model;
	
	//테이블 로드용 테이블 객체 생성
	JTable table;
	
	//라디오 버튼 크기만 할당
	JRadioButton rb [] = new JRadioButton[6];
	
	//추가, 삭제 버튼
	JButton btnAdd, btnDel;
	
	
	//////////////////////////////////////////
	//기본 세팅
	public SawonDbSwing01() {
		
		super("사원관리");
		
		cp=this.getContentPane();
		cp.setBackground(new Color(255, 255, 200));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(200, 100, 700, 500);
		
		//디자인 보이게 하기
		initDesign();
		//테이블 생성후 db로부터 데이터 거져오기
		showTable(0);
		this.setVisible(true);
		
	}
	
	//////////////////////////////////////////
	//JDBC Oracle과 연결
	public void showTable(int select) {
		
		//모델 초기화, 테이블 닦기 - Row 카운트를 0으로 만들어준다
		model.setRowCount(0);
		
		//JDBC 연결하기
		Connection conn = db.getLocalConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		//sql문
		String sql = "";
		
		
		//라디오 버튼 선택값에 따라 보이고 싶은 값을 다르게 하고 싶을 때
		if (select==0) {
			sql = "Select ROWNUM no, num id, name, buseo, gender, to_char(pay,'L999,999,999') pay from sawon";
		} else if (select==1) {
			sql = "Select ROWNUM no, num id, name, buseo, gender, to_char(pay,'L999,999,999') pay from sawon where gender = '여자'";
		} else if (select==2) {
			sql = "Select ROWNUM no, num id, name, buseo, gender, to_char(pay,'L999,999,999') pay from sawon where gender = '남자'";
		} else if (select==3) {
			sql = "Select ROWNUM no, num id, name, buseo, gender, to_char(pay,'L999,999,999') pay from sawon where buseo = '교육부'";
		} else if (select==4) {
			sql = "Select ROWNUM no, num id, name, buseo, gender, to_char(pay,'L999,999,999') pay from sawon where buseo = '홍보부'";
		} else if (select==5) {
			sql = "Select ROWNUM no, num id, name, buseo, gender, to_char(pay,'L999,999,999') pay from sawon where buseo = '관리부'";
		}
		
		
		//후속
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			//출력문
			while (rs.next()) {
				
				//본래 출력문을 넣어주지만, 테이블을 JScrollPane에 올릴 것이기에 자료구조에 담아주는 작업이 필요하다
				Vector<String> data = new Vector<String>();
				
				//데이터를 추가 - rs로 출력할 값의 특정 변수를 가져옴
				//가져올땐 문자열로 가지고 와도 된다
				data.add(rs.getString("no"));
				data.add(rs.getString("id"));
				data.add(rs.getString("name"));
				data.add(rs.getString("buseo"));
				data.add(rs.getString("gender"));
				data.add(rs.getString("pay"));
				
				//비어있던 모델에 추가
				model.addRow(data);
				
				
				//Select문에 execute는 하지 않으니 주의하자!!
				//pstmt.execute();
				
				
			}
			
		} catch (SQLException e) {
			
		} finally {
			db.dbClose(rs, pstmt, conn);
		}
	}
	
	
	//////////////////////////////////////////
	//기본 디자인
	public void initDesign() {
		
		//기본 레이아웃 설정
		this.setLayout(null);
		
		//테이블 추가 작업부터 시작
		//table 출력할 위에 네임 태그 작성
		String [] title = {"No", "ID", "사원명", "부서명", "성별", "급여"};
		
		//테이블에 모델 추가
		model = new DefaultTableModel(title, 100);	//title 이라는 이름의 Vector를 테이블 형식으로 표현할 것이고, 행갯수 얼마나 추가할지 여부 - Vector는 가변적이라 일단 0으로 설정
		table = new JTable(model);
		
		//JScrollPane에 테이블 추가
		JScrollPane js = new JScrollPane(table);
		//JScrollPane 크기 설정
		js.setBounds(10, 100, 670, 350);
		this.add(js);
		
		
		//버튼 생성
		btnAdd = new JButton("추가");
		btnDel = new JButton("삭제");
		
		//크기 설정
		btnAdd.setBounds(225, 25, 100, 30);
		btnDel.setBounds(375, 25, 100, 30);
		
		//버튼에 액션 추가
		btnAdd.addActionListener(this);
		btnDel.addActionListener(this);
		
		//버튼 추가
		this.add(btnAdd);
		this.add(btnDel);
		
		
		//라디오 버튼 추가
		//단일 선택을 위한 ButtonGroup
		ButtonGroup bg = new ButtonGroup();
		
		//버튼명들을 담을 배열 생성
		String [] rbTitle = {"전체", "여자", "남자", "교육부", "홍보부", "관리부"};
		
		//배치의 편의를 위해 xpos 값 설정
		int xpos = 60;
		
		//반복문으로 버튼 추가
		for (int i = 0; i < rbTitle.length; i++) {
			//위에서는 JRadioButton을 크기만 선언만 하였으니, 여기서 동시에 이름 설정
			rb[i] = new JRadioButton(rbTitle[i], i==0 ? true:false);	//처음 선택될 곳 삼항 연산자로 추가
			//라디오 버튼 위치 설정
			rb[i].setBounds(xpos, 65, 90, 30);
			//라디오 버튼 투명도
			rb[i].setOpaque(false);
			//라디오 버튼 기능 추가 - ItemListener로 작업
			rb[i].addItemListener(this);
			//라디오 버튼 그룹에 추가 - 한개씩만 선택되도록
			bg.add(rb[i]);
			//라디오 버튼 추가
			this.add(rb[i]);
			
			//xpos값 증가
			xpos += 100;
			
		}
		
	}
	
	
	
	/////////////////////////////////////////
	//오버라이딩
	//버튼 기능
	@Override
	public void actionPerformed(ActionEvent e) {
		
		//클릭 객체 생성
		Object ob = e.getSource();
		
		//버튼을 누르고 창이 나타날 경우, 거기서의 값도 Oracle에 연동되어야 하기에 접속 연사자 필요
		Connection conn = db.getLocalConnection();
		PreparedStatement pstmt = null;
		
		//작업을 실행할 기본 sql문 선언
		String sql = "";
		
		
		//어떤 버튼을 누르냐에 따라 기능구현
		if (ob==btnAdd) {
			
			//질문창 4개 팝업
			//이름, 성별, 부서, 급여
			//JOptionPane.showInputDialog로 설정
			String name = JOptionPane.showInputDialog("이름을 입력하세요");
			String gender = JOptionPane.showInputDialog("성별을 입력하세요");
			String buseo = JOptionPane.showInputDialog("부서를 입력하세요");
			String pay = JOptionPane.showInputDialog("급여를 입력하세요");
			
			//위의 변수를 토대로 sql문 작성
			//insert문 작성에 요주의
			sql = "insert into sawon values(seq_sawon.nextval, ?, ?, ?, ?)";
			
			
			//후속
			try {
				pstmt = conn.prepareStatement(sql);
				
				//값배정
				pstmt.setString(1, name);
				pstmt.setString(2, gender);
				pstmt.setString(3, buseo);
				pstmt.setString(4, pay);
				
				//업데이트
				pstmt.execute();
				
				//추가하고나면 추가된 테이블 "전체"로 다시 보여주기 - 인자값 0번 전달
				this.showTable(0);
				
				//추가하고 나면 "전체" 라디오 버튼으로 자동 선택되도록 하기
				rb[0].setSelected(true);
				
			} catch (SQLException e1) {
				
			} finally {
				db.dbClose(pstmt, conn);
			}
			
			
		} else if (ob==btnDel) {
			
			//선택한 행번호 저장
			int row = table.getSelectedRow(); //선택하면 특정값을 반환
			
			//아무것도 선택하지 못할 경우, -1을 반환
			//선택안했을 때, 
			if (row==-1) {
				
				//경고 메시지 출력
				JOptionPane.showMessageDialog(this, "행을 선택해주세요");
				//메서드 빠져나오기
				return;
			}
			
			//선택한 행의 id 값을 모델로부터 얻어야 한다.
			//모델로부터 행의 첫번째 값을 가져온다.
			String num = (String) model.getValueAt(row, 1);
			
			
			//얻은 id값을 바탕으로 sql문을 작성
			//sql문 작성에 유의
			sql = "delete from sawon where num = ?";
			
			
			//sql문을 작성했다면, 후속 작업이 필수적
			try {
				pstmt =conn.prepareStatement(sql);
				
				//바인딩
				pstmt.setString(1, num);
				
				//업데이트
				pstmt.execute();
				
				//삭제한 후, 결과값 테이블 "전체"값 불러오기
				this.showTable(0);
				
				//라디오 버튼도 "전체"로 맞춰주기
				rb[0].setSelected(true);
				
				
			} catch (SQLException e1) {
				
			} finally {
				db.dbClose(pstmt, conn);
			}
			
			
		}
		
	}
	
	
	/////////////////////////////////////////
	//오버라이딩
	//라디오 버튼 기능 - ItemListener
	@Override
	public void itemStateChanged(ItemEvent e) {
		
		//값 클릭시 객체 생성
		Object ob = e.getSource();
		
		//select값 지정
		int select = 0;
		
		//라디오 버튼 길이만큼 출력
		for (int i = 0; i < rb.length; i++) {
			
			if (ob==rb[i]) {
				select = i;
			}
			
		} showTable(select); //showTable 메서드 호출
		
	}
	
	
	
	
	public static void main(String[] args) {
		
		new SawonDbSwing01();

	}

}
