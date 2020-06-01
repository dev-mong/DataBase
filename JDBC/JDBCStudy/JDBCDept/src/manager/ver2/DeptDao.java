package manager.ver2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DeptDao {

	
	//VO : value object 
	//DTO: Data Transfer Object ->데이터 전송 목적 
	//DAO= Data Acess Object
	// 데이터 베이스 처리하는 클래스
	//입력 수정 삭제 처리만 해줌 
	
	//mvc -> model,view, controller
	//model ->service,dao
	//데이터 베이스
	
	
		public void List<> list() {
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		
		try {
			//2. DB 연결
			conn=DriverManager.getConnection(url, user, password);

			//3.SQL 처리
			System.out.println("<< 부서 정보 조회 >>");
			System.out.print("deptno"+"\t");
			System.out.print("dname"+"\t");
			System.out.print("loc"+"\n");
			
			
			String sql="select * from dept";
			pstmt=conn.prepareStatement(sql);

			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				System.out.print(rs.getInt("deptno")+"\t");
				System.out.print(rs.getString("dname")+"\t");
				System.out.println(rs.getString("loc"));
			}
			
		} catch (SQLException e) {
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
		
		
	}//list
	

	
	
}
