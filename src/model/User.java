package model;

import java.util.Date;

import javaxt.utils.string;

public class User {
	private string userName;
	private string password;
	private string name;
	private string surname;
	private Gender gender;
	private Date birthday;
	
	public User(string userName, string password, string name, string surname, Gender gender, Date birthday) {
		super();
		this.userName = userName;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.gender = gender;
		this.birthday = birthday;
	}
	
	
}
