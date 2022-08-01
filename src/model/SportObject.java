package model;

public class SportObject {
	private String name;
	private ObjectType objectType;
	private String services;
	private ObjectStatus objectStatus;
	private Location location;
	private String imageRelLocation;
	private double avgGrade;
	private String workingHours;
	private boolean deleted;
	
	public SportObject(String name, ObjectType objectType, String services, ObjectStatus objectStatus,
			Location location, String imageString, double avgGrade, String workingHours) {
		super();
		this.name = name;
		this.objectType = objectType;
		this.services = services;
		this.objectStatus = objectStatus;
		this.location = location;
		this.imageRelLocation = imageString;
		this.avgGrade = avgGrade;
		this.workingHours = workingHours;
		this.deleted = false;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ObjectType getObjectType() {
		return objectType;
	}

	public void setObjectType(ObjectType objectType) {
		this.objectType = objectType;
	}

	public String getServices() {
		return services;
	}

	public void setServices(String services) {
		this.services = services;
	}

	public ObjectStatus getObjectStatus() {
		return objectStatus;
	}

	public void setObjectStatus(ObjectStatus objectStatus) {
		this.objectStatus = objectStatus;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public String getImageRelLocation() {
		return imageRelLocation;
	}

	public void setImageRelLocation(String imageRelLocation) {
		this.imageRelLocation = imageRelLocation;
	}

	public double getAvgGrade() {
		return avgGrade;
	}

	public void setAvgGrade(double avgGrade) {
		this.avgGrade = avgGrade;
	}

	public String getWorkingHours() {
		return workingHours;
	}

	public void setWorkingHours(String workingHours) {
		this.workingHours = workingHours;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	
	
	
}
