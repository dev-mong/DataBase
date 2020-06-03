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
			String sql="select * from phoneInfo_basic order by idx";
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
	public List<PhoneInfoDto> searchName(String fr_name, Connection conn){
		

		PreparedStatement pstmt=null;
		ResultSet rs=null;

		List<PhoneInfoDto> phoneData=new ArrayList<>(); 
		
		try {
			String sql="select * from phoneInfo_basic where fr_name=?";
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setString(1,fr_name);
			
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
		}
		
		return phoneData;
	}
	
	//기본 정보 추가
	public int insert(String fr_name, String fr_phoneNumber, String fr_email,String fr_address) {
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		int result=0;
		
		try {
			
			conn=ConnectionProvider.getConnection();
			
			//sql
			String sql="insert into phoneInfo_basic(idx,fr_name,fr_phonenumber,fr_email,fr_address) "
					+ "values(pb_basic_idx_seq.nextval,?,?,?,?) ";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, fr_name);
			pstmt.setString(2, fr_phoneNumber);
			pstmt.setString(3, fr_email);
			pstmt.setString(4, fr_address);
			
			result=pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		
		return result;
		
	}

	
	//기본 정보 수정
	public int update(Connection conn, String searchName,
			String nfr_name, String nfr_phoneNumber, String nfr_email,String nfr_address) {
		
		int result=0;
		PreparedStatement pstmt=null;
		
		String sql="update phoneInfo_basic set fr_name=?, fr_phonenumber=?, fr_email=?, fr_address=? where fr_name=?";
		
		try {
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setString(1, nfr_name);
			pstmt.setString(2, nfr_phoneNumber);
			pstmt.setString(3, nfr_email);
			pstmt.setString(4, nfr_address);
			pstmt.setString(5, searchName);
			
			result=pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return result;
	}
	
	//기본 정보 삭제
	public int delete(String fr_name, Connection conn) {
		

		PreparedStatement pstmt=null;
		int result=0;
		
		try {

			String sql="delete from phoneInfo_basic where fr_name=?";
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setString(1, fr_name);
			
			result=pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	

	
	
}
