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
	
	public Training findTraining(SportObject sportObject, String name, Coach coach, TrainingType trainingType, long duration) throws IOException{
		return ts.findTraining(sportObject, name, coach, trainingType, duration);
	}
	
	public List<Training> findAllTraining() throws IOException{
		return ts.findAllTraining();
	}
}
