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
import model.Training;

public class CoachRepository {
	private String fileLocation = "./data/coaches.json";

	public CoachRepository() {
		super();
	}
	
	public boolean addCoach(Coach coach) throws IOException {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		Type listType = new TypeToken<List<Coach>>(){}.getType();
		FileReader fileReader = new FileReader(fileLocation);
		List<Coach> coaches = gson.fromJson(fileReader, listType);
		fileReader.close();
		if(coaches == null) {
			coaches = new ArrayList<>();
		}
		for (Coach coach2 : coaches) {
			if(coach2.getUserName().equals(coach.getUserName())) {
				return false;
			}
		}
		boolean added = coaches.add(coach);
		FileWriter fileWriter = new FileWriter(fileLocation);
		gson.toJson(coaches, fileWriter);
		fileWriter.close();
		if(added) {
			return true;
		}
		return false;
	}
	
	public String editCoach(Coach coach, String selectedCoach) throws IOException {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		Type listType = new TypeToken<List<Coach>>(){}.getType();
		FileReader fileReader = new FileReader(fileLocation);
		List<Coach> coaches = gson.fromJson(fileReader, listType);
		fileReader.close();
		for (Coach coach2 : coaches) {
			if(coach.getUserName().equalsIgnoreCase(selectedCoach)) {
				break;
			}
			
			if(coach.getUserName().equalsIgnoreCase(coach2.getUserName())) {
				return "ID se ne moze promeniti u vec postojeci";
			}
		}
		
		for (Coach c : coaches) {
			if(c.getUserName().equalsIgnoreCase(selectedCoach)) {
				c.setUserName(coach.getUserName());
				c.setPassword(coach.getPassword());
				c.setName(coach.getName());
				c.setSurname(coach.getSurname());
				c.setGender(coach.getGender());
				c.setBirthday(coach.getBirthday());
				FileWriter fileWriter = new FileWriter(fileLocation);
				gson.toJson(coaches, fileWriter);
				fileWriter.close();
				return "Uspesno izmenjeno";
			}
		}
		return "Ne postoji izabrani membership u listi membershipa";
	}
	
	public boolean deleteCoach(String coach) throws IOException {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		Type listType = new TypeToken<List<Coach>>(){}.getType();
		FileReader fileReader = new FileReader(fileLocation);
		List<Coach> coaches = gson.fromJson(fileReader, listType);
		fileReader.close();
		for (Coach c : coaches) {
			if(coach.equalsIgnoreCase(c.getUserName())) {
				c.setDeleted(true);
				FileWriter fileWriter = new FileWriter(fileLocation);
				gson.toJson(coaches, fileWriter);
				fileWriter.close();
				return true;
			}
		}
		return false;
	}
	
	public Coach findCoach(String username) throws IOException {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		Type listType = new TypeToken<List<Coach>>(){}.getType();
		FileReader fileReader = new FileReader(fileLocation);
		List<Coach> coaches = gson.fromJson(fileReader, listType);
		fileReader.close();
		for (Coach coach : coaches) {
			if(coach.getUserName().equalsIgnoreCase(username)) {
				return coach;
			}
		}
		return null;
	}
	
	public List<Coach> findAllCoaches() throws IOException{
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		Type listType = new TypeToken<List<Coach>>(){}.getType();
		FileReader fileReader = new FileReader(fileLocation);
		List<Coach> coaches = gson.fromJson(fileReader, listType);
		fileReader.close();
		return coaches;
	}
}
