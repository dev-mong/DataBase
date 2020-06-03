package phoneBook;

import java.util.List;

//데이터 입출력

public class PhoneInfoManager {
	
	PhoneInfoDao dao=new PhoneInfoDao();
	
	//메뉴
	public  void pmMenu() {
		
		System.out.println("   \t\tPhoneBook MENU  ");
		while(true) {
			
		System.out.println("----------------------------------------------------");
		System.out.println("\t1.전체 조회  2.검색 3.전화번호 저장  0.종료");
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
		System.out.print("주소   ");
		System.out.print("이메일  ");
		System.out.println("등록일자  ");
		System.out.println("======================================================================");
		if(phoneData !=null && !phoneData.isEmpty()) { //데이터 존재 여부
			for(int i=0;i<phoneData.size();i++) {
				System.out.print(phoneData.get(i).getIdx()+"   ");
				System.out.print(phoneData.get(i).getName()+"   ");
				System.out.print(phoneData.get(i).getPhoneNumber()+"   ");
				System.out.print(phoneData.get(i).getAddress()+"    ");
				System.out.print(phoneData.get(i).getEmail()+"   ");
				System.out.println(phoneData.get(i).getRegdate().substring(0,10));
			}
		}
		
		System.out.println("======================================================================");
	
	}
	
	//기본 정보 검색 
	public void pSearch() {
		System.out.println("이름을 입력하세요");
		
		String name=Main.sc.nextLine();
		
		List<PhoneInfoDto> phoneData = dao.searchName(name);
		
		System.out.print("IDX    ");
		System.out.print("이름      ");
		System.out.print("전화번호   ");
		System.out.print("주소   ");
		System.out.print("이메일  ");
		System.out.println("등록일자  ");
		System.out.println("======================================================================");
		if(phoneData !=null && !phoneData.isEmpty()) { //데이터 존재 여부
			for(int i=0;i<phoneData.size();i++) {
				System.out.print(phoneData.get(i).getIdx()+"   ");
				System.out.print(phoneData.get(i).getName()+"   ");
				System.out.print(phoneData.get(i).getPhoneNumber()+"   ");
				System.out.print(phoneData.get(i).getAddress()+"    ");
				System.out.print(phoneData.get(i).getEmail()+"   ");
				System.out.println(phoneData.get(i).getRegdate().substring(0,10));
			}
		}
		
		System.out.println("======================================================================");
	
		
		
	}
	
	//기본 정보 저장
	public void pInsert() {
		
		System.out.println("이름을 입력하세요");
		String fr_name=Main.sc.nextLine();
		System.out.println("전화번호를 입력하세요");
		String fr_phoneNumber=Main.sc.nextLine();
		System.out.println("이메일을 입력하세요");
		String fr_email=Main.sc.nextLine();
		System.out.println("주소를 입력하세요");
		String fr_address=Main.sc.nextLine();

		int result = dao.insert(fr_name, fr_phoneNumber,fr_email,fr_address);
		
		System.out.println("======================================================================");
		if(result>0) {
			System.out.println(result+"행이 추가 되었습니다.");
		}else {
			System.out.println("잘못된 입력입니다. 다시 입력하세요.");
		}
		System.out.println("======================================================================");
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
