package phoneBook;

import java.util.Scanner;

public class Main {

	public static Scanner sc=new Scanner(System.in);
	
	public static void main(String[] args) {
		
		//드라이버 로드
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
		
		PhoneInfoManager pm=new PhoneInfoManager();
		pm.pmMenu();
	}

}
