package service;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import model.Coach;
import model.Training;
import repository.CoachRepository;

public class CoachService {

	private CoachRepository cr = new CoachRepository();

	public CoachService() {
		super();
	}
	
	public boolean addCoach(Coach coach) throws IOException{
		return cr.addCoach(coach);
	}
	
	public String editCoach(Coach coach, String selectedCoach) throws IOException{
		return cr.editCoach(coach, selectedCoach);
	}
	
	public boolean deleteCoach(String coach) throws IOException{
		return cr.deleteCoach(coach);
	}
	
	public Coach findCoach(String username) throws IOException{
		return cr.findCoach(username);
	}
	
	public List<Coach> findAllCoaches() throws IOException{
		return cr.findAllCoaches();
	}
	
	public void appointTrainingToCoach(Training training, String name, String surname) throws IOException {
		List<Coach> coaches = cr.findAllCoaches();
		for (Coach coach : coaches) {
			if(coach.getName().equals(name) && coach.getSurname().equals(surname)) {
				List<Training> trainings = new ArrayList<>();
				if(coach.getNotCompletedTrainings() != null) {
					trainings = coach.getNotCompletedTrainings();
				}
				trainings.add(training);
				coach.setNotCompletedTrainings(trainings);
			}
		}
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		FileWriter fileWriter = new FileWriter("./data/coaches.json");
		gson.toJson(coaches, fileWriter);
		fileWriter.close();
	}
	
}
