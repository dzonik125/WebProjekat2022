package service;

import java.io.IOException;
import java.util.List;

import model.Location;
import model.SportObject;

public class SportObjectService {
	
	private SportObjectService sor = new SportObjectService();

	public SportObjectService() {
		super();
	}
	
	public boolean addSportObject(SportObject sportObject) throws IOException{
		return sor.addSportObject(sportObject);
	}
	
	public String editSportObject(SportObject sportObject, SportObject selectedSportObject) throws IOException{
		return sor.editSportObject(sportObject, selectedSportObject);
	}
	
	public boolean deleteSportObject(Location location) throws IOException{
		return sor.deleteSportObject(location);
	}
	
	public SportObject findSportObject(Location location) throws IOException{
		return sor.findSportObject(location);
	}
	
	public List<SportObject> findAllSportObjects() throws IOException{
		return sor.findAllSportObjects();
	}
}
