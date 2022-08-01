package controller;

import java.io.IOException;
import java.util.List;

import model.Coach;
import service.CoachService;

public class CoachController {

	private CoachService cs = new CoachService();

	public CoachController() {
		super();
	}
	
	public boolean addCoach(Coach coach) throws IOException{
		return cs.addCoach(coach);
	}
	
	public String editCoach(Coach coach, Coach selectedCoach) throws IOException{
		return cs.editCoach(coach, selectedCoach);
	}
	
	public boolean deleteCoach(Coach coach) throws IOException{
		return cs.deleteCoach(coach);
	}
	
	public Coach findCoach(String username) throws IOException{
		return cs.findCoach(username);
	}
	
	public List<Coach> findAllCoaches() throws IOException{
		return cs.findAllCoaches();
	}
}