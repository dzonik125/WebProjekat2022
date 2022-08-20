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

	
	
}
