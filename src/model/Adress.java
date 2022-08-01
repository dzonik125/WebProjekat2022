package model;

public class Adress {
	private String street;
	private int sNum;
	private String city;
	private int postCode;
	public Adress(String string, int sNum, String string2, int postCode){
		super();
		this.street = string;
		this.sNum = sNum;
		this.city = string2;
		this.postCode = postCode;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public int getsNum() {
		return sNum;
	}
	public void setsNum(int sNum) {
		this.sNum = sNum;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public int getPostCode() {
		return postCode;
	}
	public void setPostCode(int postCode) {
		this.postCode = postCode;
	}
	
	
	
	
}