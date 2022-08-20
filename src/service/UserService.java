package service;

import java.io.IOException;
import java.util.List;

import model.User;
import repository.UserRepository;

public class UserService {

	private UserRepository userRepository = new UserRepository();

	public UserService() {
		super();
	}
	
	public boolean addUser(User user) throws IOException {
		return userRepository.addUser(user);
	} 
	
	public List<User> findAllUsers() throws IOException {
		return userRepository.findAllUsers();
	}
	
	public User findUser(String username) throws IOException {
		return userRepository.findUser(username);
	}
	
	
}
