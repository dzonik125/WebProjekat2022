package model;

import java.awt.Image;

import javaxt.utils.string;

public class SportObject {
	private string name;
	private ObjectType objectType;
	private string services;
	private ObjectStatus objectStatus;
	private Location location;
	private Image logo;
	private double avgGrade;
	private string workingHours;
	
	public SportObject(string name, ObjectType objectType, string services, ObjectStatus objectStatus,
			Location location, Image logo, double avgGrade, string workingHours) {
		super();
		this.name = name;
		this.objectType = objectType;
		this.services = services;
		this.objectStatus = objectStatus;
		this.location = location;
		this.logo = logo;
		this.avgGrade = avgGrade;
		this.workingHours = workingHours;
	}
	
	
}
