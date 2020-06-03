package MenuIf;

public interface MenuInterface {

	public static final int menu1=1;
	public static final int menu2=2;
	public static final int menu3=3;
	public static final int menu4=4;
	public static final int menu5=5;
	public static final int menu6=6;
	public static final int menu0=0;
	
	public static void colPrint() {
		System.out.print("\nIDX");
		System.out.print("\t이름");
		System.out.print("\t전화번호");
		System.out.print("\t이메일   ");
		System.out.print("\t주소        ");
		System.out.print("\t등록일자 \t");
	}
	
	public static void comCol(){
		System.out.print("\t회사이름"+"\t");
	}
	
	public static void univCol() {
		System.out.print("\t전공"+"\t");
		System.out.print("학년");
	}
	
	public static void typePrint() {
		System.out.println("\n--------------  친구 타입을 선택하세요.  ------------------");
		System.out.println("\t\t"+MenuInterface.menu1+"회사 친구 정보 ");
		System.out.println("\t\t"+MenuInterface.menu2+"대학 친구 정보");
		System.out.println("-----------------------------------------------------");
	}
	
	
}
