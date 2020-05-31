package ex;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class JDBCTest2 {

	public static void main(String[] args)  {

	Connection conn=null;	
	ResultSet rs=null;
	
	try {
		
	//1. DB 드라이버 로드
		Class.forName("oracle.jdbc.driver.OracleDriver");
		System.out.println("접속 성공");
		
		String url="jdbc:oracle:thin:@localhost:1522:orcl";
		String user="scott";
		String pw="tiger";
		
		
	//2. 데이터베이스 접속
		conn=DriverManager.getConnection(url,user,pw);
		System.out.println("데이터베이스에 접속했습니다.");
		
		//트랜잭션 설정
//		conn.setAutoCommit(false);
		
		//commit : 처리 완료 
//		conn.commit();
		
		
		
	//3. SQL로 데이터 처리: Connection
		Statement stmt=conn.createStatement();
		
		
		System.out.println("회원의 이름을 입력해주세요");
		Scanner kb=new Scanner(System.in);
		String searchName = kb.nextLine();
		
		String sql="SELECT * FROM DEPT ORDER BY DEPTNO";
	
		String empSql="SELECT EMPNO, ENAME, JOB FROM EMP";
		
		String bookSql="SELECT O.ORDERID, C.NAME, B.BOOKNAME FROM CUSTOMER C, ORDERS O, BOOK B WHERE O.CUSTID=C.CUSTID AND O.BOOKID=B.BOOKID"
				+ " AND C.NAME='"+searchName+"'"; //띄어쓰기 주의해랑 ~ 
		
//		System.out.println("SQL: "+bookSql);
//		System.out.println();
//		System.out.println();
//		System.out.println();
		
		
		//SELECT의 결과는 ResultSet이 받음
		//executeQuery(sql문)-> ResultSet
		rs=stmt.executeQuery(sql);
		rs=stmt.executeQuery(empSql);
		rs=stmt.executeQuery(bookSql);
		
		//ResultSet : next() -> 행의 존재 유무 확인
		

		System.out.println();
		
//		System.out.print ("사원번호"+"  ");
//		System.out.print ("사원이름"+"  ");
//		System.out.println("사원직급");
		
		System.out.println("판매리스트");
		System.out.println("---------------------------------------------");
		System.out.println("판매아이디 \t 회원이름 \t 책이름");
		System.out.println("---------------------------------------------");
		while(rs.next()) { //데이터 행 단위로 데이터 읽기
			
			//컬럼의 이름으로 데이터 출력
//			System.out.print(rs.getInt("deptno")+"\t");
//			System.out.print(rs.getString("dname")+"\t");
//			System.out.print(rs.getString("loc") + "\n");
			
			//컬럼의 위치 값으로 데이터 출력 
//			System.out.print(rs.getInt(1)+"\t");
//			System.out.print(rs.getString(2)+"\t");
//			System.out.print(rs.getString(3) + "\n");
			
			
//			System.out.print(rs.getString("empno") +"\t"); //사원 번호
//			System.out.print(rs.getString("ename") +"\t"); //사원 이름
//			System.out.println(rs.getString("job"));   //사원 직급
			
			System.out.print(rs.getInt(1)+"\t    ");
			System.out.print(rs.getString(2)+"\t    ");
			System.out.print(rs.getString(3) + "\n");
		}
		
		rs.close();
		stmt.close();
		
		
		
		
		
	//4. close
		conn.close();
	}catch (ClassNotFoundException e) {
		e.printStackTrace();
	}catch (SQLException se) {
		se.printStackTrace();
	}		
	}
	

}
