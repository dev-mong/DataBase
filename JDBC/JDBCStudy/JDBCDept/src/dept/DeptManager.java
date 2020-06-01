package dept;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class DeptManager {

	
	Scanner sc=new Scanner(System.in);
	
	String url="jdbc:oracle:thin:@localhost:1522:orcl";
	String user="scott";
	String password="tiger";
	
	public  void menu() {
	
		System.out.println("<< 부서 관리 프로그램 >>");
		System.out.println("1. 입력  2. 수정  3.삭제  4. 리스트  5.검색 0.홈으로 이동");
		
		int menu=sc.nextInt();
		sc.nextLine();
		

		switch(menu) {
		case 1:
			insert();
			break;
		case 2: 
			update(); 
			break;
		case 3: 
			delete(); 
			break;
		case 4: 
			list();
			break;
		case 5: 
			select(); 
			break;
		case 0:
			System.out.println("홈으로 이동");
			return;
		}
	
	}
	public void insert() {
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		
		try {
			//2. DB 연결
			conn=DriverManager.getConnection(url, user, password);

			//3.SQL 처리
			System.out.println("<< 부서 정보 입력>>");
			System.out.println("부서번호를 입력하세요.");
			int deptno=sc.nextInt();
			sc.nextLine();
			System.out.println("부서 이름을 입력하세요.");
			String dname=sc.nextLine();
			System.out.println("부서 위치를 입력하세요.");
			String loc=sc.nextLine();
			
			String sql="insert into dept values(?,?,?)";
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setInt(1, deptno);
			pstmt.setString(2, dname);
			pstmt.setString(3, loc);
			
			int rsCnt=pstmt.executeUpdate();
			if(rsCnt>0) {
			System.out.println(rsCnt+"행이 입력되었습니다.");
			}else {
				System.out.println("입력되지 않았습니다. 다시 입력 하세요.");
			}
			
			
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
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
		
	} // insert
	
	public void update() {
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		
		try {
			//2. DB 연결
			conn=DriverManager.getConnection(url, user, password);

			//3.SQL 처리
			System.out.println("<<부서 정보 수정>>");
			System.out.println("부서번호를 입력하세요.");
			int deptno=sc.nextInt();
			sc.nextLine();
			System.out.println("부서 이름을 입력하세요.");
			String dname=sc.nextLine();
			System.out.println("부서 위치를 입력하세요.");
			String loc=sc.nextLine();
			
			String sql="update dept set dname=?, loc=? where deptno=?";
			pstmt=conn.prepareStatement(sql);
			
			
			pstmt.setString(1, dname);
			pstmt.setString(2, loc);
			pstmt.setInt(3, deptno);
			
			int rsCnt=pstmt.executeUpdate();
			if(rsCnt>0) {
				System.out.println(rsCnt+"개 수정되었습니다.");
				}else {
					System.out.println("수정되지 않았습니다. 다시입력하세요.");
				}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
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
		
		
	}//update
	
	public void delete(){
		Connection conn=null;
		PreparedStatement pstmt=null;
		
		try {
			
			//2. DB 연결
			conn=DriverManager.getConnection(url, user, password);

			//3.SQL 처리
			System.out.println("<<부서 정보 삭제>>");
			System.out.println("부서번호를 입력하세요.");
			int deptno=sc.nextInt();
			sc.nextLine();
		
			String sql="delete from dept where deptno=?";
			pstmt=conn.prepareStatement(sql);
			
			
			pstmt.setInt(1, deptno);
			
			int rsCnt=pstmt.executeUpdate();
			if(rsCnt>0) {
				System.out.println(rsCnt+"개 행이 삭제되었습니다.");
			}else {
				System.out.println("삭제되지 않았습니다. 다시 확인하세요.");
			}
		
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
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
		
			
		
	}//delete
	
	public void list() {
		
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
	
	public void select() {
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {

			//2. DB 연결
			conn=DriverManager.getConnection(url, user, password);

			//3.SQL 처리
			System.out.println("<< 부서 정보 검색 >>");
			System.out.println("부서 이름 또는 지역을 검색하세요.");
			String search=sc.nextLine();
			
			
			String sql="select * from dept where dname=? or loc=?";
			pstmt=conn.prepareStatement(sql);

			pstmt.setString(1, search);
			pstmt.setString(2, search);
			
			rs=pstmt.executeQuery();
			
			System.out.println("---------------------------------");
			System.out.print("deptno"+"\t");
			System.out.print("dname"+"\t");
			System.out.print("loc"+"\n");
			
			
			if(rs.next()) {
				System.out.print(rs.getInt("deptno")+"\t");
				System.out.print(rs.getString("dname")+"\t");
				System.out.println(rs.getString("loc"));
			}else{
				System.out.println("● 검색된 정보가 없습니다.●");
			}
			
			System.out.println("---------------------------------");
			
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
		
		
		
	}//select 



}
