package controller;

import java.io.IOException;
import java.util.List;

import model.TrainingHistory;
import service.TrainingHistoryService;

public class TrainingHistoryController {

	private TrainingHistoryService ths = new TrainingHistoryService();

	public TrainingHistoryController() {
		super();
	}
	
	public boolean addTrainingHistory(TrainingHistory trainingHistory) throws IOException{
		return ths.addTrainingHistory(trainingHistory);
	}
	
	public boolean deleteTrainingHistory(TrainingHistory trainingHistory) throws IOException{
		return ths.deleteTrainingHistory(trainingHistory);
	}
	
	public List<TrainingHistory> findAllHistories() throws IOException{
		return ths.findAllHistories();
	}
}
