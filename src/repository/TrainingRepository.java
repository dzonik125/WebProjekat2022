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

import model.Coach;
import model.SportObject;
import model.Training;
import model.TrainingType;

public class TrainingRepository {
	private String fileLocation = "./data/trainings.json";

	public TrainingRepository() {
		super();
	}
	
	public boolean addTraining(Training training) throws IOException {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		Type listType = new TypeToken<List<Training>>(){}.getType();
		FileReader fileReader = new FileReader(fileLocation);
		List<Training> trainings = gson.fromJson(fileReader, listType);
		fileReader.close();
		if(trainings == null) {
			trainings = new ArrayList<>();
		}
		
		for (Training training2 : trainings) {
			if(training2.getCoach().equals(training.getCoach()) && training2.getName().equals(training.getName()) && training2.getSportObject().equals(training.getSportObject()) && training2.getDuration() == training.getDuration() && training.getTrainingType().equals(training2.getTrainingType())){
				return false;
			}
		}
		
		boolean added = trainings.add(training);
		FileWriter fileWriter = new FileWriter(fileLocation);
		gson.toJson(trainings, fileWriter);
		fileWriter.close();
		return added;
	}
	
	public String editTraining(Training training, Training selectedTraining) throws IOException {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		Type listType = new TypeToken<List<Training>>(){}.getType();
		FileReader fileReader = new FileReader(fileLocation);
		List<Training> trainings = gson.fromJson(fileReader, listType);
		fileReader.close();
		
		for (Training training2 : trainings) {
			if(training2.getCoach().equals(training.getCoach()) && training2.getName().equals(training.getName()) && training2.getSportObject().equals(training.getSportObject()) && training2.getDuration() == training.getDuration() && training.getTrainingType().equals(training2.getTrainingType())){
				return "Takav trening vec postoji u ovom objektu!";
			}
		}
		
		for (Training t : trainings) {
			if(t.getName().equals(selectedTraining.getName()) && t.getSportObject().equals(selectedTraining.getSportObject())) {
				t.setCoach(training.getCoach());
				t.setDescription(training.getDescription());
				t.setDuration(training.getDuration());
				t.setImageLocation(training.getImageLocation());
				t.setName(training.getName());
				t.setTrainingType(training.getTrainingType());
				FileWriter fileWriter = new FileWriter(fileLocation);
				gson.toJson(trainings, fileWriter);
				fileWriter.close();
				return "Uspesno izmenjeno";
			}
		}
		return "Ne postoji izabrani membership u listi membershipa";
	}
	
	public boolean deleteTraining(Training t) throws IOException {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		Type listType = new TypeToken<List<Training>>(){}.getType();
		FileReader fileReader = new FileReader(fileLocation);
		List<Training> trainings = gson.fromJson(fileReader, listType);
		fileReader.close();
		for (Training training : trainings) {
			if(training.equals(t)) {
				training.setDeleted(true);
				FileWriter fileWriter = new FileWriter(fileLocation);
				gson.toJson(trainings, fileWriter);
				fileWriter.close();
				return true;
			}
		}
		return false;
	}
	
	public Training findTraining(SportObject sportObject, String name, Coach coach, TrainingType trainingType, long duration) throws IOException {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		Type listType = new TypeToken<List<Training>>(){}.getType();
		FileReader fileReader = new FileReader(fileLocation);
		List<Training> trainings = gson.fromJson(fileReader, listType);
		fileReader.close();
		for (Training training : trainings) {
			if(training.getSportObject().equals(sportObject) && training.getName().equals(name) && training.getCoach().equals(coach) && training.getTrainingType().equals(trainingType) && training.getDuration() == duration) {
				return training;
			}
		}
		return null;
	}
	
	public List<Training> findAllTraining() throws IOException{
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		Type listType = new TypeToken<List<Training>>(){}.getType();
		FileReader fileReader = new FileReader(fileLocation);
		List<Training> trainings = gson.fromJson(fileReader, listType);
		fileReader.close();
		return trainings;
	}
}
