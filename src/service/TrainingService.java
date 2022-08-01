package service;

import java.io.IOException;
import java.util.List;

import model.Coach;
import model.SportObject;
import model.Training;
import model.TrainingType;
import repository.TrainingRepository;

public class TrainingService {

	private TrainingRepository tr = new TrainingRepository();

	public TrainingService() {
		super();
	}
	
	public boolean addTraining(Training training) throws IOException{
		return tr.addTraining(training);
	}
	
	public String editTraining(Training training, Training selectedTraining) throws IOException{
		return tr.editTraining(training, selectedTraining);
	}
	
	public boolean deleteTraining(Training t) throws IOException{
		return tr.deleteTraining(t);
	}
	
	public Training findTraining(SportObject sportObject, String name, Coach coach, TrainingType trainingType, long duration) throws IOException{
		return tr.findTraining(sportObject, name, coach, trainingType, duration);
	}
	
	public List<Training> findAllTraining() throws IOException{
		return tr.findAllTraining();
	}
	
	
}
