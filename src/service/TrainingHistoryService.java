package service;

import java.io.IOException;
import java.util.List;

import model.TrainingHistory;
import repository.TrainingHistoryRepository;

public class TrainingHistoryService {

	private TrainingHistoryRepository thr = new TrainingHistoryRepository();

	public TrainingHistoryService() {
		super();
	}
	
	
	public boolean addTrainingHistory(TrainingHistory trainingHistory) throws IOException{
		return thr.addTrainingHistory(trainingHistory);
	}
	
	public boolean deleteTrainingHistory(TrainingHistory trainingHistory) throws IOException{
		return thr.deleteTrainingHistory(trainingHistory);
	}
	
	public List<TrainingHistory> findAllHistories() throws IOException{
		return thr.findAllHistories();
	}
	
	
}
