package ex;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCTest4 {

	public static void main(String[] args) {
	
		//JDBC 사용 객체 
		Connection conn=null;
		Statement stmt=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		
		//0. 드라이버 LIB 추가
		try {
			
			//1. 데이터베이스 드라이버 로드
			//Class.forName(드라이버 클래스 전체 이름)
			//Oracle : oracle.jdbc.driver.OracleDriver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
		
			//2. 데이터베이스 연결
			//url : jdbc:oracle:thin:@주소:포트번호:데이터베이스이름
			//		localhost or 127.0.0.1
			String url= "jdbc:oracle:thin:@localhost:1522:orcl";
			String user="scott";
			String pw="tiger";
			
			//Connection 객체 생성
			conn=DriverManager.getConnection(url, user, pw);
			
			//3. SQL 처리
			//Statement or PreparedStatement
			//pstmt=conn.prepareStatement(sql문장)
			String sql="Select * from dept where deptno=?";
			pstmt=conn.prepareStatement(sql);
	
			
			//변수 데이터 설정
			pstmt.setInt(1, 10);
			rs=pstmt.executeQuery();
			
			System.out.println("<< 부서 목록 >>");
			System.out.println("-------------------------------");
			//ResultSet -> 결과 참조
			while(rs.next()) {
				System.out.print(rs.getInt("deptno")+"\t");
				System.out.print(rs.getString("dname")+"\t");
				System.out.print(rs.getString("loc")+"\n");
			}
			System.out.println("-------------------------------");
			
			
//			//4. 데이터베이스 연결 종료 
//			rs.close();
//			pstmt.close();
//			conn.close();
			
		} catch (ClassNotFoundException e) { //드라이버 로드
			e.printStackTrace();
		}catch (SQLException e) { //connection
			e.printStackTrace();
//			if(rs != null) {
//			//4. 데이터베이스 연결 종료 
//			try {
//				rs.close();
//			} catch (SQLException e1) {
//				e1.printStackTrace();
//			}
//			}
//			if(pstmt != null) {
//			try {
//				pstmt.close();
//			} catch (SQLException e1) {
//				e1.printStackTrace();
//			}
//			}
//			if(conn != null) {
//			try {
//				conn.close();
//			} catch (SQLException e1) {
//				e1.printStackTrace();
//			}
//			}
		}finally {
			if(rs != null) {
			//4. 데이터베이스 연결 종료 
			try {
				rs.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			}
			if(pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			}
			if(conn != null) {
			try {
				conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			}
		}
			
		
	}

}
