package model;

import java.util.Date;

public class Coach extends User{
	
	private transient TrainingHistory trainingHistory;

	public Coach(String userName, String password, String name, String surname, Gender gender, Date birthday, UserType userType, TrainingHistory th) {
		super(userName, password, name, surname, gender, birthday, userType);
		this.trainingHistory = th;
	}

	public TrainingHistory getTrainingHistory() {
		return trainingHistory;
	}

	public void setTrainingHistory(TrainingHistory trainingHistory) {
		this.trainingHistory = trainingHistory;
	}
	
}
