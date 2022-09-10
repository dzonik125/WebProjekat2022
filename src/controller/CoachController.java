package controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import model.Coach;
import model.Training;
import service.CoachService;

public class CoachController {

	private CoachService cs = new CoachService();

	public CoachController() {
		super();
	}
	
	public boolean addCoach(Coach coach) throws IOException{
		return cs.addCoach(coach);
	}
	
	public String editCoach(Coach coach, String selectedCoach) throws IOException{
		return cs.editCoach(coach, selectedCoach);
	}
	
	public boolean deleteCoach(String coach) throws IOException{
		return cs.deleteCoach(coach);
	}
	
	public Coach findCoach(String username) throws IOException{
		return cs.findCoach(username);
	}
	
	public List<Coach> findAllCoaches() throws IOException{
		return cs.findAllCoaches();
	}
	
	public void appointTrainingToCoach(Training training, String name, String surname) throws IOException {
		cs.appointTrainingToCoach(training, name, surname);
	}
	
	public List<Training> getScheduledTrainingsForCoach(String username) throws IOException {
		return cs.getScheduledTrainingsForCoach(username);
	}
	
	public boolean deleteAppointedTraining (String username, String name, Date date) throws IOException {
		return cs.deleteAppointedTraining(username, name, date);
	}
	
	public void deleteSportObjectFromCoachTrainings (String object) throws IOException {
		cs.deleteSportObjectFromCoachTrainings(object);
	}
}
