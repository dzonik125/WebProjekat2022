package controller;

import java.io.IOException;
import java.util.List;

import model.Manager;
import model.SportObject;
import service.ManagerService;

public class ManagerController {

	private ManagerService ms = new ManagerService();

	public ManagerController() {
		super();
	}
	
	public boolean addManager(Manager manager) throws IOException{
		return ms.addManager(manager);
	}
	
	public String editManager(Manager manager, String selectedManager) throws IOException{
		return ms.editManager(manager, selectedManager);
	}
	
	public boolean deleteManager(String manager) throws IOException{
		return ms.deleteManager(manager);
	}
	
	public Manager findManager(String username) throws IOException{
		return ms.findManager(username);
	}
	
	public List<Manager> findAllManagers() throws IOException{
		return ms.findAllManagers();
	}
	
	public List<Manager> findFreeManagers() throws IOException{
		return ms.findFreeManagers();
	}
	
	public boolean bindManagerWithSportObject(String username, SportObject so) throws IOException {
		return ms.bindManagerWithSportObject(username, so);
	}
	
	public SportObject getManagerSportObject(String username) throws IOException{
		return ms.getManagerSportObject(username);
	}
	
	public void deleteManagerSportObject(String name) throws IOException {
		ms.deleteManagerSportObject(name);
	}
}
