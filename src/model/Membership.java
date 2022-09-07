package model;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class Membership {
	private String id;
	private MembershipType membershipType;
	private Date dueDate; //od kada vazi
	private Date active; //do kada vazi
	private int price;
	private String buyer;
	private MembershipStatus membershipStatus;
	private boolean isDeleted;
	private int dailyLogs; //broj ulazaka dnevno (koliko je dozvoljeno)
	
	
	
	public Membership(MembershipType membershipType, int price,
			String buyer, int dailyLogs) {
		super();
		this.id = generateID();
		this.membershipType = membershipType;
		Calendar cal = Calendar.getInstance();
		Date date = cal.getTime();
		this.dueDate = date;
		Date act = null;
		if(membershipType.toString().equals("ANNUAL")) {
			cal.setTime(date);
			cal.add(Calendar.YEAR, 1);
			act = cal.getTime();
		} else {
			cal.setTime(date);
			cal.add(Calendar.MONTH, 1);
			act = cal.getTime();
		}
		this.active = act;
		this.price = price;
		this.buyer = buyer;
		this.membershipStatus = MembershipStatus.ACTIVE;
		this.dailyLogs = dailyLogs;
		this.isDeleted = false;
	}



	private String generateID() {
		int leftLimit = 48; // numeral '0'
	    int rightLimit = 122; // letter 'z'
	    int targetStringLength = 10;
	    Random random = new Random();

	    return random.ints(leftLimit, rightLimit + 1)
	      .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
	      .limit(targetStringLength)
	      .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
	      .toString();
	}



	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public MembershipType getMembershipType() {
		return membershipType;
	}



	public void setMembershipType(MembershipType membershipType) {
		this.membershipType = membershipType;
	}



	public Date getDueDate() {
		return dueDate;
	}



	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}



	public Date getActive() {
		return active;
	}



	public void setActive(Date active) {
		this.active = active;
	}



	public int getPrice() {
		return price;
	}



	public void setPrice(int price) {
		this.price = price;
	}



	public String getBuyer() {
		return buyer;
	}



	public void setBuyer(String buyer) {
		this.buyer = buyer;
	}



	public MembershipStatus getMembershipStatus() {
		return membershipStatus;
	}



	public void setMembershipStatus(MembershipStatus membershipStatus) {
		this.membershipStatus = membershipStatus;
	}



	public int getDailyLogs() {
		return dailyLogs;
	}



	public void setDailyLogs(int dailyLogs) {
		this.dailyLogs = dailyLogs;
	}



	public boolean isDeleted() {
		return isDeleted;
	}



	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	
	
	
}
