package controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import model.Buyer;
import model.Training;
import service.BuyerService;

public class BuyerController {

	private BuyerService bs = new BuyerService();

	public BuyerController() {
		super();
	}
	
	public boolean addBuyer(Buyer buyer) throws IOException {
		return bs.addBuyer(buyer);
	}
	
	public String editBuyer(Buyer buyer, String selectedBuyer) throws IOException{
		return bs.editBuyer(buyer, selectedBuyer);
	}
	
	public boolean deleteBuyer(String buyer) throws IOException {
		return bs.deleteBuyer(buyer);
	}
	
	public Buyer findBuyer(String username) throws IOException{
		return bs.findBuyer(username);
	}
	
	public List<Buyer> findAllBuyers() throws IOException{
		return bs.findAllBuyers();
	}
	
	public void addObjectToBuyerVisited(String username, String object) throws IOException {
		bs.addObjectToBuyerVisited(username, object);
	}
	
	public List<Buyer> findBuyersVisitedSportObject (String sportObject) throws IOException {
		return bs.findBuyersVisitedSportObject(sportObject);
	}
	
	public void appointTrainingToBuyer (String b, Training t) throws IOException {
		bs.appointTrainingToBuyer(b, t);
	}
	
	public List<Training> getBuyerAppointedTrainings (String username) throws IOException {
		return bs.getBuyerAppointedTrainings(username);
	}
	
	public void deleteBuyerAppointedTrainings (String coachUsername, String name, Date dt) throws IOException {
		bs.deleteBuyerAppointedTrainings(coachUsername, name, dt);
	}
	
	public void deleteSportObjectFromBuyersTrainings(String name) throws IOException {
		bs.deleteSportObjectFromBuyersTrainings(name);
	}
}
