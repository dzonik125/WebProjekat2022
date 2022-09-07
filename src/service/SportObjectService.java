package service;

import java.io.IOException;
import java.util.List;

import model.Location;
import model.SportObject;
import repository.SportObjectRepository;

public class SportObjectService {
	
	private SportObjectRepository sor = new SportObjectRepository();

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
	
	public SportObject findSportObject(String name) throws IOException{
		return sor.findSportObject(name);
	}
	
	public List<SportObject> findAllSportObjects() throws IOException{
		return sor.findAllSportObjects();
	}
}
