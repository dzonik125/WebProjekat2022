package controller;

import java.io.IOException;
import java.util.List;

import model.Location;
import model.SportObject;
import service.SportObjectService;

public class SportObjectController {

	private SportObjectService sos = new SportObjectService();

	public SportObjectController() {
		super();
	}
	
	public boolean addSportObject(SportObject sportObject) throws IOException{
		return sos.addSportObject(sportObject);
	}
	
	public String editSportObject(SportObject sportObject, SportObject selectedSportObject) throws IOException{
		return sos.editSportObject(sportObject, selectedSportObject);
	}
	
	public boolean deleteSportObject(Location location) throws IOException{
		return sos.deleteSportObject(location);
	}
	
	public SportObject findSportObject(Location location) throws IOException{
		return sos.findSportObject(location);
	}
	
	public List<SportObject> findAllSportObjects() throws IOException{
		return sos.findAllSportObjects();
	}
	
}
