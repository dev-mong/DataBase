package manager.ver2;

import java.util.Scanner;

public class Main {

	public static void main(String[] args)  {

	Scanner sc=new Scanner(System.in);
	
	EmpManager mm=new EmpManager();
	DeptManager dm=new DeptManager();
	
	try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	}

	while(true) {
		System.out.println("<< 메뉴 >>");
		System.out.println("----------------------------");
		System.out.println("1.사원 2.부서 0.종료");
		System.out.println("----------------------------");
		
	
		int menu=sc.nextInt();
		sc.nextLine();
		
		switch(menu) {
		case 1:
			mm.menu();
			break;
		case 2: 
			dm.menu();
			break;
		
		case 0:
			System.out.println("종료되었습니다.");
			System.exit(0);
		
		}
		}
	}

}
