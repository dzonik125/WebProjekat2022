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

import model.Adress;

public class AdressRepository {
	private String fileLocation = "./data/adress.json";

	public AdressRepository() {
		super();
	}
	
	public boolean addAdress(Adress adress) throws IOException {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		Type listType = new TypeToken<List<Adress>>(){}.getType();
		FileReader fileReader = new FileReader(fileLocation);
		List<Adress> adresses = gson.fromJson(fileReader, listType);
		fileReader.close();
		if(adresses == null) {
			adresses = new ArrayList<>();
		}
		boolean added = adresses.add(adress);
		FileWriter fileWriter = new FileWriter(fileLocation);
		gson.toJson(adresses, fileWriter);
		fileWriter.close();
		return added;
	}
	
	public String editAdress(Adress adress, Adress selectedAdress) throws IOException {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		Type listType = new TypeToken<List<Adress>>(){}.getType();
		FileReader fileReader = new FileReader(fileLocation);
		List<Adress> adresses = gson.fromJson(fileReader, listType);
		fileReader.close();
		for (Adress ad : adresses) {
			if(ad.getCity().equalsIgnoreCase(selectedAdress.getCity()) && ad.getPostCode() == selectedAdress.getPostCode() && ad.getsNum() == selectedAdress.getsNum() && ad.getStreet().equalsIgnoreCase(selectedAdress.getStreet())) {
				ad.setCity(adress.getCity());
				ad.setPostCode(adress.getPostCode());
				ad.setsNum(adress.getsNum());
				ad.setStreet(adress.getStreet());
				FileWriter fileWriter = new FileWriter(fileLocation);
				gson.toJson(adresses, fileWriter);
				fileWriter.close();
				return "Uspesno izmenjeno";
			}
		}
		return "Ne postoji izabrana adresa u listi adresa!";
	}
	
	public List<Adress> findAllAdresses() throws IOException{
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		Type listType = new TypeToken<List<Adress>>(){}.getType();
		FileReader fileReader = new FileReader(fileLocation);
		List<Adress> adresses = gson.fromJson(fileReader, listType);
		fileReader.close();
		return adresses;
	}
	
}