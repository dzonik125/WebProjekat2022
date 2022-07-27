package model;

public class BuyerType {
	private BType buyerType;
	private double discount;
	private int neededPoints;
	public BuyerType(BType buyerType, double discount, int neededPoints) {
		super();
		this.buyerType = buyerType;
		this.discount = discount;
		this.neededPoints = neededPoints;
	}
	
	
}
