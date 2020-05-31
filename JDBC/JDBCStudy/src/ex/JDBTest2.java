package ex;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBTest2 {

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
//		String sql="insert into empex values(1234,'test1','MANAGER',null,sysdate, 1500,100,40)";
		
//		String sql2="select * from empex";

//		int rs=stmt.executeUpdate(sql);
//		System.out.println(rs+"행이 삽입되었습니다.");

//		ResultSet rs2=stmt.executeQuery(sql2);

//		String sql3="update empex set sal=1000 where ename='SCOTT'";
//		int rs=stmt3.executeUpdate(sql3);
//		System.out.println(rs+"행이 변경되었습니다.");

		String sql ="select * from empex e natural join dept d";
		ResultSet rs=stmt.executeQuery(sql);
		
		while(rs.next()) {
			System.out.print(rs.getInt(1)+" ");
			System.out.print(rs.getInt(2)+" ");
			System.out.print(rs.getString(3)+" ");
			System.out.print(rs.getString(4)+" ");
			System.out.print(rs.getInt(5)+" ");
			System.out.print(rs.getDate(6)+" ");
			System.out.print(rs.getInt(7)+" ");
			System.out.print(rs.getInt(8)+"    ");
			System.out.print(rs.getString(9)+" ");
			System.out.println(rs.getString(10)+" ");
		}
		
		
		rs.close();
		stmt.close();
//		stmt2.close();
		conn.close();		

		}
	
		catch (SQLException e) {
			e.printStackTrace();
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

}
