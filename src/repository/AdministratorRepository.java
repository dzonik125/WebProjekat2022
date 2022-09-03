package repository;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import model.Administrator;
import model.User;

public class AdministratorRepository {
	
	public AdministratorRepository() {
		super();
	}

	public void addAdmin(Administrator administrator) throws IOException {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		Type listType = new TypeToken<List<Administrator>>(){}.getType();
		FileReader fileReader = new FileReader("./data/administrators.json");
		List<Administrator> admins = gson.fromJson(fileReader, listType);
		fileReader.close();
		if(admins == null) {
			admins = new ArrayList<Administrator>();
		}
		admins.add(administrator);
		FileWriter fileWriter = new FileWriter("./data/administrators.json");
		gson.toJson(admins, fileWriter);
		fileWriter.close();
	}
	
	public void deleteAdmin(String username) throws FileNotFoundException {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		Type listType = new TypeToken<List<Administrator>>(){}.getType();
		FileReader fileReader = new FileReader("./data/administrators.json");
		List<Administrator> admins = gson.fromJson(fileReader, listType);
		if(admins == null) {
			return;
		}
		for (Administrator administrator : admins) {
			if(administrator.getUserName().equalsIgnoreCase(username)) {
				administrator.setDeleted(true);
				return;
			}
		}
	}
	
	public String editAdministrator(Administrator administrator, String selectedAdministrator) throws IOException {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		Type listType = new TypeToken<List<Administrator>>(){}.getType();
		FileReader fileReader = new FileReader("./data/administrators.json");
		List<Administrator> admins = gson.fromJson(fileReader, listType);
		fileReader.close();
		for (Administrator administrator2 : admins) {
			if(administrator.getUserName().equalsIgnoreCase(selectedAdministrator)) {
				break;
			}
			
			if(administrator.getUserName().equalsIgnoreCase(administrator2.getUserName())) {
				return "Username se ne moze promeniti u vec postojeci";
			}
		}
		
		for (Administrator admin : admins) {
			if(admin.getUserName().equalsIgnoreCase(selectedAdministrator)) {
				admin.setUserName(administrator.getUserName());
				admin.setPassword(administrator.getPassword());
				admin.setName(administrator.getName());
				admin.setSurname(administrator.getSurname());
				admin.setGender(administrator.getGender());
				admin.setBirthday(administrator.getBirthday());
				FileWriter fileWriter = new FileWriter("./data/administrators.json");
				gson.toJson(admins, fileWriter);
				fileWriter.close();
				return "Uspesno izmenjeno";
			}
		}
		return "Ne postoji izabrani admin u listi administratora";
	}
	
	public Administrator findAdministrator(String username) throws FileNotFoundException {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		Type listType = new TypeToken<List<Administrator>>(){}.getType();
		FileReader fileReader = new FileReader("./data/administrators.json");
		List<Administrator> admins = gson.fromJson(fileReader, listType);
		for (Administrator administrator : admins) {
			if(administrator.getUserName().equalsIgnoreCase(username)) {
				return administrator;
			}
		}
		return null;
	}
	
	public List<Administrator> findAllAdministrators() throws FileNotFoundException{
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		Type listType = new TypeToken<List<Administrator>>(){}.getType();
		FileReader fileReader = new FileReader("./data/administrators.json");
		List<Administrator> admins = gson.fromJson(fileReader, listType);
		return admins;
	}
	
	public boolean deleteAdministrator(String username) throws IOException {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		Type listType = new TypeToken<List<Administrator>>(){}.getType();
		FileReader fileReader = new FileReader("./data/administrators.json");
		List<Administrator> admins = gson.fromJson(fileReader, listType);
		fileReader.close();
		for (Administrator a : admins) {
			if(username.equalsIgnoreCase(a.getUserName())) {
				a.setDeleted(true);
				FileWriter fileWriter = new FileWriter("./data/administrators.json");
				gson.toJson(admins, fileWriter);
				fileWriter.close();
				return true;
			}
		}
		return false;
	}
	
	
}
