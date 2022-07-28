package model;

import java.time.LocalDate;
import java.util.Date;

import javaxt.utils.string;

public class User {
	private String userName;
	private String password;
	private String name;
	private String surname;
	private Gender gender;
	private Date birthday;
	private boolean deleted;
	private UserType userType;
	
	public User(String userName, String password, String name, String surname, Gender gender, Date birthday, UserType uType) {
		super();
		this.userName = userName;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.gender = gender;
		this.birthday = birthday;
		this.deleted = false;
		this.userType = uType;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	
	public UserType getUserType() {
		return userType;
	}
	
	
}
