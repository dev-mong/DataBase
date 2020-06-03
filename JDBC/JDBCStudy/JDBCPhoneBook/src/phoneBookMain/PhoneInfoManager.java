package phoneBookMain;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import PhoneInfo.PhoneInfoAllDto;
import PhoneInfo.PhoneInfoDao;
import phoneCom.PhoneComDao;
import phoneCom.PhoneComDto;
import phoneUniv.PhoneUnivDao;
import phoneUniv.PhoneUnivDto;

//데이터 입출력

public class PhoneInfoManager {

	
	//메뉴
	public void pmMenu() {
		
		while(true) {
		System.out.println("\n   \t\t  PhoneBook MENU  ");	
		
		System.out.println("-----------------------------------------------------");
		System.out.println("\t\t"+MenuInterface.menu1+" 전체 정보 조회 ");
		System.out.println( "\t\t"+MenuInterface.menu2+" 전화 번호 조회 ");
		System.out.println( "\t\t"+MenuInterface.menu3+" 전화번호 검색  ");
		System.out.println( "\t\t"+MenuInterface.menu4+" 전화번호 저장");
		System.out.println( "\t\t"+MenuInterface.menu5+" 전화번호 수정 ");
		System.out.println( "\t\t"+MenuInterface.menu6+" 전화번호 삭제 ");
		System.out.println( "\t\t"+MenuInterface.menu0+" 종료");
		System.out.println("-----------------------------------------------------");
		
		int menu=Main.sc.nextInt();
		Main.sc.nextLine();
		
		switch(menu) {
		case MenuInterface.menu1:
			System.out.println("\t\t<< 전체 정보 조회 >>");
			pAllList();
			break;
		case MenuInterface.menu2:
			System.out.println("\t\t<< 전화 번호 조회 >>");
			pList();
			break;
		case MenuInterface.menu3:
			System.out.println("\t\t<< 전화번호 검색 >>");
			pSearch();
			break;
		case MenuInterface.menu4:
			System.out.println("\t\t<< 전화번호 저장 >>");
			pInsert();
			break;
		case MenuInterface.menu5:
			System.out.println("\t\t<< 전화번호 수정>>");
			pUpdate();
			break;
		case MenuInterface.menu6:
			System.out.println("\t\t<< 전화번호 삭제 >>");
			pDel();
			break;
		case MenuInterface.menu0:
			System.out.println("프로그램 종료");
			System.exit(0);
			break;
		
		}
		}
		
	}
	
