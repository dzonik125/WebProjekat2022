package service;

import java.io.IOException;
import java.util.List;

import model.Manager;
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
}
