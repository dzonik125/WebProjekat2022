package model;

import java.awt.Image;

import javaxt.utils.string;

public class SportObject {
	private String name;
	private ObjectType objectType;
	private String services;
	private ObjectStatus objectStatus;
	private Location location;
	private String imageRelLocation;
	private double avgGrade;
	private String workingHours;
	
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
	}
	
	
}
