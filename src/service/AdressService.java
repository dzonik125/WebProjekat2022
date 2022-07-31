package service;

import java.io.IOException;
import java.util.List;

import model.Adress;
import repository.AdressRepository;

public class AdressService {
	private AdressRepository ar = new AdressRepository();
	
	public AdressService() {
		super();
	}

	public boolean addAdress(Adress adress) throws IOException {
		return ar.addAdress(adress);
	}
	
	public String editAdress(Adress adress, Adress selectedAdress) throws IOException{
		return ar.editAdress(adress, selectedAdress);
	}
	
	public List<Adress> findAllAdresses() throws IOException{
		return ar.findAllAdresses();
	}
}
