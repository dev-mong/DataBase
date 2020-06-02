package phoneBook;

import java.util.List;

//데이터 입출력

public class PhoneInfoManager {
	
	PhoneInfoDao dao=new PhoneInfoDao();
	
	public  void pmMenu() {
		
		System.out.println("PhoneBook MENU");
		
		System.out.println("1.전체 조회 2.검색");
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
	
	
	
}
