package service;

import java.io.IOException;
import java.util.List;

import model.Coach;
import repository.CoachRepository;

public class CoachService {

	private CoachRepository cr = new CoachRepository();

	public CoachService() {
		super();
	}
	
	public boolean addCoach(Coach coach) throws IOException{
		return cr.addCoach(coach);
	}
	
	public String editCoach(Coach coach, Coach selectedCoach) throws IOException{
		return cr.editCoach(coach, selectedCoach);
	}
	
	public boolean deleteCoach(Coach coach) throws IOException{
		return cr.deleteCoach(coach);
	}
	
	public Coach findCoach(String username) throws IOException{
		return cr.findCoach(username);
	}
	
	public List<Coach> findAllCoaches() throws IOException{
		return cr.findAllCoaches();
	}
	
	
}
