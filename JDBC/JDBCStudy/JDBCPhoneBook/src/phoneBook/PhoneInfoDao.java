package phoneBook;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PhoneInfoDao {

	
	//전체 조회
	public List<PhoneInfoDto> InfoList(){
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;

		List<PhoneInfoDto> PhoneData=new ArrayList<>(); 
		
		try {
			//DB 연결
			conn=ConnectionProvider.getConnection();
			
			//sql 처리
			String sql="select * from phoneInfo_basic";
			pstmt=conn.prepareStatement(sql);
			
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				PhoneInfoDto pdto=new PhoneInfoDto(
						rs.getInt("idx"),
						rs.getString("fr_name"),
						rs.getString("fr_phonenumber"),
						rs.getString("fr_address"),
						rs.getString("fr_email"),
						rs.getString("fr_regdate")
						);
				PhoneData.add(pdto);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//데이터 베이스 연결 종료
			
			if(rs !=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}if(pstmt !=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn !=null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
		}
		
		return PhoneData;
	}
	
	//기본정보 검색
	public List<PhoneInfoDto> searchName(String name){
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;

		List<PhoneInfoDto> phoneData=new ArrayList<>(); 
		
		try {
			//DB 연결
			conn=ConnectionProvider.getConnection();
			
			//sql 
			String sql="select * from phoneInfo_basic where fr_name=?";
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setString(1,name);
			
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				PhoneInfoDto pdto=new PhoneInfoDto(
						rs.getInt("idx"),
						rs.getString("fr_name"),
						rs.getString("fr_phonenumber"),
						rs.getString("fr_address"),
						rs.getString("fr_email"),
						rs.getString("fr_regdate")
						);
				phoneData.add(pdto);
			}else {
				System.out.println("저장된 이름이 없습니다. 다시 입력하세요.");
			}
			
			
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//데이터 베이스 연결 종료
			
			if(rs !=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}if(pstmt !=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn !=null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
		}
		
		
		return phoneData;
	}
	
	
	
}
