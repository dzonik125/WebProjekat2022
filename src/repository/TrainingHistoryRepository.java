package repository;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import model.TrainingHistory;

public class TrainingHistoryRepository {
	private String fileLocation = "./data/trainingHistory.json";

	public TrainingHistoryRepository() {
		super();
	}
	
	public boolean addTrainingHistory(TrainingHistory trainingHistory) throws IOException {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		Type listType = new TypeToken<List<TrainingHistory>>(){}.getType();
		FileReader fileReader = new FileReader(fileLocation);
		List<TrainingHistory> trainings = gson.fromJson(fileReader, listType);
		fileReader.close();
		if(trainings == null) {
			trainings = new ArrayList<>();
		}
		boolean added = trainings.add(trainingHistory);
		FileWriter fileWriter = new FileWriter(fileLocation);
		gson.toJson(trainings, fileWriter);
		fileWriter.close();
		return added;
	}
	
	public boolean deleteTrainingHistory(TrainingHistory trainingHistory) throws IOException {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		Type listType = new TypeToken<List<TrainingHistory>>(){}.getType();
		FileReader fileReader = new FileReader(fileLocation);
		List<TrainingHistory> trainings = gson.fromJson(fileReader, listType);
		fileReader.close();
		for (TrainingHistory trainingHistory2 : trainings) {
			if(trainingHistory.equals(trainingHistory2)) {
				trainingHistory2.setDeleted(true);
				FileWriter fileWriter = new FileWriter(fileLocation);
				gson.toJson(trainings, fileWriter);
				fileWriter.close();
				return true;
			}
		}
		
		return false;
	}
	
	public List<TrainingHistory> findAllHistories() throws IOException{
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		Type listType = new TypeToken<List<TrainingHistory>>(){}.getType();
		FileReader fileReader = new FileReader(fileLocation);
		List<TrainingHistory> trainings = gson.fromJson(fileReader, listType);
		fileReader.close();
		return trainings;
	}
}
