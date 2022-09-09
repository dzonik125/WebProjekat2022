package service;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
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
	
	public List<SportObject> sortSportObjectsByClosingTime () throws IOException {
		List<SportObject> sportObjects = sor.findAllSportObjects();
		int counter = 0;
		for (SportObject sportObject : sportObjects) {
			if(isClosed(sportObject)) {
				counter++;
			}
		}
		int counter2 = 0;
		for (int i = 0; i < sportObjects.size(); i++) {
			if(counter2 == counter) {
				break;
			}
			SportObject sportObject = sportObjects.get(i);
			if(isClosed(sportObject)) {
				sportObjects.remove(i--);
				sportObjects.add(sportObject);
				counter2++;
			}
		}
		return sportObjects;
	}
	
	private boolean isClosed(SportObject sportObject) {
		Calendar calendar = Calendar.getInstance();
		Date now = calendar.getTime();
		String[] splitted = sportObject.getWorkingHours().split("-");
		String[] open = splitted[0].split(":");
		int openHour = Integer.parseInt(open[0]);
		int openMinute = Integer.parseInt(open[1]);
		String[] closed = splitted[1].split(":");
		int closedHour = Integer.parseInt(closed[0]);
		int closedMinute = Integer.parseInt(closed[1]);
		calendar.set(Calendar.HOUR_OF_DAY, openHour);
		calendar.set(Calendar.MINUTE, openMinute);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		Date openDate = calendar.getTime();
		calendar.set(Calendar.HOUR_OF_DAY, closedHour);
		calendar.set(Calendar.MINUTE, closedMinute);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		Date closeDate = calendar.getTime();
		if(now.after(openDate) && now.before(closeDate)) {
			return false;
		}
		return true;
	}
}
