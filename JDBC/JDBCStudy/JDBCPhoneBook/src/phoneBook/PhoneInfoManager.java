package phoneBook;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

//데이터 입출력

public class PhoneInfoManager {
	
	PhoneInfoDao dao=new PhoneInfoDao();
	
	//메뉴
	public  void pmMenu() {
		
		while(true) {
		System.out.println("\n   \t\tPhoneBook MENU  ");	
		System.out.println("----------------------------------------------------");
		System.out.println("\t1.전체 조회   2.검색   3.전화번호 저장    4.전화번호 삭제   0.종료");
		System.out.println("----------------------------------------------------");
		int menu=Main.sc.nextInt();
		Main.sc.nextLine();
		
		switch(menu) {
		case 1:
		System.out.println("<< 기본 정보 전체 조회 >>");
		pList();
		break;
		case 2:
		System.out.println("<< 전화번호 검색 >>");
		pSearch();
		break;
		case 3:
		System.out.println("<< 전화번호 저장 >>");
		pInsert();
		break;
		case 4:
		System.out.println("<< 전화번호 삭제 >>");
		pDelete();
		break;
		case 0:
			System.out.println("프로그램 종료");
			System.exit(0);
			break;
		
		}
		}
		
	}
	
	//기본 정보 전체 조회
	public void pList() {
		
		List<PhoneInfoDto> phoneData = dao.InfoList();
	
		System.out.print("IDX    ");
		System.out.print("이름      ");
		System.out.print("전화번호   ");
		System.out.print("이메일  ");
		System.out.print("주소   ");
		System.out.println("등록일자  ");
		System.out.println("======================================================================");
		if(phoneData !=null && !phoneData.isEmpty()) { //데이터 존재 여부
			for(int i=0;i<phoneData.size();i++) {
				System.out.print(phoneData.get(i).getIdx()+"   ");
				System.out.print(phoneData.get(i).getName()+"   ");
				System.out.print(phoneData.get(i).getPhoneNumber()+"   ");
				System.out.print(phoneData.get(i).getEmail()+"   ");
				System.out.print(phoneData.get(i).getAddress()+"    ");
				System.out.println(phoneData.get(i).getRegdate().substring(0,10));
			}
		}
		
		System.out.println("======================================================================");
	
	}
	
	//기본 정보 검색 
	public void pSearch() {
		
		Connection conn=null;
		
		try {
			conn=ConnectionProvider.getConnection();
			
			System.out.println("이름을 입력하세요");
			
			String name=Main.sc.nextLine();
			
			List<PhoneInfoDto> phoneData = dao.searchName(name,conn);
			
			if(phoneData !=null && !phoneData.isEmpty()) { //데이터 존재 여부
				System.out.print("IDX    ");
				System.out.print("이름      ");
				System.out.print("전화번호   ");
				System.out.print("주소   ");
				System.out.print("이메일  ");
				System.out.println("등록일자  ");
				System.out.println("======================================================================");
				
				for(int i=0;i<phoneData.size();i++) {
					System.out.print(phoneData.get(i).getIdx()+"   ");
					System.out.print(phoneData.get(i).getName()+"   ");
					System.out.print(phoneData.get(i).getPhoneNumber()+"   ");
					System.out.print(phoneData.get(i).getAddress()+"    ");
					System.out.print(phoneData.get(i).getEmail()+"   ");
					System.out.println(phoneData.get(i).getRegdate().substring(0,10));
				}

				System.out.println("======================================================================");
			
			} else {
				System.out.println("존재하는 정보가 없습니다. 다시입력하세요.");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(conn !=null)
			{
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}
	
	//기본 정보 저장
	public void pInsert() {
		
		System.out.println("이름을 입력하세요");
		String fr_name=Main.sc.nextLine();
		System.out.println("전화번호를 입력하세요");
		String fr_phoneNumber=Main.sc.nextLine();
		System.out.println("주소를 입력하세요");
		String fr_address=Main.sc.nextLine();
		System.out.println("이메일을 입력하세요");
		String fr_email=Main.sc.nextLine();
		

		int result = dao.insert(fr_name, fr_phoneNumber,fr_email,fr_address);
		
		System.out.println("======================================================================");
		if(result>0) {
			System.out.println(result+"행이 추가 되었습니다.");
		}else {
			System.out.println("잘못된 입력입니다. 다시 입력하세요.");
		}
		System.out.println("======================================================================");
		
	}
	
	
	//기본 정보 삭제 -- 검색 결과, 동일 이름 삭제 여부 -> 수정 
	public void pDelete() {

		Connection conn=null;	
		int result=0;
		
		try {
			while(true) {
			conn=ConnectionProvider.getConnection();
			conn.setAutoCommit(false); //자동 커밋 false
			
			System.out.println("이름을 검색하세요.");
			String fr_name=Main.sc.nextLine();		
			
			List<PhoneInfoDto> phoneData=dao.searchName(fr_name, conn);
			
				if(phoneData !=null && !phoneData.isEmpty()) { //데이터 존재 여부
					System.out.print("IDX    ");
					System.out.print("이름      ");
					System.out.print("전화번호   ");
					System.out.print("주소   ");
					System.out.print("이메일  ");
					System.out.println("등록일자  ");
					System.out.println("======================================================================");
					for(int i=0;i<phoneData.size();i++) {
						System.out.print(phoneData.get(i).getIdx()+"   ");
						System.out.print(phoneData.get(i).getName()+"   ");
						System.out.print(phoneData.get(i).getPhoneNumber()+"   ");
						System.out.print(phoneData.get(i).getAddress()+"    ");
						System.out.print(phoneData.get(i).getEmail()+"   ");
						System.out.println(phoneData.get(i).getRegdate().substring(0,10));
					}
					System.out.println("======================================================================");

					result=dao.delete(fr_name, conn);
					if(result>0) {
						System.out.println("■  "+result+"행이 삭제되었습니다.  ■");
						conn.commit();
					}
					return;
					
				}else {
					System.out.println("존재하는 정보가 없습니다. 다시입력하세요.");
				}
			}
		} catch (SQLException e) {
			
			if(conn != null) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		} 
		finally {
			if(conn !=null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
