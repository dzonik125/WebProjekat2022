package model;

import java.util.Date;
import java.util.List;

import javaxt.utils.string;

public class Buyer extends User{

	private int membership;
	private List<SportObject> visitedObjects;
	private int points;
	private BuyerType buyerType;
	
	public Buyer(string userName, string password, string name, string surname, Gender gender, Date birthday, int m, List<SportObject> los, int p, BuyerType bt) {
		super(userName, password, name, surname, gender, birthday);
		this.buyerType = bt;
		this.points = p;
		this.visitedObjects = los;
		this.membership = m;
		// TODO Auto-generated constructor stub
	}
	
}
