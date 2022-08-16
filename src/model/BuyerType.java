package model;

public class BuyerType {
	private BType buyerType;
	private double discount;
	private int neededPoints;
	public BuyerType(BType buyerType, double discount, int neededPoints) {
		super();
		this.buyerType = buyerType;
		if(buyerType.toString().equals("BRONZE")) {
			this.neededPoints = 250;
			this.discount = 0;
		}else if (buyerType.toString().equals("SILVER")) {
			this.neededPoints = 500;
			this.discount = 0.2;
		}else {
			this.neededPoints = 0;
			this.discount = 0.5;
		}
	}
	
	public BType getBuyerType() {
		return buyerType;
	}
	public void setBuyerType(BType buyerType) {
		this.buyerType = buyerType;
	}
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	public int getNeededPoints() {
		return neededPoints;
	}
	public void setNeededPoints(int neededPoints) {
		this.neededPoints = neededPoints;
	}
	
	
}
