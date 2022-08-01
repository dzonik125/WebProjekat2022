package controller;

import java.io.IOException;
import java.util.List;

import model.Buyer;
import service.BuyerService;

public class BuyerController {

	private BuyerService bs = new BuyerService();

	public BuyerController() {
		super();
	}
	
	public boolean addBuyer(Buyer buyer) throws IOException {
		return bs.addBuyer(buyer);
	}
	
	public String editBuyer(Buyer buyer, Buyer selectedBuyer) throws IOException{
		return bs.editBuyer(buyer, selectedBuyer);
	}
	
	public boolean deleteBuyer(Buyer buyer) throws IOException {
		return bs.deleteBuyer(buyer);
	}
	
	public Buyer findBuyer(String username) throws IOException{
		return bs.findBuyer(username);
	}
	
	public List<Buyer> findAllBuyers() throws IOException{
		return bs.findAllBuyers();
	}
}
