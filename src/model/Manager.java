package model;

import java.util.Date;

import javaxt.utils.string;

public class Manager extends User {

	private SportObject sportObject;
	
	public Manager(string userName, string password, string name, string surname, Gender gender, Date birthday, SportObject so) {
		super(userName, password, name, surname, gender, birthday);
		this.sportObject = so;
		// TODO Auto-generated constructor stub
	}
	
	

}
