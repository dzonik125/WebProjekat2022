package controller;

import java.io.IOException;
import java.util.List;

import model.Coach;
import model.SportObject;
import model.Training;
import model.TrainingType;
import service.TrainingService;

public class TrainingController {

	private TrainingService ts = new TrainingService();

	public TrainingController() {
		super();
	}
	
	public boolean addTraining(Training training) throws IOException{
		return ts.addTraining(training);
	}
	
	public String editTraining(Training training, Training selectedTraining) throws IOException{
		return ts.editTraining(training, selectedTraining);
	}
	
	public boolean deleteTraining(Training t) throws IOException{
		return ts.deleteTraining(t);
	}
	
	public Training findTraining(int id) throws IOException{
		return ts.findTraining(id);
	}
	
	public List<Training> findAllTraining() throws IOException{
		return ts.findAllTraining();
	}
	
	public List<Training> findTrainingsForSportObject(String name) throws IOException{
		return ts.findTrainingsForSportObject(name);
	}
	
	public List<Training> getGroupTrainingsForCoach (String username) throws IOException {
		return ts.getGroupTrainingsForCoach(username);
	}
	
	public void checkCompletedPersonal () throws IOException {
		ts.checkCompletedPersonal();
	}
	
	public void deleteSportObjectsFromTrainings (String name) throws IOException {
		ts.deleteSportObjectsFromTrainings(name);
	}
	
	public void deleteCoachesTrainings (String coach) throws IOException {
		ts.deleteCoachesTrainings(coach);
	}
}
