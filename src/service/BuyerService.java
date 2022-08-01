package service;

import java.io.IOException;
import java.util.List;

import model.Buyer;
import repository.BuyerRepository;

public class BuyerService {

	private BuyerRepository br = new BuyerRepository();
		
	public BuyerService() {
		super();
	}

	public boolean addBuyer(Buyer buyer) throws IOException {
		return br.addBuyer(buyer);
	}
	
	public String editBuyer(Buyer buyer, Buyer selectedBuyer) throws IOException{
		return br.editBuyer(buyer, selectedBuyer);
	}
	
	public boolean deleteBuyer(Buyer buyer) throws IOException {
		return br.deleteBuyer(buyer);
	}
	
	public Buyer findBuyer(String username) throws IOException{
		return br.findBuyer(username);
	}
	
	public List<Buyer> findAllBuyers() throws IOException{
		return br.findAllBuyers();
	}
}
