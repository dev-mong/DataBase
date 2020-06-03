package phoneUniv;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import phoneCom.PhoneComDto;

public class PhoneUnivDao {
	
	//대학 친구 정보 조회
	public List<PhoneUnivDto> uList(Connection conn){
		
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		List<PhoneUnivDto> univData =new ArrayList<>();
	
		try {
			
			String sql="select * from phoneInfo_univ_all";		
			pstmt=conn.prepareStatement(sql);
			
			rs=pstmt.executeQuery();
			while(rs.next()) {
				PhoneUnivDto uDao=new PhoneUnivDto(
						rs.getInt("idx"),
						rs.getString("fr_name"),
						rs.getString("fr_phonenumber"),
						rs.getString("fr_email"),
						rs.getString("fr_address"),
						rs.getString("fr_regdate"),
						rs.getString("fr_u_major"),
						rs.getString("fr_u_year")
						);
				univData.add(uDao);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
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

		
		return univData;
	}
	
	//대학 친구 검색 
	public List<PhoneUnivDto> select(Connection conn, String uname){

		PreparedStatement pstmt=null;
		ResultSet rs=null;

		List<PhoneUnivDto> univData=new ArrayList<>(); 
		
		try {
			String sql="select * from phoneInfo_univ_all where fr_name=?";
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setString(1,uname);
			
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				PhoneUnivDto pdto=new PhoneUnivDto(
						rs.getInt("idx"),
						rs.getString("fr_name"),
						rs.getString("fr_phonenumber"),
						rs.getString("fr_email"),
						rs.getString("fr_address"),
						rs.getString("fr_regdate"),
						rs.getString("fr_u_major"),
						rs.getString("fr_u_year")
						);
				univData.add(pdto);
			}
			
			
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
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
		
		return univData;
	}

	//대학 친구 정보 저장
	public int insert(Connection conn,String major,String year) {
		
		PreparedStatement pstmt=null;
		int result=0;
		
		try {
			
			//sql
			String sql="insert into phoneInfo_univ values(pb_com_idx_seq.nextval, ?,?,pb_basic_idx_seq.currval)";
			pstmt=conn.prepareStatement(sql);
	
			pstmt.setString(1, major);
			pstmt.setString(2, year);
			
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


	//대학 친구 정보 수정 
	public int update(Connection conn,int idx, String nMajor, String nYear) {

		int result=0;
		PreparedStatement pstmt=null;
		
		try {
			
			String sql="update phoneInfo_univ set fr_u_major=?, fr_u_year=? where ufr_ref=?";	
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setString(1,nMajor);
			pstmt.setString(2,nYear);
			pstmt.setInt(3,idx);
			
			result=pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}

	//대학 친구 정보 삭제
	public int delete(Connection conn,String searchUniv) {
		
		PreparedStatement pstmt=null;
		int result=0;
		
		try {

			String sql="delete from phoneInfo_basic where fr_name=?";
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setString(1, searchUniv);
			
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
