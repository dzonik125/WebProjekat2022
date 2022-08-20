package controller;

import java.io.IOException;
import java.util.List;

import model.User;
import service.UserService;

public class UserController {

	private UserService userService = new UserService();

	public UserController() {
		super();
	}
	
	public boolean addUser(User user) throws IOException {
		return userService.addUser(user);
	}
	
	public List<User> findAllUsers() throws IOException {
		return userService.findAllUsers();
	}
	
	public User findUser(String username) throws IOException {
		return userService.findUser(username);
	}
}
