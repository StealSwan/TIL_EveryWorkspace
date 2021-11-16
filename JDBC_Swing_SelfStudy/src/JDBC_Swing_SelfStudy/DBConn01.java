package JDBC_Swing_SelfStudy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConn01 {
	
	static final String ORACLE_LOCAL = "jdbc:oracle:thin:@localhost:1521:orcl";

	//Connection
	public Connection getLocalConnection() {
		
		Connection conn = null;

		try {
			conn = DriverManager.getConnection(ORACLE_LOCAL,"SWAN","1234");
		} catch (SQLException e) {

		}
		
		//return 값을 돌려주기 위해 conn은 try-catch 안에 있으면 안된다
		return conn;
	}
	
	////////////////////////////////////////////////////////////////////////
	//4개의 Close 메서드
	
	
	//Result Set - Statement
	public void dbClose(ResultSet rs, Statement stmt, Connection conn) {
		// TODO Auto-generated constructor stub
		try {
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	//Result Set - PreparedStatement
	public void dbClose(ResultSet rs, PreparedStatement pstmt, Connection conn) {
		// TODO Auto-generated constructor stub
		try {
			rs.close();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	//Statemnet
	public void dbClose(Statement stmt, Connection conn) {
		// TODO Auto-generated constructor stub
		try {
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	//PreparedStatement
	public void dbClose(PreparedStatement pstmt, Connection conn) {
		// TODO Auto-generated constructor stub
		try {
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
