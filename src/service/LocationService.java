package service;

import java.io.IOException;
import java.util.List;

import model.Location;
import repository.LocationRepository;

public class LocationService {

	private LocationRepository lr = new LocationRepository();

	public LocationService() {
		super();
	}
	
	public boolean addLocation(Location location) throws IOException{
		return lr.addLocation(location);
	}
	
	public String editLocation(Location location, Location selectedLocation) throws IOException{
		return lr.editLocation(location, selectedLocation);
	}
	
	public boolean deleteLocation(double lat, double longt) throws IOException {
		return lr.deleteLocation(lat, longt);
	}
	
	public Location findLocation(double lat, double longt) throws IOException{
		return lr.findLocation(lat, longt);
	}
	
	public List<Location> findAllLocations() throws IOException{
		return lr.findAllLocations();
	}
	
}
