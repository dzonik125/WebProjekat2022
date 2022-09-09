package model;

public class BuyerType {
	private BType buyerType;
	private double discount;
	private int neededPoints;
	public BuyerType(BType buyerType) {
		super();
		this.buyerType = buyerType;
		if(buyerType.toString().equals("BRONZE")) {
			this.neededPoints = 3942;
			this.discount = 0.1;
		}else if (buyerType.toString().equals("SILVER")) {
			this.neededPoints = 7884;
			this.discount = 0.3;
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
