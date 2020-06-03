package PhoneInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PhoneInfoDao {

	
	//전체 조회
	public List<PhoneInfoAllDto> InfoList(Connection conn){
		
		PreparedStatement pstmt=null;
		ResultSet rs=null;

		List<PhoneInfoAllDto> PhoneData=new ArrayList<>(); 
		
		try {
			
			//sql 처리
			String sql="select * from phoneInfo_all_view order by idx";
			pstmt=conn.prepareStatement(sql);
			
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				PhoneInfoAllDto pdto=new PhoneInfoAllDto(
						rs.getInt("idx"),
						rs.getString("fr_name"),
						rs.getString("fr_phonenumber"),
						rs.getString("fr_address"),
						rs.getString("fr_email"),
						rs.getString("fr_regdate"),
						rs.getString("fr_c_company"),
						rs.getString("fr_u_major"),
						rs.getInt("fr_u_year")
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
	public int insert(Connection conn,String fr_name, String fr_phoneNumber, String fr_email,String fr_address) {
		
		PreparedStatement pstmt=null;
		int result=0;
		
		try {
			
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
		String nname, String nphoneNumber, String nemail,String naddress) {
		
		int result=0;
		PreparedStatement pstmt=null;
		
		String sql="update phoneInfo_basic set fr_name=?, fr_phonenumber=?, fr_email=?, fr_address=? where fr_name=?";
		
		try {
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setString(1, nname);
			pstmt.setString(2, nphoneNumber);
			pstmt.setString(3, nemail);
			pstmt.setString(4, naddress);
			pstmt.setString(5, searchName);
			
			result=pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return result;
	}
	

	
}
