package service;

import java.io.FileNotFoundException;
import java.io.IOException;

import model.Administrator;
import repository.AdministratorRepository;

public class AdministratorService {
	
	private AdministratorRepository ar = new AdministratorRepository();

	public AdministratorService() {
		super();
	}
	
	public void addAdmin(Administrator administrator) throws IOException{
		ar.addAdmin(administrator);
	}
	
	public Administrator findAdministrator(String username) throws FileNotFoundException{
		return ar.findAdministrator(username);
	}
	
	public String editAdministrator(Administrator administrator, String selectedAdministrator) throws IOException{
		return ar.editAdministrator(administrator, selectedAdministrator);
	}

	public boolean deleteAdministrator(String username) throws IOException {
		return ar.deleteAdministrator(username);
	}
	
}
