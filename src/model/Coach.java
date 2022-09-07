package model;

import java.util.Date;
import java.util.List;

public class Coach extends User{
	
	private transient TrainingHistory trainingHistory;
	private List<Integer> notCompletedTrainings;

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

	public List<Integer> getNotCompletedTrainings() {
		return notCompletedTrainings;
	}

	public void setNotCompletedTrainings(List<Integer> notCompletedTrainings) {
		this.notCompletedTrainings = notCompletedTrainings;
	}
	
	

}
