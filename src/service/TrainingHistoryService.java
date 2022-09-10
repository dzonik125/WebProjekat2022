package service;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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
	
	public void deleteSportObjectFromTH(String name) throws IOException {
		List<TrainingHistory> toRet = findAllHistories();
		for (TrainingHistory trainingHistory : toRet) {
			if(trainingHistory.getTraining().getSportObject().getName().equals(name)) {
				trainingHistory.getTraining().getSportObject().setDeleted(true);
			} 
		}
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		FileWriter fileWriter = new FileWriter("./data/trainingHistory.json");
		gson.toJson(toRet, fileWriter);
		fileWriter.close();
	}
	
}
