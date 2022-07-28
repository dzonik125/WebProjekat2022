package repository;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import model.Administrator;
import model.Gender;
import model.UserType;

public class AdministratorRepository {

	private List<Administrator> administrators;
	
	public AdministratorRepository() throws JsonIOException, JsonSyntaxException, FileNotFoundException, ParseException {
		super();
		administrators = new ArrayList<Administrator>();
		File input = new File("./data/administrators.json");
		JsonParser jParser = new JsonParser();
		JsonElement fileElement = jParser.parse(new FileReader(input));
		JsonArray fileObject = fileElement.getAsJsonArray();
		
		for (JsonElement jsonElement : fileObject) {
			JsonObject admJsonObject = jsonElement.getAsJsonObject();
			
			String userName = admJsonObject.get("userName").getAsString();
			String password = admJsonObject.get("password").getAsString();
			String name = admJsonObject.get("name").getAsString();
			String surname = admJsonObject.get("surname").getAsString();
			Gender gender = Gender.valueOf(admJsonObject.get("gender").getAsString());
			SimpleDateFormat format = new SimpleDateFormat("MMM d, yyyy, HH:mm:ss aaa");
			Date birthday = format.parse(admJsonObject.get("birthday").getAsString());
			UserType userType = UserType.valueOf(admJsonObject.get("userType").getAsString());
			
			Administrator admin = new Administrator(userName, password, name, surname, gender, birthday, userType);
			
			administrators.add(admin);
		}
	}

	public void addAdmin(Administrator administrator) {
		administrators.add(administrator);
	}
	
	public void deleteAdmin(String username) {
		for (Administrator administrator : administrators) {
			if(administrator.getUserName().equalsIgnoreCase(username)) {
				administrator.setDeleted(true);
				return;
			}
		}
	}
	
	public void editAdministrator(Administrator administrator) {
		for (Administrator admin : administrators) {
			if(admin.getUserName().equalsIgnoreCase(administrator.getUserName())) {
				admin.setPassword(administrator.getPassword());
				admin.setName(administrator.getName());
				admin.setSurname(administrator.getSurname());
				admin.setGender(administrator.getGender());
				admin.setBirthday(administrator.getBirthday());
				return;
			}
		}
	}
	
	public Administrator findAdministrator(String username) {
		for (Administrator administrator : administrators) {
			if(administrator.getUserName().equalsIgnoreCase(username)) {
				return administrator;
			}
		}
		return null;
	}
	
	public List<Administrator> findAllAdministrators(){
		return administrators;
	}
	
	
}
