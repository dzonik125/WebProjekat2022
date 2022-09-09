package service;

import java.io.IOException;
import java.util.ArrayList;
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
	
	public List<TrainingHistory> findTrainingHistoriesForBuyer (String buyer) throws IOException {
		List<TrainingHistory> toRet = new ArrayList<>();
		for (TrainingHistory trainingHistory : thr.findAllHistories()) {
			if(trainingHistory.getBuyer().equals(buyer)) {
				toRet.add(trainingHistory);
			}
		}
		return toRet;
	}
	
}
