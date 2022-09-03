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

import model.Manager;

public class ManagerRepository {

	private String fileLocation = "./data/managers.json";

	public ManagerRepository() {
		super();
	}
	
	public boolean addManager(Manager manager) throws IOException {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		Type listType = new TypeToken<List<Manager>>(){}.getType();
		FileReader fileReader = new FileReader(fileLocation);
		List<Manager> managers = gson.fromJson(fileReader, listType);
		fileReader.close();
		if(managers == null) {
			managers = new ArrayList<>();
		}
		boolean added = managers.add(manager);
		FileWriter fileWriter = new FileWriter(fileLocation);
		gson.toJson(managers, fileWriter);
		fileWriter.close();
		if(added) {
			return true;
		}
		return false;
	}
	
	public String editManager(Manager manager, String selectedManager) throws IOException {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		Type listType = new TypeToken<List<Manager>>(){}.getType();
		FileReader fileReader = new FileReader(fileLocation);
		List<Manager> managers = gson.fromJson(fileReader, listType);
		fileReader.close();
		for (Manager manager2 : managers) {
			if(manager.getUserName().equalsIgnoreCase(selectedManager)) {
				break;
			}
			
			if(manager.getUserName().equalsIgnoreCase(manager2.getUserName())) {
				return "Username se ne moze promeniti u vec postojeci";
			}
		}
		
		for (Manager b : managers) {
			if(b.getUserName().equalsIgnoreCase(selectedManager)) {
				b.setUserName(manager.getUserName());
				b.setPassword(manager.getPassword());
				b.setName(manager.getName());
				b.setSurname(manager.getSurname());
				b.setGender(manager.getGender());
				b.setBirthday(manager.getBirthday());
				b.setSportObject(manager.getSportObject());
				FileWriter fileWriter = new FileWriter(fileLocation);
				gson.toJson(managers, fileWriter);
				fileWriter.close();
				return "Uspesno izmenjeno";
			}
		}
		return "Ne postoji izabrani menadzer u listi menadzera";
	}
	
	public boolean deleteManager(String manager) throws IOException {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		Type listType = new TypeToken<List<Manager>>(){}.getType();
		FileReader fileReader = new FileReader(fileLocation);
		List<Manager> managers = gson.fromJson(fileReader, listType);
		fileReader.close();
		for (Manager b : managers) {
			if(manager.equalsIgnoreCase(b.getUserName())) {
				b.setDeleted(true);
				FileWriter fileWriter = new FileWriter(fileLocation);
				gson.toJson(managers, fileWriter);
				fileWriter.close();
				return true;
			}
		}
		return false;
	}
	
	public Manager findManager(String username) throws IOException {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		Type listType = new TypeToken<List<Manager>>(){}.getType();
		FileReader fileReader = new FileReader(fileLocation);
		List<Manager> managers = gson.fromJson(fileReader, listType);
		fileReader.close();
		for (Manager manager : managers) {
			if(manager.getUserName().equalsIgnoreCase(username)) {
				return manager;
			}
		}
		return null;
	}
	
	public List<Manager> findAllManagers() throws IOException{
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		Type listType = new TypeToken<List<Manager>>(){}.getType();
		FileReader fileReader = new FileReader(fileLocation);
		List<Manager> managers = gson.fromJson(fileReader, listType);
		fileReader.close();
		return managers;
	}
}
