package phoneBook;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import phoneCom.PhoneComDao;
import phoneCom.PhoneComDto;
import phoneUniv.PhoneUnivDao;
import phoneUniv.PhoneUnivDto;


public class PhoneInfoManager {

	
	//메뉴
	public void pmMenu() {
		
		while(true) {
		System.out.println("\n   \t\tPhoneBook MENU  ");	
		System.out.println("-----------------------------------------------------------------------------");
		System.out.println("\t1.전체 조회  2. 전화번호 조회  3.검색   4.전화번호 저장   5.전화번호 수정  6.전화번호 삭제    0.종료");
		System.out.println("-----------------------------------------------------------------------------");
		int menu=Main.sc.nextInt();
		Main.sc.nextLine();
		
		switch(menu) {
		case 1:
			System.out.println("\t<< 기본 정보 전체 조회 >>");
			pAllList();
			break;
		case 2:
			System.out.println("\t<< 친구 정보 조회 >>");
			pList();
			break;
		case 3:
			System.out.println("\t<< 전화번호 검색 >>");
			pSearch();
			break;
		case 4:
			System.out.println("\t<< 전화번호 저장 >>");
			pInsert();
			break;
		case 5:
			System.out.println("\t<< 전화번호 수정>>");
			pUpdate();
			break;
		case 6:
			System.out.println("\t<< 전화번호 삭제 >>");
			pDel();
			break;
		case 0:
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
				System.out.print("IDX"+"     ");
				System.out.printf("%5s","이름"+"     ");
				System.out.printf("%5s","전화번호"+"     ");
				System.out.printf("%5s","이메일"+"     ");
				System.out.printf("%5s","주소 "+"     ");
				System.out.printf("%5s","등록일자 "+"     ");
				System.out.printf("%5s","회사 이름"+"     ");
				System.out.printf("전공"+"       ");
				System.out.println("학년");
				System.out.println("======================================================================");
				for(int i=0;i<phoneData.size();i++) {
					System.out.print(phoneData.get(i).getIdx()+"   ");
					System.out.printf("%5s",phoneData.get(i).getName()+"   ");
					System.out.printf("%5s",phoneData.get(i).getPhoneNumber()+"   ");
					System.out.printf("%5s",phoneData.get(i).getEmail()+"   ");
					System.out.printf("%5s",phoneData.get(i).getAddress()+"   ");
					System.out.printf("%5s",phoneData.get(i).getRegdate().substring(0,10)+"   ");
					System.out.printf("%5s",phoneData.get(i).getComName()+"   ");
					System.out.printf("%5s",phoneData.get(i).getMajor()+"   ");
					System.out.println(phoneData.get(i).getYear());
				}
			}
			
			System.out.println("======================================================================");
			
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
			System.out.println("----------------친구 타입을 선택하세요.--------------------");
			System.out.println("\t 1.회사 친구 정보 ");
			System.out.println("\t 2.대학 친구 정보");
			int menu=Main.sc.nextInt();
			Main.sc.nextLine();
			
			switch(menu) {
			case 1:
				System.out.println("<<   회사 친구 정보 조회     >>");
				PhoneComDao comDao=new PhoneComDao();
				List<PhoneComDto> comData=comDao.comList(conn);
			
				if(comData!=null && !comData.isEmpty()) {
					System.out.print("\nIDX"+"\t");
					System.out.print("이름"+"\t");
					System.out.print("전화번호"+"\t");
					System.out.print("이메일"+"\t");
					System.out.print("주소"+"\t");
					System.out.print("회사이름"+"\t");
					System.out.println("등록일자"+"\t");
					System.out.println("======================================================================");
					for(int i=0;i<comData.size();i++) {
						System.out.print(comData.get(i).getIdx()+"\t");
						System.out.print(comData.get(i).getName()+"\t");
						System.out.print(comData.get(i).getPhoneNumber()+"\t");
						System.out.print(comData.get(i).getEmail()+"\t");
						System.out.print(comData.get(i).getAddress()+"\t");
						System.out.print(comData.get(i).getCompany()+"\t");
						System.out.println(comData.get(i).getRegdate());
					}
				System.out.println("======================================================================");
				}else {
					System.out.println("회사 친구 정보가 없습니다.");
				}
				
				break;
				
			case 2: 
				System.out.println("<<   대학 친구 정보 조회     >>");
				PhoneUnivDao uDao=new PhoneUnivDao();
				List<PhoneUnivDto> univData=uDao.uList(conn);
				
				if(univData !=null && !univData.isEmpty()) {
					System.out.print("\nIDX"+"\t");
					System.out.print("이름"+"\t");
					System.out.print("전화번호"+"\t\t");
					System.out.print("이메일"+"\t\t");
					System.out.print("주소"+"\t");
					System.out.print("전공"+"\t");
					System.out.print("학년"+"\t");
					System.out.println("등록일자"+"\t");
					
					System.out.println("==============================================================================================");
				
					for(int i=0;i<univData.size();i++) {
						System.out.print(univData.get(i).getIdx()+"\t");
						System.out.print(univData.get(i).getName()+"\t");
						System.out.print(univData.get(i).getPhoneNumber()+"\t");
						System.out.print(univData.get(i).getEmail()+"\t\t");
						System.out.print(univData.get(i).getAddress()+"\t");
						System.out.print(univData.get(i).getMajor()+"\t");
						System.out.print(univData.get(i).getYear()+"\t");
						System.out.println(univData.get(i).getRegdate());
					}
					System.out.println("==============================================================================================");
						
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
			
			System.out.println(" -------------------친구 타입을 선택하세요. ------------------");
			System.out.println("1.회사 친구 정보   2.대학 친구 정보");
			int menu=Main.sc.nextInt();
			Main.sc.nextLine();
			
			switch(menu) {
			case 1:
				System.out.println("이름을 입력하세요");
				String cName=Main.sc.nextLine();
				
				PhoneComDao cDao=new PhoneComDao();
				List<PhoneComDto> comData=cDao.select(conn,cName);

				if(comData!=null) {
					System.out.print("\nIDX"+"\t");
					System.out.print("회사이름"+"\t");
					System.out.print("이름"+"\t");
					System.out.print("전화번호"+"\t");
					System.out.print("이메일"+"\t");
					System.out.print("주소"+"\t");
					System.out.println("등록일자"+"\t");
					System.out.println("======================================================================");
					for(int i=0;i<comData.size();i++) {
						System.out.print(comData.get(i).getIdx()+"\t");
						System.out.print(comData.get(i).getCompany()+"\t");
						System.out.print(comData.get(i).getName()+"\t");
						System.out.print(comData.get(i).getPhoneNumber()+"\t");
						System.out.print(comData.get(i).getEmail()+"\t");
						System.out.print(comData.get(i).getAddress()+"\t");
						System.out.println(comData.get(i).getRegdate());
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
					System.out.print("\nIDX"+"\t");
					System.out.print("회사이름"+"\t");
					System.out.print("이름"+"\t");
					System.out.print("전화번호"+"\t");
					System.out.print("이메일"+"\t");
					System.out.print("주소"+"\t");
					System.out.print("전공"+"\t");
					System.out.print("학년"+"\t");
					System.out.print("등록일자"+"\t");
					System.out.println("======================================================================");
					for(int i=0;i<univData.size();i++) {
						System.out.print(univData.get(i).getIdx()+"\t");
						System.out.print(univData.get(i).getName()+"\t");
						System.out.print(univData.get(i).getPhoneNumber()+"\t");
						System.out.print(univData.get(i).getEmail()+"\t");
						System.out.print(univData.get(i).getAddress()+"\t");
						System.out.println(univData.get(i).getMajor()+"\t");
						System.out.println(univData.get(i).getYear()+"\t");
						System.out.println(univData.get(i).getRegdate());
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

				System.out.println(" -------------------친구 타입을 선택하세요. ------------------");
				System.out.println("1.회사 친구 저장     2.대학 친구 저장");
				int menu=Main.sc.nextInt();
				Main.sc.nextLine();
			
				switch(menu) {
				case 1:
					System.out.println("\n<< 회사 친구 상세 정보 저장 >>");
					PhoneComDao com=new PhoneComDao();
					
					System.out.println("회사 이름을 입력하세요.");
					String company=Main.sc.nextLine();
					
					result=com.insert(conn, company);

					if(result >0) {
						System.out.println("회사 친구는 "+result+"행이 추가되었습니다.");
						conn.commit();
					}else {
						System.out.println("잘못된 입력입니다.");
					}
					break;
				case 2:
					System.out.println("<< 대학 친구 상세 정보 저장 >>");
					PhoneUnivDao univ=new PhoneUnivDao();
					System.out.println("전공을 입력하세요.");
					String major=Main.sc.nextLine();
					System.out.println("학년을 입력하세요.");
					String year=Main.sc.nextLine();
					
					result=univ.insert(conn,major,year);
					
					if(result >0) {
						System.out.println("대학친구는 "+result+"행이 추가되었습니다.");
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
			
				System.out.println(" -------------------친구 타입을 선택하세요. ------------------");
				
				System.out.println("\t 1.회사 친구");
				System.out.println("\t 2.대학 친구");
				int menu=Main.sc.nextInt();
				Main.sc.nextLine();
				
				switch(menu) {
				case 1:
					
					System.out.println("이름을 입력하세요.");
					String searchName=Main.sc.nextLine();
					
					PhoneComDao cdao=new PhoneComDao();
					List<PhoneComDto> phoneData=cdao.select(conn,searchName);

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
						System.out.println(phoneData.get(0).getIdx());
						result=	cdao.update(conn,phoneData.get(0).getIdx(),nCompany);
						if(result>0) {
							System.out.println("■  "+result+"행이 수정되었습니다.  ■");
							conn.commit();
						}	
					} else {
						System.out.println("정보가 추가되지 않았습니다.");
					}
					}else {
						System.out.println("존재하는 정보가 없습니다. 다시입력하세요.");
					}
					break;
				case 2:
					
					System.out.println("이름을 입력하세요.");
					String searchUniv=Main.sc.nextLine();
					PhoneUnivDao udao=new PhoneUnivDao();
					List<PhoneUnivDto> univData=udao.select(conn,searchUniv);
					
					if(univData !=null && !univData.isEmpty()) { //데이터 존재 여부
						System.out.print("IDX    ");
						System.out.print("이름      ");
						System.out.print("전화번호   ");
						System.out.print("주소   ");
						System.out.print("이메일  ");
						System.out.println("등록일자  ");
						System.out.println("======================================================================");
						for(int i=0;i<univData.size();i++) {
							System.out.print(univData.get(i).getIdx()+"   ");
							System.out.print(univData.get(i).getName()+"   ");
							System.out.print(univData.get(i).getPhoneNumber()+"   ");
							System.out.print(univData.get(i).getAddress()+"    ");
							System.out.print(univData.get(i).getEmail()+"   ");
							System.out.println(univData.get(i).getRegdate().substring(0,10));
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
			
				System.out.println(" -------------------친구 타입을 선택하세요. ------------------");
				
				System.out.println("\t 1.회사 친구");
				System.out.println("\t 2.대학 친구");
				int menu=Main.sc.nextInt();
				Main.sc.nextLine();
				
				switch(menu) {
				case 1:
					System.out.println("이름을 입력하세요.");
					String searchCom=Main.sc.nextLine();
					
					PhoneComDao cdao=new PhoneComDao();
					List<PhoneComDto> comData=cdao.select(conn,searchCom);

					if(comData !=null && !comData.isEmpty()) { //데이터 존재 여부
						System.out.print("IDX    ");
						System.out.print("이름      ");
						System.out.print("전화번호   ");
						System.out.print("주소   ");
						System.out.print("이메일  ");
						System.out.println("등록일자  ");
						System.out.println("======================================================================");
						for(int i=0;i<comData.size();i++) {
							System.out.print(comData.get(i).getIdx()+"   ");
							System.out.print(comData.get(i).getName()+"   ");
							System.out.print(comData.get(i).getPhoneNumber()+"   ");
							System.out.print(comData.get(i).getAddress()+"    ");
							System.out.print(comData.get(i).getEmail()+"   ");
							System.out.println(comData.get(i).getRegdate().substring(0,10));
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
						System.out.print("IDX    ");
						System.out.print("이름      ");
						System.out.print("전화번호   ");
						System.out.print("주소   ");
						System.out.print("이메일  ");
						System.out.println("등록일자  ");
						System.out.println("======================================================================");
						for(int i=0;i<univData.size();i++) {
							System.out.print(univData.get(i).getIdx()+"   ");
							System.out.print(univData.get(i).getName()+"   ");
							System.out.print(univData.get(i).getPhoneNumber()+"   ");
							System.out.print(univData.get(i).getAddress()+"    ");
							System.out.print(univData.get(i).getEmail()+"   ");
							System.out.println(univData.get(i).getRegdate().substring(0,10));
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
	
	
