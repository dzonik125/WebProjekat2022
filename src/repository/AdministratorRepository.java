package repository;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import model.Administrator;

public class AdministratorRepository {
	
	public AdministratorRepository() throws JsonIOException, JsonSyntaxException, FileNotFoundException, ParseException {
		super();
//		administrators = new ArrayList<Administrator>();
//		File input = new File("./data/administrators.json");
//		JsonParser jParser = new JsonParser();
//		JsonElement fileElement = jParser.parse(new FileReader(input));
//		JsonArray fileObject = fileElement.getAsJsonArray();
//		
//		for (JsonElement jsonElement : fileObject) {
//			JsonObject admJsonObject = jsonElement.getAsJsonObject();
//			
//			String userName = admJsonObject.get("userName").getAsString();
//			String password = admJsonObject.get("password").getAsString();
//			String name = admJsonObject.get("name").getAsString();
//			String surname = admJsonObject.get("surname").getAsString();
//			Gender gender = Gender.valueOf(admJsonObject.get("gender").getAsString());
//			SimpleDateFormat format = new SimpleDateFormat("MMM d, yyyy, HH:mm:ss aaa");
//			Date birthday = format.parse(admJsonObject.get("birthday").getAsString());
//			UserType userType = UserType.valueOf(admJsonObject.get("userType").getAsString());
//			
//			Administrator admin = new Administrator(userName, password, name, surname, gender, birthday, userType);
//			
//			administrators.add(admin);
//		}
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
	
	public void editAdministrator(Administrator administrator) throws FileNotFoundException {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		Type listType = new TypeToken<List<Administrator>>(){}.getType();
		FileReader fileReader = new FileReader("./data/administrators.json");
		List<Administrator> admins = gson.fromJson(fileReader, listType);
		if(admins == null) {
			return;
		}
		for (Administrator admin : admins) {
			if(admin.getUserName().equalsIgnoreCase(administrator.getUserName())) {
				admin.setUserName(administrator.getUserName());
				admin.setPassword(administrator.getPassword());
				admin.setName(administrator.getName());
				admin.setSurname(administrator.getSurname());
				admin.setGender(administrator.getGender());
				admin.setBirthday(administrator.getBirthday());
				return;
			}
		}
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
	
	
}
