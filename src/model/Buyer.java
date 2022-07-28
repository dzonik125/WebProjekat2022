package model;

import java.util.Date;
import java.util.List;

import javaxt.utils.string;

public class Buyer extends User{

	private Membership membership;
	private List<SportObject> visitedObjects;
	private int points;
	private BuyerType buyerType;
	
	public Buyer(String userName, String password, String name, String surname, Gender gender, Date birthday, UserType userType, Membership m, List<SportObject> los, int p, BuyerType bt) {
		super(userName, password, name, surname, gender, birthday, userType);
		this.buyerType = bt;
		this.points = p;
		this.visitedObjects = los;
		this.membership = m;
		// TODO Auto-generated constructor stub
	}

	public Membership getMembership() {
		return membership;
	}

	public void setMembership(Membership membership) {
		this.membership = membership;
	}

	public List<SportObject> getVisitedObjects() {
		return visitedObjects;
	}

	public void setVisitedObjects(List<SportObject> visitedObjects) {
		this.visitedObjects = visitedObjects;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public BuyerType getBuyerType() {
		return buyerType;
	}

	public void setBuyerType(BuyerType buyerType) {
		this.buyerType = buyerType;
	}
	
	
	
}
