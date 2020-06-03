package phoneUniv;

public class PhoneUnivDto {
	
	private int idx;
	private String name;
	private String phoneNumber;
	private String email;
	private String address;
	private String regdate;
	private String major;
	private String year;
	
	
	
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
