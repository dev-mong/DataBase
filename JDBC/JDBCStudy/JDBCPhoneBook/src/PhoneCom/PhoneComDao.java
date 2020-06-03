package PhoneCom;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import phoneBook.ConnectionProvider;


public class PhoneComDao {

	
	//회사 친구 전체 조회
	public List<PhoneComDto> comList(Connection conn){
		
		PreparedStatement pstmt=null;
		ResultSet rs=null;

		List<PhoneComDto> phoneData=new ArrayList<>(); 
		
		try {
			
			//sql 처리
			String sql="select * from phoneInfo_com, phoneInfo_basic b where c.cfr_ref=b.idx order by idx";
			pstmt=conn.prepareStatement(sql);
			
			rs=pstmt.executeQuery();		
			
			while(rs.next()) {
				PhoneComDto cdot=new PhoneComDto(
						rs.getInt("idx"),
						rs.getString("fr_name"),
						rs.getString("fr_phonenumber"),
						rs.getString("fr_email"),
						rs.getString("fr_address"),
						rs.getString("fr_refdate"),
						rs.getString("fr_c_company")
						);
				phoneData.add(cdot);

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
		
		return phoneData;
	}
	
	//기본정보 검색
	public List<PhoneComDto> searchName(String fr_name, Connection conn){
		

		PreparedStatement pstmt=null;
		ResultSet rs=null;

		List<PhoneComDto> phoneData=new ArrayList<>(); 
		
		try {
			String sql="select * from phoneInfo_com where fr_name=?";
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setString(1,fr_name);
			
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				PhoneComDto pdto=new PhoneComDto(
						rs.getInt("idx"),
						rs.getString("fr_name"),
						rs.getString("fr_phonenumber"),
						rs.getString("fr_email"),
						rs.getString("fr_address"),
						rs.getString("fr_refdate"),
						rs.getString("fr_c_company")
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
	public int insert(Connection conn, String fr_C_company) { //회사 정보 추가 
		
		PreparedStatement pstmt=null;
		int result=0;
		
		try {
			
			//sql
			String sql="insert into phoneInfo_com values(pb_com_idx_seq.nextval, ?,pb_basic_idx_seq.currval)";
			pstmt=conn.prepareStatement(sql);
	
			pstmt.setString(1, fr_C_company);
			
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

	

	
	
}
