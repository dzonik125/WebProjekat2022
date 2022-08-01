package controller;

import java.io.IOException;
import java.util.List;

import model.Location;
import service.LocationService;

public class LocationController {

	private LocationService ls = new LocationService();

	public LocationController() {
		super();
	}
	
	public boolean addLocation(Location location) throws IOException{
		return ls.addLocation(location);
	}
	
	public String editLocation(Location location, Location selectedLocation) throws IOException{
		return ls.editLocation(location, selectedLocation);
	}
	
	public boolean deleteLocation(double lat, double longt) throws IOException {
		return ls.deleteLocation(lat, longt);
	}
	
	public Location findLocation(double lat, double longt) throws IOException{
		return ls.findLocation(lat, longt);
	}
	
	public List<Location> findAllLocations() throws IOException{
		return ls.findAllLocations();
	}
	
}
