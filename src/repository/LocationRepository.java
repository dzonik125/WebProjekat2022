package repository;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import model.Location;

public class LocationRepository {

	private String fileLocation = "./data/locations.json";

	public LocationRepository() {
		super();
	}
	
	public boolean addLocation(Location location) throws IOException {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		Type listType = new TypeToken<List<Location>>(){}.getType();
		FileReader fileReader = new FileReader(fileLocation);
		List<Location> locations = gson.fromJson(fileReader, listType);
		fileReader.close();
		if(locations == null) {
			locations = new ArrayList<>();
		}
		boolean added = locations.add(location);
		FileWriter fileWriter = new FileWriter(fileLocation);
		gson.toJson(locations, fileWriter);
		fileWriter.close();
		return added;
	}
	
	public String editLocation(Location location, Location selectedLocation) throws IOException {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		Type listType = new TypeToken<List<Location>>(){}.getType();
		FileReader fileReader = new FileReader(fileLocation);
		List<Location> locations = gson.fromJson(fileReader, listType);
		fileReader.close();
		for (Location location2 : locations) {
			if(location.getLatitude() == selectedLocation.getLatitude() && location.getLongtitude() == selectedLocation.getLongtitude()) {
				break;
			}
			
			if(location.getLatitude() == location2.getLatitude() && location.getLongtitude() == location2.getLongtitude()) {
				return "Ne mogu postojati dve identicne lokacije!";
			}
		}
		
		for (Location lo : locations) {
			if(lo.getLatitude() == selectedLocation.getLatitude() && lo.getLongtitude() == selectedLocation.getLongtitude()) {
				lo.setLatitude(location.getLatitude());
				lo.setLongtitude(location.getLongtitude());
				lo.setAdress(location.getAdress());
				FileWriter fileWriter = new FileWriter(fileLocation);
				gson.toJson(locations, fileWriter);
				fileWriter.close();
				return "Uspesno izmenjeno";
			}
		}
		return "Ne postoji izabrana lokacija u listi lokacija!";
	}
	
	public boolean deleteLocation(double lat, double longt) throws IOException {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		Type listType = new TypeToken<List<Location>>(){}.getType();
		FileReader fileReader = new FileReader(fileLocation);
		List<Location> locations = gson.fromJson(fileReader, listType);
		fileReader.close();
		for (Location location : locations) {
			if(location.getLongtitude() == longt && location.getLatitude() == lat) {
				location.setDeleted(true);
				FileWriter fileWriter = new FileWriter(fileLocation);
				gson.toJson(locations, fileWriter);
				fileWriter.close();
			}
		}
		return false;
	}
	
	public Location findLocation(double lat, double longt) throws IOException {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		Type listType = new TypeToken<List<Location>>(){}.getType();
		FileReader fileReader = new FileReader(fileLocation);
		List<Location> locations = gson.fromJson(fileReader, listType);
		fileReader.close();
		for (Location location : locations) {
			if(location.getLongtitude() == longt && location.getLatitude() == lat) {
				return location;
			}
		}
		return null;
	}
	
	public List<Location> findAllLocations() throws IOException{
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		Type listType = new TypeToken<List<Location>>(){}.getType();
		FileReader fileReader = new FileReader(fileLocation);
		List<Location> locations = gson.fromJson(fileReader, listType);
		fileReader.close();
		return locations;
	}
}
