package model;

import java.util.Date;
import java.util.List;

public class Coach extends User{
	
	private transient TrainingHistory trainingHistory;
	private List<Training> notCompletedTrainings;

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

	public List<Training> getNotCompletedTrainings() {
		return notCompletedTrainings;
	}

	public void setNotCompletedTrainings(List<Training> notCompletedTrainings) {
		this.notCompletedTrainings = notCompletedTrainings;
	}
	
	

}