	//기본 정보 전체 조회 
	public void pAllList() {
		
		Connection conn=null;
		
		try {
			
			conn=ConnectionProvider.getConnection();
			PhoneInfoDao dao=new PhoneInfoDao();
			List<PhoneInfoAllDto> phoneData = dao.InfoList(conn);
			
			if(phoneData !=null && !phoneData.isEmpty()) { //데이터 존재 여부
				
				MenuInterface.colPrint();
				MenuInterface.comCol();
				MenuInterface.univCol();
				
				System.out.println("\n===================================================================================");
				for(int i=0;i<phoneData.size();i++) {
					System.out.print(phoneData.get(i).getIdx()+"\t");
					System.out.print(phoneData.get(i).getName()+"\t");
					System.out.print(phoneData.get(i).getPhoneNumber()+"\t");
					System.out.print(phoneData.get(i).getEmail()+"\t");
					System.out.print(phoneData.get(i).getAddress()+"\t");
					System.out.print(phoneData.get(i).getRegdate().substring(0,10)+"\t");
					System.out.print(phoneData.get(i).getComName()+"\t");
					System.out.print(phoneData.get(i).getMajor()+"\t");
					System.out.println(phoneData.get(i).getYear()+"\t");
				}

				   System.out.println("===================================================================================");
			
			}else {
				System.out.println("■  저장 된 친구 정보가 없습니다.  ■");
			}
			
		} catch (SQLException e) {
		
			e.printStackTrace();
		} finally {
			if(conn !=null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	
	}
	
	//친구 타입 별  상세 조회
	public void pList() {
		
		Connection conn=null;
		
		try {
			
			conn=ConnectionProvider.getConnection();
			MenuInterface.typePrint();

			int menu=Main.sc.nextInt();
			Main.sc.nextLine();
			
			switch(menu) {
			case MenuInterface.menu1:
				System.out.println("\n---------------  회사 친구 정보 조회  -------------------");
				PhoneComDao comDao=new PhoneComDao();
				List<PhoneComDto> comData=comDao.comList(conn);
				
				if(comData!=null && !comData.isEmpty()) {
					MenuInterface.colPrint();
					MenuInterface.comCol();
					
					System.out.println("\n======================================================================");
					for(int i=0;i<comData.size();i++) {
						System.out.print(comData.get(i).getIdx()+"\t");
						System.out.print(comData.get(i).getName()+"\t");
						System.out.print(comData.get(i).getPhoneNumber()+"\t");
						System.out.print(comData.get(i).getEmail()+"\t");
						System.out.print(comData.get(i).getAddress()+"\t");
						System.out.print(comData.get(i).getRegdate().substring(0,10)+"\t");
						System.out.println(comData.get(i).getCompany());
					}
				System.out.println("======================================================================");
				}else {
					System.out.println("회사 친구 정보가 없습니다.");
				}
				
				break;
				
			case MenuInterface.menu2: 
				System.out.println("\n---------------  회사 친구 정보 조회  -------------------");
				PhoneUnivDao uDao=new PhoneUnivDao();
				List<PhoneUnivDto> univData=uDao.uList(conn);
				
				if(univData !=null && !univData.isEmpty()) {

					MenuInterface.colPrint();
					MenuInterface.univCol();
					
					System.out.println("\n======================================================================");
				
					for(int i=0;i<univData.size();i++) {
						System.out.print(univData.get(i).getIdx()+"\t");
						System.out.print(univData.get(i).getName()+"\t");
						System.out.print(univData.get(i).getPhoneNumber()+"\t");
						System.out.print(univData.get(i).getEmail()+"\t");
						System.out.print(univData.get(i).getAddress()+"\t");
						System.out.print(univData.get(i).getRegdate().substring(0,10)+"\t");
						System.out.print(univData.get(i).getMajor()+"\t");
						System.out.println(univData.get(i).getYear());
						
					}
					System.out.println("======================================================================");
						
				}else {
					System.out.println("대학 친구 정보가 없습니다.");
				}
				break;
				}
				
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(conn !=null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	//기본 정보 검색 
	public void pSearch() {
		
		Connection conn=null;
		
		try {
			conn=ConnectionProvider.getConnection();
			
			MenuInterface.typePrint();
			int menu=Main.sc.nextInt();
			Main.sc.nextLine();
			
			switch(menu) {
			case 1:
				System.out.println("이름을 입력하세요");
				String cName=Main.sc.nextLine();
				
				PhoneComDao cDao=new PhoneComDao();
				List<PhoneComDto> comData=cDao.select(conn,cName);

				if(comData!=null) {

					
					MenuInterface.colPrint();
					MenuInterface.comCol();
					
					System.out.println("\n======================================================================");
					for(int i=0;i<comData.size();i++) {
						System.out.print(comData.get(i).getIdx()+"\t");
						
						System.out.print(comData.get(i).getName()+"\t");
						System.out.print(comData.get(i).getPhoneNumber()+"\t");
						System.out.print(comData.get(i).getEmail()+"\t");
						System.out.print(comData.get(i).getAddress()+"\t");
						System.out.print(comData.get(i).getRegdate().substring(0,10)+"\t");
						System.out.println(comData.get(i).getCompany());
					}
					System.out.println("======================================================================");
				}else {
					System.out.println("회사 친구 정보가 없습니다.");
				}
				
				break;
			case 2:
				System.out.println("이름을 입력하세요");
				String uname=Main.sc.nextLine();
				
				PhoneUnivDao uDao=new PhoneUnivDao();
				List<PhoneUnivDto> univData=uDao.select(conn,uname);
				
			
				if(univData!=null) {

					MenuInterface.colPrint();
					MenuInterface.univCol();
					
					System.out.println("\n======================================================================");
					for(int i=0;i<univData.size();i++) {
						System.out.print(univData.get(i).getIdx()+"\t");
						System.out.print(univData.get(i).getName()+"\t");
						System.out.print(univData.get(i).getPhoneNumber()+"\t");
						System.out.print(univData.get(i).getEmail()+"\t");
						System.out.print(univData.get(i).getAddress()+"\t");
						System.out.print(univData.get(i).getRegdate().substring(0,10)+"\t");
						System.out.print(univData.get(i).getMajor()+"\t");
						System.out.println(univData.get(i).getYear());
					
					}
					System.out.println("======================================================================");
				}else {
					System.out.println("대학 친구 정보가 없습니다.");
				}
				
				
				break;
			}//switch
			
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
		
		Connection conn=null;
		
	
		try {
			conn=ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			PhoneInfoDao dao=new PhoneInfoDao();	
			
			System.out.println("이름을 입력하세요");
			String fr_name=Main.sc.nextLine();
			System.out.println("전화번호를 입력하세요");
			String fr_phoneNumber=Main.sc.nextLine();
			System.out.println("주소를 입력하세요");
			String fr_address=Main.sc.nextLine();
			System.out.println("이메일을 입력하세요");
			String fr_email=Main.sc.nextLine();
			

			int result = dao.insert(conn,fr_name, fr_phoneNumber,fr_email,fr_address);
			
			
			if(result>0) {

				MenuInterface.typePrint();
				int menu=Main.sc.nextInt();
				Main.sc.nextLine();
			
				switch(menu) {
				case MenuInterface.menu1:
					System.out.println("\n---------------  회사 친구 상제 정보 저장  -------------------");
					PhoneComDao com=new PhoneComDao();
					
					System.out.println("회사 이름을 입력하세요.");
					String company=Main.sc.nextLine();
					
					result=com.insert(conn, company);

					if(result >0) {
						System.out.println("■  회사 친구는 "+result+"행이 추가되었습니다.  ■");
						conn.commit();
					}else {
						System.out.println("잘못된 입력입니다.");
					}
					break;
				case MenuInterface.menu2:
					System.out.println("\n-------------  대학 친구 상제 정보 저장  -----------------");
					PhoneUnivDao univ=new PhoneUnivDao();
					System.out.println("전공을 입력하세요.");
					String major=Main.sc.nextLine();
					System.out.println("학년을 입력하세요.");
					String year=Main.sc.nextLine();
					
					result=univ.insert(conn,major,year);
					
					if(result >0) {
						System.out.println("■   대학친구는 "+result+"행이 추가되었습니다.  ■");
						conn.commit();
					}else {
						System.out.println("잘못된 입력입니다.");
					}
					
					
					break;
				}
				
				
			}else {
				System.out.println("잘못된 입력입니다. 다시 입력하세요.");
			}
			System.out.println("======================================================================");
			
			
			
		} catch (SQLException e) {
			if(conn !=null) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			
		} finally {
			if(conn!=null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			}
		}
		
	}
	
	//기본 정보 수정
	public void pUpdate() {
		Connection conn=null;	
		int result=0;
		
		try {
			conn=ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			PhoneInfoDao dao=new PhoneInfoDao();
			
				MenuInterface.typePrint();
				int menu=Main.sc.nextInt();
				Main.sc.nextLine();
				
				switch(menu) {
				case MenuInterface.menu1:
					
					System.out.println("이름을 입력하세요.");
					String searchName=Main.sc.nextLine();
					
					PhoneComDao cdao=new PhoneComDao();
					List<PhoneComDto> comData=cdao.select(conn,searchName);

					if(comData !=null && !comData.isEmpty()) { //데이터 존재 여부
						
						MenuInterface.colPrint();
						MenuInterface.comCol();
						
						System.out.println("\n======================================================================");
						for(int i=0;i<comData.size();i++) {
							System.out.print(comData.get(i).getIdx()+"\t");
							
							System.out.print(comData.get(i).getName()+"\t");
							System.out.print(comData.get(i).getPhoneNumber()+"\t");
							System.out.print(comData.get(i).getEmail()+"\t");
							System.out.print(comData.get(i).getAddress()+"\t");
							System.out.print(comData.get(i).getRegdate().substring(0,10)+"\t");
							System.out.println(comData.get(i).getCompany());
						}
					System.out.println("======================================================================");
	
					
					System.out.println("회사 친구 이름을 입력하세요.");
					String cName=Main.sc.nextLine();
					System.out.println("회사 친구 전화번호를 입력하세요.");
					String cphoneNumber=Main.sc.nextLine();
					System.out.println("회사 친구 이메일을 입력하세요.");
					String cemail=Main.sc.nextLine();
					System.out.println("회사 친구 주소를 입력하세요.");
					String caddress=Main.sc.nextLine();
					
					
					result=dao.update(conn, searchName, cName, cphoneNumber, cemail, caddress);
					
					if(result >0) {
						System.out.println("회사 이름을 입력하세요.");
						String nCompany=Main.sc.nextLine();
						System.out.println(comData.get(0).getIdx());
						result=	cdao.update(conn,comData.get(0).getIdx(),nCompany);
						if(result>0) {
							System.out.println("■  "+result+"행이 수정되었습니다.  ■");
							conn.commit();
						}	
					} else {
						System.out.println("정보가 수정되지 않았습니다.");
					}
					}else {
						System.out.println("존재하는 정보가 없습니다. 다시입력하세요.");
					}
					break;
				case MenuInterface.menu2:
					
					System.out.println("이름을 입력하세요.");
					String searchUniv=Main.sc.nextLine();
					PhoneUnivDao udao=new PhoneUnivDao();
					List<PhoneUnivDto> univData=udao.select(conn,searchUniv);
					
					if(univData !=null && !univData.isEmpty()) { //데이터 존재 여부
						MenuInterface.colPrint();
						MenuInterface.univCol();
						
						System.out.println("\n======================================================================");
						for(int i=0;i<univData.size();i++) {
							System.out.print(univData.get(i).getIdx()+"\t");
							System.out.print(univData.get(i).getName()+"\t");
							System.out.print(univData.get(i).getPhoneNumber()+"\t");
							System.out.print(univData.get(i).getEmail()+"\t");
							System.out.print(univData.get(i).getAddress()+"\t");
							System.out.print(univData.get(i).getRegdate().substring(0,10)+"\t");
							System.out.print(univData.get(i).getMajor()+"\t");
							System.out.println(univData.get(i).getYear());
						
						}
					System.out.println("======================================================================");
					
					System.out.println("대학 친구 이름을 입력하세요.");
					String uName=Main.sc.nextLine();
					System.out.println("대학 친구 전화번호를 입력하세요.");
					String uphoneNumber=Main.sc.nextLine();
					System.out.println("대학 친구 이메일을 입력하세요.");
					String uemail=Main.sc.nextLine();
					System.out.println("대학 친구 주소를 입력하세요.");
					String uaddress=Main.sc.nextLine();
					
					result=dao.update(conn, searchUniv, uName, uphoneNumber, uemail, uaddress);
					if(result >0) {
						System.out.println("전공을 입력하세요.");
						String nmajor=Main.sc.nextLine();
						System.out.println("학년을 입력하세요.");
						String nYear=Main.sc.nextLine();
						
						PhoneUnivDao uDao=new PhoneUnivDao();
						
						result=	uDao.update(conn,univData.get(0).getIdx(),nmajor,nYear);
						if(result>0) {
							System.out.println("■  "+result+"행이 수정되었습니다.  ■");
							conn.commit();
						}	
					} else {
						System.out.println("존재하는 정보가 없습니다. 다시입력하세요.");
					}
					
				}else {
					System.out.println("존재하는 정보가 없습니다. 다시입력하세요.");
				}
					
					break;
				
			}
				conn.commit();
			
		} catch (SQLException e) {
			if(conn!=null) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		} finally {
			if(conn!=null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		
	
	}
	
	//기본 정보 삭제 -- 검색 결과, 동일 이름 삭제 여부 -> 수정 
	public void pDel() {
		
		Connection conn=null;	
		int result=0;
		
		try {
			conn=ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
				MenuInterface.typePrint();
				int menu=Main.sc.nextInt();
				Main.sc.nextLine();
				
				switch(menu) {
				case 1:
					System.out.println("이름을 입력하세요.");
					String searchCom=Main.sc.nextLine();
					
					PhoneComDao cdao=new PhoneComDao();
					List<PhoneComDto> comData=cdao.select(conn,searchCom);

					if(comData !=null && !comData.isEmpty()) { //데이터 존재 여부
						MenuInterface.colPrint();
						MenuInterface.comCol();
						
						System.out.println("\n======================================================================");
						for(int i=0;i<comData.size();i++) {
							System.out.print(comData.get(i).getIdx()+"\t");
							
							System.out.print(comData.get(i).getName()+"\t");
							System.out.print(comData.get(i).getPhoneNumber()+"\t");
							System.out.print(comData.get(i).getEmail()+"\t");
							System.out.print(comData.get(i).getAddress()+"\t");
							System.out.print(comData.get(i).getRegdate().substring(0,10)+"\t");
							System.out.println(comData.get(i).getCompany());
						}
					System.out.println("======================================================================");
					
						
						result=cdao.delete(conn,searchCom);
						if(result>0) {
							System.out.println("■  "+result+"행이 삭제되었습니다.  ■");
							conn.commit();
						}
						
					} else {
						System.out.println("입력하신 정보가 없습니다.");
					}
					
				
					break;
				case 2:
					
					System.out.println("이름을 입력하세요.");
					String searchUniv=Main.sc.nextLine();
					PhoneUnivDao udao=new PhoneUnivDao();
					List<PhoneUnivDto> univData=udao.select(conn,searchUniv);
					
					if(univData !=null && !univData.isEmpty()) { //데이터 존재 여부
						MenuInterface.colPrint();
						MenuInterface.univCol();
						
						System.out.println("\n======================================================================");
						for(int i=0;i<univData.size();i++) {
							System.out.print(univData.get(i).getIdx()+"\t");
							System.out.print(univData.get(i).getName()+"\t");
							System.out.print(univData.get(i).getPhoneNumber()+"\t");
							System.out.print(univData.get(i).getEmail()+"\t");
							System.out.print(univData.get(i).getAddress()+"\t");
							System.out.print(univData.get(i).getRegdate().substring(0,10)+"\t");
							System.out.print(univData.get(i).getMajor()+"\t");
							System.out.println(univData.get(i).getYear());
						
						}
					System.out.println("======================================================================");
						
						
						
						result=udao.delete(conn,searchUniv);
						if(result>0) {
							System.out.println("■  "+result+"행이 삭제되었습니다.  ■");
						}
					} else {
						System.out.println("입력하신 정보가 없습니다.");
					}
	
					
					break;
				
			}
				conn.commit();
			
		} catch (SQLException e) {
			if(conn!=null) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		} finally {
			if(conn!=null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
	}


		
}
	
	
