package model;

import java.util.Date;

public class Manager extends User {

	private SportObject sportObject;
	
	public Manager(String userName, String password, String name, String surname, Gender gender, Date birthday, UserType userType, SportObject so) {
		super(userName, password, name, surname, gender, birthday, userType);
		this.sportObject = so;
	}

	public SportObject getSportObject() {
		return sportObject;
	}

	public void setSportObject(SportObject sportObject) {
		this.sportObject = sportObject;
	}

}
