package PhoneInfo;

public class PhoneInfoAllDto {

	private int idx; //인덱스
	private String name; //이름
	private String phoneNumber; //전화번호
	private String address; //주소
	private String email; //이메일
	private String regdate; //생일
	
	private String comName;//회사이름
	
	private String major;//전공
	private int year; //학년
	
	
	public PhoneInfoAllDto(int idx, String name, String phoneNumber, String address, String email, String regdate,
			String comName, String major, int year) {
	
		this.idx=idx;
		this.name=name;
		this.phoneNumber=phoneNumber;
		this.address=address;
		this.email=email;
		this.regdate=regdate;
		
		this.comName=comName;
		
		this.major=major;
		this.year=year;
	
	}


	public int getIdx() {
		return idx;
	}


	public void setIdx(int idx) {
		this.idx = idx;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getRegdate() {
		return regdate;
	}


	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}


	public String getComName() {
		return comName;
	}


	public void setComName(String comName) {
		this.comName = comName;
	}



	public String getMajor() {
		return major;
	}


	public void setMajor(String major) {
		this.major = major;
	}


	public int getYear() {
		return year;
	}


	public void setYear(int year) {
		this.year = year;
	}

	
	
	
}
