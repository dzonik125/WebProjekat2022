package controller;

import java.io.IOException;
import java.util.List;

import model.Adress;
import service.AdressService;

public class AdressController {
	
	private AdressService as = new AdressService();

	public AdressController() {
		super();
	}
	
	public boolean addAdress(Adress adress) throws IOException {
		return as.addAdress(adress);
	}
	
	public String editAdress(Adress adress, Adress selectedAdress) throws IOException{
		return as.editAdress(adress, selectedAdress);
	}
	
	public List<Adress> findAllAdresses() throws IOException{
		return as.findAllAdresses();
	}
	

}
