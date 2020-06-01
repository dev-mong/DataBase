package manager.ver2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class EmpManager {

	
	Scanner sc=new Scanner(System.in);
	String url="jdbc:oracle:thin:@localhost:1522:orcl";
	String user="scott";
	String password="tiger";
	
	public void menu() {
	
		System.out.println("<< 사원 관리 프로그램 >>");
		System.out.println("1. 입력  2. 수정  3.삭제  4. 리스트  5.검색  0.홈으로 이동");
		
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
				break;
		}
	}
	
	public void insert() {
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		
		try {
			
			//2. DB 연결
			conn=DriverManager.getConnection(url, user, password);

			//3.SQL 처리	
			
			String sql="insert into emp values(?,?,?,?,?,?,?,?) ";
			pstmt=conn.prepareStatement(sql);
			
			System.out.println("<< 회원 정보 입력 >>");
			System.out.println("--------------------------------");
			System.out.println("사원번호를 입력하세요.");
			int empno=sc.nextInt();
			sc.nextLine();
			
			System.out.println("이름을 입력하세요.");
			String ename=sc.nextLine();
			
			System.out.println("업무를 입력하세요.");
			String job=sc.nextLine();
			
			System.out.println("입사연도를 입력하세요.");
			System.out.println("연도");
			String year=sc.nextLine();
			System.out.println("월");
			String month=sc.nextLine();
			System.out.println("일");
			String day=sc.nextLine();
			String date=year+"-"+month+"-"+day;
			
			
			System.out.println("관리자를 입력하세요.");
			int mgr=sc.nextInt();
			sc.nextLine();
			
			System.out.println("급여을 입력하세요.");
			int sal=sc.nextInt();
			sc.nextLine();
			
			System.out.println("커미션을 입력하세요.");
			int comm=sc.nextInt();
			sc.nextLine();
			
			System.out.println("부서번호를 입력하세요.");
			int deptno=sc.nextInt();
			sc.nextLine();
			System.out.println("--------------------------------");
			
			pstmt.setInt(1, empno);
			pstmt.setString(2, ename);
			pstmt.setString(3, job);
			pstmt.setInt(4, mgr);
			pstmt.setString(5, date);
			pstmt.setInt(6, sal);
			pstmt.setInt(7, comm);
			pstmt.setInt(8,deptno);
			
			int rsCnt=pstmt.executeUpdate();
			if(rsCnt>0) {
			System.out.println(rsCnt+"개 입력되었습니다.");
			}else {
				System.out.println("입력되지 않았습니다. 다시 입력 하세요.");
			}

			
		} catch (SQLException e) {
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
			
			System.out.println("<< 사원 정보 수정 >>");
			System.out.println("--------------------------------");
			
			System.out.println("사원 번호를 입력하세요.");
			int empno=sc.nextInt();
			sc.nextLine();
			
			System.out.println("수정할 이름을 입력하세요.");
			String ename=sc.nextLine();
			
			System.out.println("수정할 부서를 입력하세요.");
			String job=sc.nextLine();
			
			System.out.println("수정할 급여를 입력하세요.");
			int sal=sc.nextInt();
			sc.nextLine();
			
			
			String sql="update emp set ename=?, job=?, sal=?  where empno=? ";
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setString(1,ename);
			pstmt.setString(2,job);
			pstmt.setInt(3,sal);
			pstmt.setInt(4,empno);
			
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
			
			System.out.println("<< 사원 정보 삭제 >>");
			System.out.println("--------------------------------");
			
			System.out.println("사원 번호를 입력하세요.");
			int empno=sc.nextInt();
			sc.nextLine();

			
			String sql= "delete from emp where empno=?";
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setInt(1,empno);
			
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
			
			String sql="select * from emp ";
			pstmt=conn.prepareStatement(sql);
			
			rs=pstmt.executeQuery();
			
			System.out.println("<< 사원 정보 조회 >>");
			System.out.println("----------------------------------------------------------------");
			System.out.print("empno"+"\t");
			System.out.print("ename"+"\t");
			System.out.print("job"+"\t");
			System.out.print("hiredate"+"\t");
			System.out.print("sal"+"\t");
			System.out.print("comm"+"\t");
			System.out.println("deptno");
			
			while(rs.next()) {
				System.out.print(rs.getInt("empno")+"\t");
				System.out.print(rs.getString("ename")+"\t");
				System.out.print(rs.getString("job")+"\t");
				System.out.print(rs.getDate("hiredate")+"\t");
				System.out.print(rs.getInt("sal")+"\t");
				System.out.print(rs.getInt("comm")+"\t");
				System.out.println(rs.getInt("deptno"));
			}
			
			System.out.println("----------------------------------------------------------------");			
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
		
	}//list
	
	public void select() {
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
			//2. DB 연결
			conn=DriverManager.getConnection(url, user, password);

			//3.SQL 처리
			
			String sql="select * from emp where ename=?";
			pstmt=conn.prepareStatement(sql);
			
			System.out.println("이름을 입력하세요.");
			String name=sc.nextLine();
			
	
			pstmt.setString(1, name);
			
			rs=pstmt.executeQuery();
			
			System.out.println("<< 사원 정보 검색 >>");
			System.out.println("----------------------------------------------------------------");
			System.out.print("empno"+"\t");
			System.out.print("ename"+"\t");
			System.out.print("job"+"\t");
			System.out.print("hiredate"+"\t");
			System.out.print("sal"+"\t");
			System.out.print("comm"+"\t");
			System.out.println("deptno");
			
			if(rs.next()) {
				System.out.print(rs.getInt("empno")+"\t");
				System.out.print(rs.getString("ename")+"\t");
				System.out.print(rs.getString("job")+"\t");
				System.out.print(rs.getDate("hiredate")+"\t");
				System.out.print(rs.getInt("sal")+"\t");
				System.out.print(rs.getInt("comm")+"\t");
				System.out.println(rs.getInt("deptno"));
			}else {
				System.out.println("검색하신 정보가없습니다.");
			}
			
			System.out.println("----------------------------------------------------------------");
			
			rs.close();
			pstmt.close();
			conn.close();
			
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
		
		
	}//select 



}
