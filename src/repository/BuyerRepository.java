package repository;

import java.util.List;

import model.Buyer;

public class BuyerRepository {
	private List<Buyer> buyers;

	public BuyerRepository() {
		super();
		
	}
	
	public boolean addBuyer(Buyer buyer) {
		for (Buyer b : buyers) {
			if(buyer.getUserName().equalsIgnoreCase(b.getUserName())) {
				return false;
			}
		}
		buyers.add(buyer);
		return true;
	}
	
	public boolean editBuyer(Buyer buyer, Buyer selectedBuyer) {
		for (Buyer buyer2 : buyers) {
			if(buyer.getUserName().equalsIgnoreCase(selectedBuyer.getUserName())) {
				break;
			}
			
			if(buyer.getUserName().equalsIgnoreCase(buyer2.getUserName())) {
				return false;
			}
		}
		
		for (Buyer b : buyers) {
			if(b.getUserName().equalsIgnoreCase(selectedBuyer.getUserName())) {
				b.setUserName(buyer.getUserName());
				b.setPassword(buyer.getPassword());
				b.setName(buyer.getName());
				b.setSurname(buyer.getSurname());
				b.setGender(buyer.getGender());
				b.setBirthday(buyer.getBirthday());
				return true;
			}
		}
		return false;
	}
	
	public boolean deleteBuyer(Buyer buyer) {
		for (Buyer b : buyers) {
			if(buyer.getUserName().equalsIgnoreCase(b.getUserName())) {}
			b.setDeleted(true);
			return true;
		}
		return false;
	}
	
	public Buyer findBuyer(String username) {
		for (Buyer buyer : buyers) {
			if(buyer.getUserName().equalsIgnoreCase(username)) {
				return buyer;
			}
		}
		return null;
	}
	
	public List<Buyer> findAllBuyers(){
		return buyers;
	}
	
}
