package service;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import model.Coach;
import model.SportObject;
import model.Training;
import repository.CoachRepository;

public class CoachService {

	private CoachRepository cr = new CoachRepository();

	public CoachService() {
		super();
	}
	
	public boolean addCoach(Coach coach) throws IOException{
		return cr.addCoach(coach);
	}
	
	public String editCoach(Coach coach, String selectedCoach) throws IOException{
		return cr.editCoach(coach, selectedCoach);
	}
	
	public boolean deleteCoach(String coach) throws IOException{
		return cr.deleteCoach(coach);
	}
	
	public Coach findCoach(String username) throws IOException{
		return cr.findCoach(username);
	}
	
	public List<Coach> findAllCoaches() throws IOException{
		return cr.findAllCoaches();
	}
	
	public void appointTrainingToCoach(Training training, String name, String surname) throws IOException {
		List<Coach> coaches = cr.findAllCoaches();
		for (Coach coach : coaches) {
			if(coach.getName().equals(name) && coach.getSurname().equals(surname)) {
				List<Training> trainings = new ArrayList<>();
				if(coach.getNotCompletedTrainings() != null) {
					trainings = coach.getNotCompletedTrainings();
				}
				trainings.add(training);
				coach.setNotCompletedTrainings(trainings);
			}
		}
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		FileWriter fileWriter = new FileWriter("./data/coaches.json");
		gson.toJson(coaches, fileWriter);
		fileWriter.close();
	}
	
	public List<Training> getScheduledTrainingsForCoach(String username) throws IOException {
		List<Training> toRet = new ArrayList<Training>();
		Calendar calendar = Calendar.getInstance();
		Date now = calendar.getTime();
		for (Coach coach : cr.findAllCoaches()) {
			if(coach.getUserName().equals(username)) {
				if(coach.getNotCompletedTrainings() == null) {
					return null;
				}
				for (Training training : coach.getNotCompletedTrainings()) {
					if((!training.getAppointmentDate().before(now)) && !training.isDeleted()) {
						toRet.add(training);
					}
				}
			}
		}
		return toRet;
	}
	
	public boolean deleteAppointedTraining (String username, String name, Date date) throws IOException {
		List<Coach> coaches = findAllCoaches();
		for (Coach coach : coaches) {
			if(coach.getUserName().equals(username)) {
				List<Training> notCompleted = coach.getNotCompletedTrainings();
				for (Training training : notCompleted) {
					if(training.getAppointmentDate().equals(date) && training.getName().equals(name)) {
						training.setDeleted(true);
					}
				}
				coach.setNotCompletedTrainings(notCompleted);
				Gson gson = new GsonBuilder().setPrettyPrinting().create();
				FileWriter fileWriter = new FileWriter("./data/coaches.json");
				gson.toJson(coaches, fileWriter);
				fileWriter.close();
				return true;
			}
		}
		return false;
	}
	
	public void deleteSportObjectFromCoachTrainings (String object) throws IOException {
		List<Coach> coaches = findAllCoaches();
		for (Coach coach : coaches) {
			List<Training> notCompleted = new ArrayList<>();
			if(coach.getNotCompletedTrainings() != null) {
				notCompleted = coach.getNotCompletedTrainings();
			}
			for (Training training : notCompleted) {
				if(training.getSportObject().getName().equals(object)) {
					SportObject so = training.getSportObject();
					so.setDeleted(true);
					training.setSportObject(so);
				}
			}
		}
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		FileWriter fileWriter = new FileWriter("./data/coaches.json");
		gson.toJson(coaches, fileWriter);
		fileWriter.close();
	}
	
}
