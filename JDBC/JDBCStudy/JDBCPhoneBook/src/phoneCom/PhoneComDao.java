package phoneCom;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import phoneBookMain.ConnectionProvider;


public class PhoneComDao {

	
	//회사 친구 전체 조회
	public List<PhoneComDto> comList(Connection conn){
		
		PreparedStatement pstmt=null;
		ResultSet rs=null;

		List<PhoneComDto> comData=new ArrayList<>(); 
		
		try {
			
			//sql 처리
			String sql="select * from phoneInfo_com_all";
			pstmt=conn.prepareStatement(sql);

			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				PhoneComDto cdot=new PhoneComDto(
						rs.getInt("idx"),
						rs.getString("fr_name"),
						rs.getString("fr_phonenumber"),
						rs.getString("fr_email"),
						rs.getString("fr_address"),
						rs.getString("fr_regdate"),
						rs.getString("fr_c_company")
						);
				comData.add(cdot);

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
		
		return comData;
	}
	
	//기본정보 검색
	public List<PhoneComDto> select(Connection conn,String cName){
		

		PreparedStatement pstmt=null;
		ResultSet rs=null;

		List<PhoneComDto> phoneData=new ArrayList<>(); 
		
		try {
			String sql="select * from phoneInfo_com_all where fr_name=?";
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setString(1,cName);
			
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				PhoneComDto pdto=new PhoneComDto(
						rs.getInt("idx"),
						rs.getString("fr_name"),
						rs.getString("fr_phonenumber"),
						rs.getString("fr_email"),
						rs.getString("fr_address"),
						rs.getString("fr_regdate"),
						rs.getString("fr_c_company")
						);
				phoneData.add(pdto);
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
		
		return phoneData;
	}
	
	//기본 정보 추가
	public int insert(Connection conn, String company) { //회사 정보 추가 
		
		PreparedStatement pstmt=null;
		int result=0;
		
		try {
			
			//sql
			String sql="insert into phoneInfo_com values(pb_com_idx_seq.nextval, ?,pb_basic_idx_seq.currval)";
			pstmt=conn.prepareStatement(sql);
	
			pstmt.setString(1, company);
			
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

	
	public int update(Connection conn,int idx, String ncompany) {

		int result=0;
		PreparedStatement pstmt=null;
		
		try {
			
			String sql="update phoneInfo_com set fr_c_company=? where cfr_ref=?";	
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setString(1,ncompany);
			pstmt.setInt(2,idx);
			
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

	//삭제
	public int delete(Connection conn,String searchName) {
		

		PreparedStatement pstmt=null;
		int result=0;
		
		try {

			String sql="delete from phoneInfo_basic where fr_name=?";
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setString(1, searchName);
			
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
