package ex;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBTest1 {

	public static void main(String[] args) {
		
		try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		String url="jdbc:oracle:thin:@localhost:1521:orcl";
		String user="scott";
		String pw="tiger";
		
		//db 접속
		Connection conn=DriverManager.getConnection(url, user, pw);
		System.out.println("접속 성공");
		
		//sql 
		Statement stmt=conn.createStatement();
		String sql="select * from emp01";
//		String sqlIn="insert into emp01 values('1234','박지성')";
		
		//결과 값 처리
		
//		int rs2=stmt.executeUpdate(sqlIn);
//		System.out.println(rs2+"개 행이 입력되었습니다.");
		
		
		ResultSet rs=stmt.executeQuery(sql);
		
		//결과 출력
		while(rs.next()) {
		System.out.print(rs.getInt(1)+" ");
		System.out.print(rs.getString(2)+" ");
		}
		
		rs.close();
		stmt.close();
		conn.close();		
		}
	
		catch (SQLException e) {
			e.printStackTrace();
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

}
