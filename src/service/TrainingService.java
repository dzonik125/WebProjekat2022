package service;

import java.io.IOException;
import java.util.ArrayList;
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
	
	public Training findTraining(int id) throws IOException{
		return tr.findTraining(id);
	}
	
	public List<Training> findAllTraining() throws IOException{
		return tr.findAllTraining();
	}
	
	public List<Training> findTrainingsForSportObject(String name) throws IOException{
		List<Training> toRet = new ArrayList<>();
		for (Training training : findAllTraining()) {
			if(training.getSportObject().getName().equals(name)) {
				toRet.add(training);
			}
		}
		return toRet;
	}
	
	
}
