package JDBC_Swing_SelfStudy2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import JDBC_Swing_SelfStudy.DBConn01;
import oracle.security.crypto.core.RSA;

public class ShopDBModel01 {

	//db에 관련한 내용들 정리한 클래스
	
	//커넥션 기본
	DBConn01 db = new DBConn01();
	
	
	//////////////////////////////////////////
	//Select
	//Vector를 리턴값으로 값을 출력하는 내용 - 이를 통해 전체 출력을 가능하게 한다.
	public Vector<ShopDTO01> getAllSangPum() {
		
		//기본이 되는 백터 선언
		//중요한 건 자료형에 DTO 클래스가 들어간다는 것이다
		Vector<ShopDTO01> list = new Vector<ShopDTO01>();
		
		//DB에 접속하여 값을 받아올 것이기에 접속 내용이 필요하다
		Connection conn = db.getLocalConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		//출력 sql문 작성
		String sql = "Select * from shopping order by sangpum";
		
		
		//후속
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			//출력
			while (rs.next()) {
				
				//호출할 클래스의 객체를 생성
				ShopDTO01 dto = new ShopDTO01();
				
				//dto 클래스 setter 파일에 rs로 출력되는 값을 저장한다 
				//set에는 각 출력값의 데이터 변수들이 들어간다.
				dto.setNum(rs.getString("num"));
				dto.setSangpum(rs.getString("sangpum"));
				dto.setPhoto(rs.getString("photo"));
				dto.setSu(rs.getInt("su"));
				dto.setDan(rs.getInt("dan"));
				dto.setGuipday(rs.getDate("guipday"));
				
				//rs를 불러와 DTO setter에 넣었는데, 이걸 list에 추가
				list.add(dto);
						
			}
		} catch (SQLException e) {
			
		} finally {
			db.dbClose(rs, pstmt, conn);
		}
		
		//list 값 리턴
		return list;
		
	}
	
	
	//////////////////////////////////////////
	//insert
	//DTO를 인자값으로 받는 insert 메서드
	public void insertShop(ShopDTO01 dto) {
		
		//기본적으로 insert 역시 DB에 연결을 해야한다
		Connection conn = db.getLocalConnection();
		PreparedStatement pstmt = null;
		
		//sql문
		//sql문 작성 요주의!!
		String sql = "insert into shopping values(seq_shopping.nextval,?,?,?,?,sysdate)";
		
		
		//후속
		try {
			pstmt = conn.prepareStatement(sql);
			
			//값 바인딩
			//dto setter에 저장된 내용을 가져와서 세팅
			pstmt.setString(1, dto.getSangpum());
			pstmt.setString(2, dto.getPhoto());
			pstmt.setInt(3, dto.getSu());
			pstmt.setInt(4, dto.getDan());
			
			//업데이트
			pstmt.execute();
			
		} catch (SQLException e) {
			
		} finally {
			db.dbClose(pstmt, conn);
		}
		
	}
	
	
	///////////////////////////////////////////////////////////
	//getOneData 하나의 값만 전달받음
	//!!!목표는 전달받은 num값만 출력 -  출력한 값을 dto에 넣어준다. 
	//update 팝업창에서 적힌 String num값을 인자로 받음
	//return 값으로 단일 저장된 dto를 반환
	public ShopDTO01 getOneData(String num) {
		
		//DTO에 값을 수정해주어야 함 - DTO 호출
		ShopDTO01 dto = new ShopDTO01();
		
		//접속
		Connection conn = db.getLocalConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "Select * from shopping where num=?";
		
		
		//후속
		try {
			pstmt = conn.prepareStatement(sql);			
			
			//할당
			pstmt.setString(1, num);
			
			//!!!!!
			//결과값은 할당까지 끝나고 난 뒤에 실행!!!
			rs= pstmt.executeQuery();
			
			
			//!!!하나만 출력할 땐 if문으로 출력
			if (rs.next()) {
				
				//rs에서 출력된 값들을 dto에 넣어줌
				dto.setNum(rs.getString("num"));
				dto.setSangpum(rs.getString("sangpum"));
				dto.setPhoto(rs.getString("photo"));
				dto.setSu(rs.getInt("su"));
				dto.setDan(rs.getInt("dan"));
				dto.setGuipday(rs.getDate("guipday"));
				
			}
	
		} catch (SQLException e) {
			
		} finally {
			db.dbClose(rs, pstmt, conn);
		}
		
		//dto값 반환
		return dto;
	}
	
	
	//Update
	public void updateShop(ShopDTO01 dto) {
		
		//연결
		Connection conn = db.getLocalConnection();
		PreparedStatement pstmt = null;
		
		//sql문 작성주의!!!
		String sql = "update shopping set sangpum=?,su=?,dan=?,photo=? where num=?";
		
		//후속
		try {
			pstmt = conn.prepareStatement(sql);
			
			//값 배분
			//입력받은 dto에서 값을 출력
			pstmt.setString(1, dto.getSangpum());
			pstmt.setInt(2, dto.getSu());
			pstmt.setInt(3, dto.getDan());
			pstmt.setString(4, dto.getPhoto());
			pstmt.setString(5, dto.getNum());
			
			//업데이트
			pstmt.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.dbClose(pstmt, conn);
		}
		
	}
	
	
	
	//////////////////////////////////////////
	//Delete
	//팝업창에 입력한 num값을 받아서 삭제
	//삭제 결과값을 돌려주어야 하기에 int 반환
	public int deleteShop(int num) {
		
		//기본 메서드는 DB연결부터
		Connection conn = db.getLocalConnection();
		PreparedStatement pstmt = null;
		
		//sql문
		String sql = "delete from shopping sql where num=?";
		
		//정상 삭제되었는지 아닌지 알려주는 n값 설정
		int n = 0;
		
		//후속
		try {
			pstmt = conn.prepareStatement(sql);
			
			//할당
			pstmt.setInt(1, num);
			
			//업데이트 + 삭제 결과값 n에 담아주기
			n = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			
		}
		
		return n;
	} 
	


}
