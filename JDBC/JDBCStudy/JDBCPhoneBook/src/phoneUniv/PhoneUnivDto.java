package phoneUniv;

public class PhoneUnivDto {
	
	private int idx; //인덱스
	private String name; //이름
	private String phoneNumber; //전화번호
	private String email; //이메일 
	private String address; //주소
	private String regdate; //저장일
	private String major; //전공
	private String year; //학년
	
	
	public PhoneUnivDto(int idx, String name, String phoneNumber,  String email,String address, String regdate,
			String major,String year) {
		this.idx=idx;
		this.name=name;
		this.phoneNumber=phoneNumber;
		this.email=email;
		this.address=address;
		this.regdate=regdate;
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



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getAddress() {
		return address;
	}



	public void setAddress(String address) {
		this.address = address;
	}



	public String getRegdate() {
		return regdate;
	}



	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}



	public String getMajor() {
		return major;
	}



	public void setMajor(String major) {
		this.major = major;
	}



	public String getYear() {
		return year;
	}



	public void setYear(String year) {
		this.year = year;
	}
	
	
}
