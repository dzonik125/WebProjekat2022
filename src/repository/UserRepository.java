package repository;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import model.User;

public class UserRepository {

	public UserRepository() {
		super();
	}

	private String fileLocation = "./data/users.json";
	
	public boolean addUser(User user) throws IOException {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		Type listType = new TypeToken<List<User>>(){}.getType();
		FileReader fileReader = new FileReader(fileLocation);
		List<User> users = gson.fromJson(fileReader, listType);
		fileReader.close();
		if(users == null) {
			users = new ArrayList<>();
		}
		for (User user2 : users) {
			if(user2.getUserName().equals(user.getUserName())) {
				return false;
			}
		}
		boolean added = users.add(user);
		FileWriter fileWriter = new FileWriter(fileLocation);
		gson.toJson(users, fileWriter);
		fileWriter.close();
		return added;
	}
	
	public List<User> findAllUsers() throws IOException{
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		Type listType = new TypeToken<List<User>>(){}.getType();
		FileReader fileReader = new FileReader(fileLocation);
		List<User> users = gson.fromJson(fileReader, listType);
		fileReader.close();
		return users;
	}
	
	public User findUser(String username) throws IOException {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		Type listType = new TypeToken<List<User>>(){}.getType();
		FileReader fileReader = new FileReader(fileLocation);
		List<User> users = gson.fromJson(fileReader, listType);
		for (User user : users) {
			if(user.getUserName().equals(username)) {
				return user;
			}
		}
		fileReader.close();
		return null;
	}
	
}
