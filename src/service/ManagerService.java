package service;

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
import model.SportObject;
import repository.ManagerRepository;

public class ManagerService {

	private ManagerRepository mr = new ManagerRepository();

	public ManagerService() {
		super();
	}
	
	public boolean addManager(Manager manager) throws IOException{
		return mr.addManager(manager);
	}
	
	public String editManager(Manager manager, String selectedManager) throws IOException{
		return mr.editManager(manager, selectedManager);
	}
	
	public boolean deleteManager(String manager) throws IOException{
		return mr.deleteManager(manager);
	}
	
	public Manager findManager(String username) throws IOException{
		return mr.findManager(username);
	}
	
	public List<Manager> findAllManagers() throws IOException{
		return mr.findAllManagers();
	}
	
	public List<Manager> findFreeManagers() throws IOException{
		List<Manager> toRet = new ArrayList<>();
		for (Manager manager : mr.findAllManagers()) {
			if(manager.getSportObject() == null) {
				toRet.add(manager);
			}
		}
		return toRet;
	}
	
	public boolean bindManagerWithSportObject(String username, SportObject so) throws IOException {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		Type listType = new TypeToken<List<Manager>>(){}.getType();
		FileReader fileReader = new FileReader("./data/managers.json");
		List<Manager> managers = gson.fromJson(fileReader, listType);
		fileReader.close();
		for (Manager manager : managers) {
			if(manager.getUserName().equals(username)) {
				manager.setSportObject(so);
				FileWriter fileWriter = new FileWriter("./data/managers.json");
				gson.toJson(managers, fileWriter);
				fileWriter.close();
				return true;
			}
		}
		return false;
	}
	
	public SportObject getManagerSportObject(String username) throws IOException {
		for (Manager manager : mr.findAllManagers()) {
			if(manager.getUserName().equals(username)) {
				return manager.getSportObject();
			}
		}
		return null;
	}
}
