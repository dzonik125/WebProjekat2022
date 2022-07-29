package repository;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import model.Buyer;

public class BuyerRepository {

	public BuyerRepository() {
		super();
		
	}
	
	public boolean addBuyer(Buyer buyer) throws IOException {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		Type listType = new TypeToken<List<Buyer>>(){}.getType();
		FileReader fileReader = new FileReader("./data/buyers.json");
		List<Buyer> buyers = gson.fromJson(fileReader, listType);
		fileReader.close();
		if(buyers == null) {
			buyers = new ArrayList<Buyer>();
		}
		boolean added = buyers.add(buyer);
		FileWriter fileWriter = new FileWriter("./data/buyers.json");
		gson.toJson(buyers, fileWriter);
		fileWriter.close();
		if(added) {
			return true;
		}
		return false;
	}
	
	public String editBuyer(Buyer buyer, Buyer selectedBuyer) throws IOException {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		Type listType = new TypeToken<List<Buyer>>(){}.getType();
		FileReader fileReader = new FileReader("./data/buyers.json");
		List<Buyer> buyers = gson.fromJson(fileReader, listType);
		fileReader.close();
		for (Buyer buyer2 : buyers) {
			if(buyer.getUserName().equalsIgnoreCase(selectedBuyer.getUserName())) {
				break;
			}
			
			if(buyer.getUserName().equalsIgnoreCase(buyer2.getUserName())) {
				return "Username se ne moze promeniti u vec postojeci";
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
				return "Uspesno izmenjeno";
			}
		}
		return "Ne postoji izabrani kupac u listi kupaca";
	}
	
	public boolean deleteBuyer(Buyer buyer) throws IOException {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		Type listType = new TypeToken<List<Buyer>>(){}.getType();
		FileReader fileReader = new FileReader("./data/buyers.json");
		List<Buyer> buyers = gson.fromJson(fileReader, listType);
		fileReader.close();
		for (Buyer b : buyers) {
			if(buyer.getUserName().equalsIgnoreCase(b.getUserName())) {}
			b.setDeleted(true);
			return true;
		}
		return false;
	}
	
	public Buyer findBuyer(String username) throws IOException {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		Type listType = new TypeToken<List<Buyer>>(){}.getType();
		FileReader fileReader = new FileReader("./data/buyers.json");
		List<Buyer> buyers = gson.fromJson(fileReader, listType);
		fileReader.close();
		for (Buyer buyer : buyers) {
			if(buyer.getUserName().equalsIgnoreCase(username)) {
				return buyer;
			}
		}
		return null;
	}
	
	public List<Buyer> findAllBuyers() throws IOException{
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		Type listType = new TypeToken<List<Buyer>>(){}.getType();
		FileReader fileReader = new FileReader("./data/buyers.json");
		List<Buyer> buyers = gson.fromJson(fileReader, listType);
		fileReader.close();
		return buyers;
	}
	
}
