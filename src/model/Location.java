package model;

public class Location {
	private double latitude;
	private double longtitude;
	private Adress adress;
	private boolean deleted;
	
	public Location(double latitude, double longtitude, Adress adress) {
		super();
		this.latitude = latitude;
		this.longtitude = longtitude;
		this.adress = adress;
		this.deleted = false;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongtitude() {
		return longtitude;
	}

	public void setLongtitude(double longtitude) {
		this.longtitude = longtitude;
	}

	public Adress getAdress() {
		return adress;
	}

	public void setAdress(Adress adress) {
		this.adress = adress;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	
	
}
