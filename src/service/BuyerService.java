package service;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import model.BType;
import model.Buyer;
import model.BuyerType;
import model.SportObject;
import model.Training;
import repository.BuyerRepository;

public class BuyerService {

	private BuyerRepository br = new BuyerRepository();
	private SportObjectService sos = new SportObjectService();
		
	public BuyerService() {
		super();
	}

	public boolean addBuyer(Buyer buyer) throws IOException {
		return br.addBuyer(buyer);
	}
	
	public String editBuyer(Buyer buyer, String selectedBuyer) throws IOException{
		return br.editBuyer(buyer, selectedBuyer);
	}
	
	public boolean deleteBuyer(String buyer) throws IOException {
		return br.deleteBuyer(buyer);
	}
	
	public Buyer findBuyer(String username) throws IOException{
		return br.findBuyer(username);
	}
	
	public List<Buyer> findAllBuyers() throws IOException{
		return br.findAllBuyers();
	}
	
	public void addPointsToBuyer(String username, int points) throws IOException {
		List<Buyer> buyers = br.findAllBuyers();
		for (Buyer buyer : buyers) {
			if(buyer.getUserName().equals(username)) {
				if((buyer.getPoints() + points) < 0) {
					buyer.setPoints(0);
					break;
				}
				buyer.setPoints(buyer.getPoints() + points);
			}
		}
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		FileWriter fileWriter = new FileWriter("./data/buyers.json");
		gson.toJson(buyers, fileWriter);
		fileWriter.close();
	}
	
	public void changeBuyerType(String username, BType bType) throws IOException {
		List<Buyer> buyers = br.findAllBuyers();
		for (Buyer buyer : buyers) {
			if(buyer.getUserName().equals(username)) {
				BuyerType buyerType = new BuyerType(bType);
				buyer.setBuyerType(buyerType);
			}
		}
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		FileWriter fileWriter = new FileWriter("./data/buyers.json");
		gson.toJson(buyers, fileWriter);
		fileWriter.close();
	}
	
	public void addObjectToBuyerVisited(String username, String object) throws IOException {
		List<Buyer> buyers = br.findAllBuyers();
		for (Buyer buyer : buyers) {
			if(buyer.getUserName().equals(username)) {
				List<SportObject> objects = new ArrayList<SportObject>();
				if(buyer.getVisitedObjects() != null) {
					objects = buyer.getVisitedObjects();
					for (SportObject sportObject : objects) {
						if(sportObject.getName().equals(object)) {
							return;
						}
					}
				}
				SportObject so = sos.findSportObject(object);
				objects.add(so);
				buyer.setVisitedObjects(objects);
			}
		}
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		FileWriter fileWriter = new FileWriter("./data/buyers.json");
		gson.toJson(buyers, fileWriter);
		fileWriter.close();
	}
	
	public List<Buyer> findBuyersVisitedSportObject (String sportObject) throws IOException {
		List<Buyer> toRet = new ArrayList<>();
		for (Buyer buyer : findAllBuyers()) {
			if(buyer.getVisitedObjects() == null) {
				continue;
			}
			for (SportObject sport : buyer.getVisitedObjects()) {
				if(sport.getName().equals(sportObject)) {
					toRet.add(buyer);
				}
			}
		}
		return toRet;
	}
	
	public void appointTrainingToBuyer (String b, Training t) throws IOException {
		List<Buyer> buyers = findAllBuyers();
		for (Buyer buyer : buyers) {
			if(buyer.getUserName().equals(b)) {
				List<Training> scheduled = new ArrayList<>();
				if(buyer.getScheduledTrainings() != null) {
					scheduled = buyer.getScheduledTrainings();
				}
				scheduled.add(t);
				buyer.setScheduledTrainings(scheduled);
			}
		}
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		FileWriter fileWriter = new FileWriter("./data/buyers.json");
		gson.toJson(buyers, fileWriter);
		fileWriter.close();
	}
	
	public List<Training> getBuyerAppointedTrainings (String username) throws IOException {
		for (Buyer buyer : findAllBuyers()) {
			if(buyer.getUserName().equals(username)) {
				return buyer.getScheduledTrainings();
			}
		}
		return Collections.emptyList();
	}
	
	public void deleteBuyerAppointedTrainings (String coachUsername, String name, Date dt) throws IOException {
		List<Buyer> buyers = findAllBuyers();
		for (Buyer buyer : buyers) {
			List<Training> trainings = buyer.getScheduledTrainings();
			if (trainings == null) {
				continue;
			}
			for (Training training : trainings) {
				if(training.getCoach().equals(coachUsername) && training.getName().equals(name) && training.getAppointmentDate().equals(dt)) {
					training.setDeleted(true);
					Gson gson = new GsonBuilder().setPrettyPrinting().create();
					FileWriter fileWriter = new FileWriter("./data/buyers.json");
					gson.toJson(buyers, fileWriter);
					fileWriter.close();
					return;
				}
			}
		}
	}
	
	public void deleteSportObjectFromBuyersTrainings(String name) throws IOException {
		List<Buyer> buyers = findAllBuyers();
		for (Buyer buyer : buyers) {
			List<Training> trainings = new ArrayList<Training>();
			if(buyer.getScheduledTrainings() != null) {
				trainings = buyer.getScheduledTrainings();
			}
			for (Training training : trainings) {
				if(training.getSportObject().getName().equals(name)) {
					SportObject so = training.getSportObject();
					so.setDeleted(true);
					training.setSportObject(so);
				}
			}
			buyer.setScheduledTrainings(trainings);
		}
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		FileWriter fileWriter = new FileWriter("./data/buyers.json");
		gson.toJson(buyers, fileWriter);
		fileWriter.close();
	}
}
