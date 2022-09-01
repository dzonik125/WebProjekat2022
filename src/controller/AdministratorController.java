package controller;

import java.io.FileNotFoundException;
import java.io.IOException;

import model.Administrator;
import service.AdministratorService;

public class AdministratorController {

	private AdministratorService as = new AdministratorService();

	public AdministratorController() {
		super();
	}
	
	public Administrator findAdministrator(String username) throws FileNotFoundException{
		return as.findAdministrator(username);
	}
	
	
	public String editAdministrator(Administrator administrator, String selectedAdministrator) throws IOException{
		return as.editAdministrator(administrator, selectedAdministrator);
	}
	
	
}
