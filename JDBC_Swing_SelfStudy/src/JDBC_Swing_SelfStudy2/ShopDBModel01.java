package JDBC_Swing_SelfStudy2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import JDBC_Swing_SelfStudy.DBConn01;
import oracle.security.crypto.core.RSA;

public class ShopDBModel01 {

	//db�� ������ ����� ������ Ŭ����
	
	//Ŀ�ؼ� �⺻
	DBConn01 db = new DBConn01();
	
	
	//////////////////////////////////////////
	//Select
	//Vector�� ���ϰ����� ���� ����ϴ� ���� - �̸� ���� ��ü ����� �����ϰ� �Ѵ�.
	public Vector<ShopDTO01> getAllSangPum() {
		
		//�⺻�� �Ǵ� ���� ����
		//�߿��� �� �ڷ����� DTO Ŭ������ ���ٴ� ���̴�
		Vector<ShopDTO01> list = new Vector<ShopDTO01>();
		
		//DB�� �����Ͽ� ���� �޾ƿ� ���̱⿡ ���� ������ �ʿ��ϴ�
		Connection conn = db.getLocalConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		//��� sql�� �ۼ�
		String sql = "Select * from shopping order by sangpum";
		
		
		//�ļ�
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			//���
			while (rs.next()) {
				
				//ȣ���� Ŭ������ ��ü�� ����
				ShopDTO01 dto = new ShopDTO01();
				
				//dto Ŭ���� setter ���Ͽ� rs�� ��µǴ� ���� �����Ѵ� 
				//set���� �� ��°��� ������ �������� ����.
				dto.setNum(rs.getString("num"));
				dto.setSangpum(rs.getString("sangpum"));
				dto.setPhoto(rs.getString("photo"));
				dto.setSu(rs.getInt("su"));
				dto.setDan(rs.getInt("dan"));
				dto.setGuipday(rs.getDate("guipday"));
				
				//rs�� �ҷ��� DTO setter�� �־��µ�, �̰� list�� �߰�
				list.add(dto);
						
			}
		} catch (SQLException e) {
			
		} finally {
			db.dbClose(rs, pstmt, conn);
		}
		
		//list �� ����
		return list;
		
	}
	
	
	//////////////////////////////////////////
	//insert
	//DTO�� ���ڰ����� �޴� insert �޼���
	public void insertShop(ShopDTO01 dto) {
		
		//�⺻������ insert ���� DB�� ������ �ؾ��Ѵ�
		Connection conn = db.getLocalConnection();
		PreparedStatement pstmt = null;
		
		//sql��
		//sql�� �ۼ� ������!!
		String sql = "insert into shopping values(seq_shopping.nextval,?,?,?,?,sysdate)";
		
		
		//�ļ�
		try {
			pstmt = conn.prepareStatement(sql);
			
			//�� ���ε�
			//dto setter�� ����� ������ �����ͼ� ����
			pstmt.setString(1, dto.getSangpum());
			pstmt.setString(2, dto.getPhoto());
			pstmt.setInt(3, dto.getSu());
			pstmt.setInt(4, dto.getDan());
			
			//������Ʈ
			pstmt.execute();
			
		} catch (SQLException e) {
			
		} finally {
			db.dbClose(pstmt, conn);
		}
		
	}
	
	
	///////////////////////////////////////////////////////////
	//getOneData �ϳ��� ���� ���޹���
	//!!!��ǥ�� ���޹��� num���� ��� -  ����� ���� dto�� �־��ش�. 
	//update �˾�â���� ���� String num���� ���ڷ� ����
	//return ������ ���� ����� dto�� ��ȯ
	public ShopDTO01 getOneData(String num) {
		
		//DTO�� ���� �������־�� �� - DTO ȣ��
		ShopDTO01 dto = new ShopDTO01();
		
		//����
		Connection conn = db.getLocalConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "Select * from shopping where num=?";
		
		
		//�ļ�
		try {
			pstmt = conn.prepareStatement(sql);			
			
			//�Ҵ�
			pstmt.setString(1, num);
			
			//!!!!!
			//������� �Ҵ���� ������ �� �ڿ� ����!!!
			rs= pstmt.executeQuery();
			
			
			//!!!�ϳ��� ����� �� if������ ���
			if (rs.next()) {
				
				//rs���� ��µ� ������ dto�� �־���
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
		
		//dto�� ��ȯ
		return dto;
	}
	
	
	//Update
	public void updateShop(ShopDTO01 dto) {
		
		//����
		Connection conn = db.getLocalConnection();
		PreparedStatement pstmt = null;
		
		//sql�� �ۼ�����!!!
		String sql = "update shopping set sangpum=?,su=?,dan=?,photo=? where num=?";
		
		//�ļ�
		try {
			pstmt = conn.prepareStatement(sql);
			
			//�� ���
			//�Է¹��� dto���� ���� ���
			pstmt.setString(1, dto.getSangpum());
			pstmt.setInt(2, dto.getSu());
			pstmt.setInt(3, dto.getDan());
			pstmt.setString(4, dto.getPhoto());
			pstmt.setString(5, dto.getNum());
			
			//������Ʈ
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
	//�˾�â�� �Է��� num���� �޾Ƽ� ����
	//���� ������� �����־�� �ϱ⿡ int ��ȯ
	public int deleteShop(int num) {
		
		//�⺻ �޼���� DB�������
		Connection conn = db.getLocalConnection();
		PreparedStatement pstmt = null;
		
		//sql��
		String sql = "delete from shopping sql where num=?";
		
		//���� �����Ǿ����� �ƴ��� �˷��ִ� n�� ����
		int n = 0;
		
		//�ļ�
		try {
			pstmt = conn.prepareStatement(sql);
			
			//�Ҵ�
			pstmt.setInt(1, num);
			
			//������Ʈ + ���� ����� n�� ����ֱ�
			n = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			
		}
		
		return n;
	} 
	


}
