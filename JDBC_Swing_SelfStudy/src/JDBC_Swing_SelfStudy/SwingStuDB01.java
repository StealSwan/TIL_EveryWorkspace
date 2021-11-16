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
	
	//DB연결
	DBConn01 db = new DBConn01();
	
	
	Container cp;	//기본 컨테이너
	DefaultTableModel model;	//테이블의 삭제, 삽입, 갱신 등이 발생할 경우 DefaultTableModel 사용
	JTable table;	//Db에서 테이블 가지고 올 경우, 필요함
	JButton btnAdd, btnDel, btnUpdate;	//추가, 삭제, 갱신의 3가지 버튼 객체
	
	//Add 프레임 추가
	AddFrame01 addFrame01 = new AddFrame01();
	
	//Update 프레임 추가
	UpdateFrame01 updateFrame01 = new UpdateFrame01();
	
	
	///////////////////////////////////////////////////////////////
	//JFrame 기본 세팅
	public SwingStuDB01() {
		//제목
		super("Swing JDBC Table");
		
		cp=this.getContentPane();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//종료
		this.setBounds(200, 100, 500, 400);	//바운즈 범위 설정
		cp.setBackground(new Color(255, 255, 200));	//배경색 설정
		initDesign();	//기본 디자인 호출
		this.setVisible(true);	//보이게 하기
	}
	
	
	///////////////////////////////////////////////////////////////
	//창 열었을 때 테이블 출력하기
	//Select
	public void showTable() {
		
		//늘 테이블 초기화는 기본
		//객체는 JDBC를 이용하기에 Jtable의 객체를 사용
		//삽입, 삭제, 갱신 등에는 늘 model을 사용 - 초기화는 model을 객체로 사용
		model.setRowCount(0);
		
		//여기는 기존 Select 메서드와 같음
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		//기본 sql문 작성 까먹지 않기!!
		String sql = "Select * from student";
		
		//후속
		try {
			conn = db.getLocalConnection();	//로컬호출
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			//출력
			while (rs.next()) {
				
				//출력문 작성
				//본래 String으로 출력문 작성해주었다면 이번에는 Swing 창에 출력할 것
				//JDBC로 Swing 테이블의 자료구조에 넣어주기
				Vector<String> data = new Vector<String>();
				//출력문 rs에서 해당 객체를 가져와 저장함
				//통상 String으로 통일해서 넣는다.
				data.add(rs.getString("num"));
				data.add(rs.getString("name"));
				data.add(rs.getString("ban"));
				data.add(rs.getString("jsp"));
				data.add(rs.getString("jsp"));
				data.add(rs.getString("spring"));
				data.add(rs.getString("total"));
				data.add(rs.getString("average"));
				
				//값이 채워진 자료구조를 모델에(화면에) 추가
				model.addRow(data);

			}
			
		} catch (SQLException e) {
			
		} finally {
			db.dbClose(rs, pstmt, conn);
		}
		
	}
	
	
	///////////////////////////////////////////////////////////////
	//Insert 추가하기
	public void insertStudent() {
		
		//기본
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		//텍스트 필드에 입력된 값을 받아와야 한다.
		String name = addFrame01.tfName.getText();
		String ban = (String) addFrame01.cbBan.getSelectedItem();	//선택된 아이템 값 가져오기
		String java = addFrame01.tfJava.getText();
		String jsp = addFrame01.tfJsp.getText();
		String spring = addFrame01.tfSpring.getText();
		
		//Total 값과 Avg 값 추가
		int tot = Integer.parseInt(java) + Integer.parseInt(jsp) + Integer.parseInt(spring);
		double avg = tot/3;
		
		
		//spl문
		//Insert into
		String sql = "Insert into student values(seq_test.nextval,?,?,?,?,?,?,?)";
		
		
		//후속
		try {
			conn = db.getLocalConnection();
			pstmt = conn.prepareStatement(sql);
			
			//값 배치
			pstmt.setString(1, name);
			pstmt.setString(2, java);
			pstmt.setString(3, jsp);
			pstmt.setString(4, spring);
			pstmt.setInt(5, tot);
			pstmt.setDouble(6, avg);
			pstmt.setString(7, ban);
			
			//업데이트
			pstmt.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.dbClose(pstmt, conn);
		}
		
	}
	
	
	///////////////////////////////////////////////////////////////
	//Update 추가하기
	public void updateStudent() {
		
		//num값 인자값 없이 받는 방법
		String num = updateFrame01.num;
		
		//기본
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		
		//TextField 입력값 가지고 오기
		String name = updateFrame01.tfName.getText();
		String cbban = (String) updateFrame01.cbBan.getSelectedItem();
		String java = updateFrame01.tfJava.getText();
		String jsp = updateFrame01.tfJsp.getText();
		String spring = updateFrame01.tfSpring.getText();
		
		//계산값 저장
		int tot = Integer.parseInt(java) + Integer.parseInt(jsp) + Integer.parseInt(spring);
		double avg = tot/3.0;
		
		//sql문 작성
		String sql = "update student set name=?, java=?, jsp=?, spring=?, total=?, average=?, ban=? where num = " + num;
				
		
		//후속
		try {
			conn = db.getLocalConnection();
			pstmt = conn.prepareStatement(sql);
			
			//값 할당
			pstmt.setString(1, name);
			pstmt.setString(2, java);
			pstmt.setString(3, jsp);
			pstmt.setString(4, spring);
			pstmt.setInt(5, tot);
			pstmt.setDouble(6, avg);
			pstmt.setString(7, cbban);
			
			//업데이트
			int a = pstmt.executeUpdate();
			if (a==1) {
				JOptionPane.showMessageDialog(this, "정상 수정되었습니다");
				showTable();
			} else {
				JOptionPane.showMessageDialog(this, "없는 번호입니다");
			}
			
			
		} catch (SQLException e) {
			
		} finally {
			db.dbClose(pstmt, conn);
		}
		
	}
	
	
	///////////////////////////////////////////////////////////////
	//Delete 추가하기
	//인자값으로 아래 num 값을 받는다
	public void deleteStudent(String num) {
		
		//기본
		Connection conn = null;
		Statement stmt = null;
		
		//sql문
		//num에 대한 정의는 아래 버튼 클릭스 기능별에 정의해놓음
		String sql = "delete from student where num=" + num;
		
		
		//후속
		try {
			conn = db.getLocalConnection();
			stmt = conn.createStatement();
			
			//업데이트
			//int로 값 받을 때 pstmt는 인식이 안됨
			int a = stmt.executeUpdate(sql);
			if (a==1) {
				//JOptionPane을 이용한 다이얼로그 출력
				JOptionPane.showMessageDialog(this, "삭제되었습니다");
				//테이블 초기화
				showTable();
			} else {
				JOptionPane.showMessageDialog(this, "없는 번호입니다");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.dbClose(stmt, conn);
		}
	}

	
	///////////////////////////////////////////////////////////////
	//기본 디자인
	public void initDesign() {
		
		//출력할 내용들 이름표
		String [] title = {"번호", "이름", "반", "Java", "Jsp", "Spring", "총점", "평균"};
		model = new DefaultTableModel(title, 0);	//모델 객체 생성 + 입력한 타이틀 이름표들 상단에 쭉 출력
		table = new JTable(model);	//테이블에 모델 추가
		
		//테이블을 판 한 가운데 추가
		this.add("Center", new JScrollPane(table));
		
		//시작하면 바로 테이블 보일 수 있게하기
		//보통 확인을 위해서 디자인부터 어느정도 만들어놓는 게 좋음
		showTable();
		
		
		//판넬
		//단순공백 주기
		JPanel pTop = new JPanel();
		this.add("North",pTop);
		
		
		//버튼 추가
		btnAdd = new JButton("추가");
		btnDel = new JButton("삭제");
		btnUpdate = new JButton("수정");
		
		//버튼에 기능 추가
		btnAdd.addActionListener(this);
		btnDel.addActionListener(this);
		btnUpdate.addActionListener(this);
		
		//버튼 화면에 추가
		//판넬 위에 추가
		pTop.add(btnAdd);
		pTop.add(btnDel);
		pTop.add(btnUpdate);
		
		
		//추후, addFrame의 기능 구현시
		//팝업 창의 "추가" 버튼 눌렀을 때 기능 넣어주기
		addFrame01.btnAdd.addActionListener(this);

		
		//추후, UpdateFrame의 기능 구현시
		//팝업 창의 "수정" 버튼 눌렀을 때 기능 넣어주기
		updateFrame01.btnMod.addActionListener(this);

	}

	
	////////////////////////////////////////////////////////
	//버튼 기능 구현
	@Override
	public void actionPerformed(ActionEvent e) {

		//누르는 버튼에 따라 기능 구현
		//클릭 오브젝트 생성
		Object ob = e.getSource();
		
		if (ob==btnAdd) {
			//AddFrame 보이게 하기
			addFrame01.setVisible(true);	
		} else if (ob==addFrame01.btnAdd) {
			//Insert 메서드
			insertStudent();
			
			//추가후 테이블초기화
			//다시 테이블 보여야 함
			this.showTable();
			addFrame01.tfName.setText("");
			addFrame01.tfJava.setText("");
			addFrame01.tfJsp.setText("");
			addFrame01.tfSpring.setText("");

			//마우스 커서
			addFrame01.tfName.requestFocus();
			
			//추가가 끝났으면 창 열린 거 사라지게 하기
			addFrame01.setVisible(false);
			
		} else if (ob==btnDel) {
			//삭제 버튼을 누를 경우, 몇번을 삭제할 것인지 팝업창에서 물어보게 하기
			String num = JOptionPane.showInputDialog("삭제할 번호를 입력하세요");
			deleteStudent(num);
			
		} else if (ob==btnUpdate) {
			
			String num = JOptionPane.showInputDialog("몇번을 수정하시겠습니까?");
			
			String sql = "Select * from student where num = " + num;
			
			//여기서 조회를 구현
			Connection conn = null;
			Statement stmt = null;
			ResultSet rs = null;
			
			//후속
			try {
				conn = db.getLocalConnection();
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql);
				
				//하나만 출력할 경우 while이 아니라 if
				if (rs.next()) {
					
					//보이지는 않으나, UpdateFrame에 값 넘겨주기
					//수정과 삭제는 num값이 넘어간다
					updateFrame01.num=num;
					
					//세팅해주기
					updateFrame01.tfName.setText(rs.getString("name"));
					updateFrame01.tfJava.setText(rs.getString("java"));
					updateFrame01.tfJsp.setText(rs.getString("jsp"));
					updateFrame01.tfSpring.setText(rs.getString("spring"));
					updateFrame01.cbBan.setSelectedItem(rs.getString("ban"));
					
					//보이게 하기
					updateFrame01.setVisible(true);
					
				} else {	//데이터가 없을 경우
					JOptionPane.showMessageDialog(this, "없는 번호입니다");
				}
				
			} catch (SQLException e1) {
				
			} finally {
				db.dbClose(rs, stmt, conn);
			}
			
		} else if (ob==updateFrame01.btnMod) {
			
			//Update 메서드
			updateStudent();
			
			//추가후 테이블초기화
			//다시 테이블 보여야 함
			this.showTable();
			//수정되면 값은 그대로 남아있게 하고 싶으면 아래 안함
//			addFrame01.tfName.setText("");
//			addFrame01.tfJava.setText("");
//			addFrame01.tfJsp.setText("");
//			addFrame01.tfSpring.setText("");
			
			//추가가 끝났으면 창 열린 거 사라지게 하기
			updateFrame01.setVisible(false);
			
		} 
		
		
	}


	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new SwingStuDB01();

	}

}
