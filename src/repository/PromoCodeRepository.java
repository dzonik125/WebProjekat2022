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

import model.PromoCode;

public class PromoCodeRepository {
private String fileLocation = "./data/promocodes.json";
	
	public boolean addPromoCode(PromoCode promoCode) throws IOException {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		Type listType = new TypeToken<List<PromoCode>>(){}.getType();
		FileReader fileReader = new FileReader(fileLocation);
		List<PromoCode> promoCodes = gson.fromJson(fileReader, listType);
		fileReader.close();
		if(promoCodes == null) {
			promoCodes = new ArrayList<>();
		}
		boolean added = promoCodes.add(promoCode);
		FileWriter fileWriter = new FileWriter(fileLocation);
		gson.toJson(promoCodes, fileWriter);
		fileWriter.close();
		return added;
	}
	
	public List<PromoCode> findAllPromoCodes() throws IOException {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		Type listType = new TypeToken<List<PromoCode>>(){}.getType();
		FileReader fileReader = new FileReader(fileLocation);
		List<PromoCode> promoCodes = gson.fromJson(fileReader, listType);
		fileReader.close();
		return promoCodes;
	}

}
