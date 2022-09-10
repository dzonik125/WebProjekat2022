package service;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import model.Buyer;
import model.Coach;
import model.SportObject;
import model.Training;
import model.TrainingHistory;
import model.TrainingType;
import repository.TrainingRepository;

public class TrainingService {

	private TrainingRepository tr = new TrainingRepository();
	private BuyerService bs = new BuyerService();
	private CoachService cs = new CoachService();
	private TrainingHistoryService ths = new TrainingHistoryService();

	public TrainingService() {
		super();
	}
	
	public boolean addTraining(Training training) throws IOException{
		return tr.addTraining(training);
	}
	
	public String editTraining(Training training, Training selectedTraining) throws IOException{
		return tr.editTraining(training, selectedTraining);
	}
	
	public boolean deleteTraining(Training t) throws IOException{
		return tr.deleteTraining(t);
	}
	
	public Training findTraining(int id) throws IOException{
		return tr.findTraining(id);
	}
	
	public List<Training> findAllTraining() throws IOException{
		return tr.findAllTraining();
	}
	
	public List<Training> findTrainingsForSportObject(String name) throws IOException{
		List<Training> toRet = new ArrayList<>();
		for (Training training : findAllTraining()) {
			if(training.getSportObject().getName().equals(name)) {
				toRet.add(training);
			}
		}
		return toRet;
	}
	
	public List<Training> getGroupTrainingsForCoach (String username) throws IOException {
		List<Training> toRet = new ArrayList<Training>();
		for (Training training : tr.findAllTraining()) {
			if(training.getCoach() == null) {
				continue;
			}
			if(training.getCoach().equals(username) && training.getTrainingType().toString().equals("GROUP")) {
				toRet.add(training);
			}
		}
		return toRet;
	}
	
	public void checkCompletedPersonal () throws IOException{
		List<Coach> coaches = cs.findAllCoaches();
		Calendar calendar = Calendar.getInstance();
		Date now = calendar.getTime();
		for (Coach coach : coaches) {
			List<Training> schedTrg = new ArrayList<Training>();
			if(coach.getNotCompletedTrainings() != null) {
				schedTrg = coach.getNotCompletedTrainings();
			}
			for (Training training : schedTrg) {
				if(training.getAppointmentDate().before(now) && !training.isDeleted()) {
					training.setDeleted(true);
				}
			}
 		}
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		FileWriter fileWriter = new FileWriter("./data/coaches.json");
		gson.toJson(coaches, fileWriter);
		fileWriter.close();
		List<Buyer> buyers = bs.findAllBuyers();
		for (Buyer buyer : buyers) {
			List<Training> buyerSchedTrgs = new ArrayList<>();
			if(buyer.getScheduledTrainings() != null) {
				buyerSchedTrgs = buyer.getScheduledTrainings();
			}
			for (Training training : buyerSchedTrgs) {
				if(training.getAppointmentDate().before(now) && !training.isDeleted()) {
					TrainingHistory trainingHistory = new TrainingHistory(training.getAppointmentDate(), training, buyer.getUserName());
					ths.addTrainingHistory(trainingHistory);
					training.setDeleted(true);
				}
			}
		}
        fileWriter = new FileWriter("./data/buyers.json");
		gson.toJson(buyers, fileWriter);
		fileWriter.close();
	}
	
	public void deleteSportObjectsFromTrainings (String name) throws IOException {
		List<Training> trainings = findAllTraining();
		for (Training training : trainings) {
			if(training.getSportObject().getName().equals(name)) {
				SportObject so = training.getSportObject();
				so.setDeleted(true);				
				training.setSportObject(so);
			}
		}
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		FileWriter fileWriter = new FileWriter("./data/trainings.json");
		gson.toJson(trainings, fileWriter);
		fileWriter.close();
	}
	
	public void deleteCoachesTrainings (String coach) throws IOException {
		List<Training> trainings = findAllTraining();
		for (Training training : trainings) {
			if(training.getCoach() == null) {
				continue;
			}
			if(training.getCoach().equals(coach)) {
				training.setDeleted(true);
			}
		}
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		FileWriter fileWriter = new FileWriter("./data/trainings.json");
		gson.toJson(trainings, fileWriter);
		fileWriter.close();
		
	}
	
}
