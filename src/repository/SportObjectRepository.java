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

import model.Location;
import model.SportObject;

public class SportObjectRepository {

	private String fileLocation = "./data/sportObjects.json";

	public SportObjectRepository() {
		super();
	}
	
	public boolean addSportObject(SportObject sportObject) throws IOException {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		Type listType = new TypeToken<List<SportObject>>(){}.getType();
		FileReader fileReader = new FileReader(fileLocation);
		List<SportObject> sportObjects = gson.fromJson(fileReader, listType);
		fileReader.close();
		if(sportObjects == null) {
			sportObjects = new ArrayList<>();
		}
		boolean added = sportObjects.add(sportObject);
		FileWriter fileWriter = new FileWriter(fileLocation);
		gson.toJson(sportObjects, fileWriter);
		fileWriter.close();
		return added;
	}
	
	public String editSportObject(SportObject sportObject, SportObject selectedSportObject) throws IOException {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		Type listType = new TypeToken<List<SportObject>>(){}.getType();
		FileReader fileReader = new FileReader(fileLocation);
		List<SportObject> sportObjects = gson.fromJson(fileReader, listType);
		fileReader.close();
		for (SportObject sportObject2 : sportObjects) {
			if(sportObject.getLocation().getLatitude() == selectedSportObject.getLocation().getLatitude() && sportObject.getLocation().getLongtitude() == selectedSportObject.getLocation().getLongtitude()) {
				break;
			}
			
			if(sportObject.getLocation().getLatitude() == sportObject2.getLocation().getLatitude() && sportObject.getLocation().getLongtitude() == sportObject2.getLocation().getLongtitude()) {
				return "Ne mogu postojati dva sportska objekta na identicnoj lokaciji!";
			}
		}
		
		for (SportObject so : sportObjects) {
			if(so.getLocation().getLatitude() == selectedSportObject.getLocation().getLatitude() && so.getLocation().getLongtitude() == selectedSportObject.getLocation().getLongtitude()) {
				so.setName(sportObject.getName());
				so.setImageRelLocation(sportObject.getImageRelLocation());
				so.setAvgGrade(sportObject.getAvgGrade());
				so.setLocation(sportObject.getLocation());
				so.setObjectStatus(sportObject.getObjectStatus());
				so.setObjectType(so.getObjectType());
				so.setServices(sportObject.getServices());
				so.setWorkingHours(sportObject.getWorkingHours());
				FileWriter fileWriter = new FileWriter(fileLocation);
				gson.toJson(sportObjects, fileWriter);
				fileWriter.close();
				return "Uspesno izmenjeno";
			}
		}
		return "Ne postoji izabrani objekat u listi objekata!";
	}
	
	public boolean deleteSportObject(String name) throws IOException {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		Type listType = new TypeToken<List<SportObject>>(){}.getType();
		FileReader fileReader = new FileReader(fileLocation);
		List<SportObject> sportObjects = gson.fromJson(fileReader, listType);
		fileReader.close();
		for (SportObject sportObject : sportObjects) {
			if(sportObject.getName().equals(name)) {
				sportObject.setDeleted(true);
				FileWriter fileWriter = new FileWriter(fileLocation);
				gson.toJson(sportObjects, fileWriter);
				fileWriter.close();
				return true;
			}
		}
		return false;
	}
	
	public SportObject findSportObject(String name) throws IOException {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		Type listType = new TypeToken<List<SportObject>>(){}.getType();
		FileReader fileReader = new FileReader(fileLocation);
		List<SportObject> sportObjects = gson.fromJson(fileReader, listType);
		fileReader.close();
		for (SportObject sportObject : sportObjects) {
			if(sportObject.getName().equals(name)) {
				return sportObject;
			}
		}
		
		return null;
	}
	
	public List<SportObject> findAllSportObjects() throws IOException{
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		Type listType = new TypeToken<List<SportObject>>(){}.getType();
		FileReader fileReader = new FileReader(fileLocation);
		List<SportObject> sportObjects = gson.fromJson(fileReader, listType);
		List<SportObject> found = new ArrayList<>();
		for (SportObject sportObject : sportObjects) {
			if(sportObject.isDeleted()) {
				found.add(sportObject);
			}
		}
		sportObjects.removeAll(found);
		fileReader.close();
		return sportObjects;
	}
	
}
