package model;

import java.util.Date;

import javaxt.utils.string;

public class Coach extends User{
	
	private TrainingHistory trainingHistory;

	public Coach(string userName, string password, string name, string surname, Gender gender, Date birthday, TrainingHistory th) {
		super(userName, password, name, surname, gender, birthday);
		this.trainingHistory = th;
		// TODO Auto-generated constructor stub
	}

	
}
