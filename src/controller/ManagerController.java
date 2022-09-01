package controller;

import java.io.IOException;
import java.util.List;

import model.Manager;
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
	
	public boolean deleteManager(Manager manager) throws IOException{
		return ms.deleteManager(manager);
	}
	
	public Manager findManager(String username) throws IOException{
		return ms.findManager(username);
	}
	
	public List<Manager> findAllManagers() throws IOException{
		return ms.findAllManagers();
	}
}
