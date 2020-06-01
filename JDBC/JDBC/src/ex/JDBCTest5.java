package ex;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCTest5 {

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
			String sql="insert into dept (deptno, dname, loc) values(?,?,?)";
			pstmt=conn.prepareStatement(sql);
	
			//변수 데이터 설정 - index 1부터 시작
			pstmt.setInt(1, 70);
			pstmt.setString(2, "마케팅");
			pstmt.setString(3, "서울");
			
			int resultCnt=pstmt.executeUpdate();
			if(resultCnt>0) {
				System.out.println("정상적으로 입력되었습니다.");
				System.out.println(resultCnt+"개 행이 입력되었습니다.");
			}else {
				System.out.println("입력되지않았습니다. 확인 후 재시도해주세요.");
			}
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
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
