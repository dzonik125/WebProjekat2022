package main;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import model.Administrator;
import model.Adress;
import model.BType;
import model.Buyer;
import model.BuyerType;
import model.Gender;
import model.Location;
import model.Membership;
import model.MembershipStatus;
import model.MembershipType;
import model.ObjectStatus;
import model.ObjectType;
import model.SportObject;
import model.UserType;
import repository.AdministratorRepository;
import repository.BuyerRepository;
import repository.MembershipRepository;

public class Main {

	public static void main(String[] args) throws IOException, JsonIOException, JsonSyntaxException, ParseException {
//		Adress adress = new Adress("Laze Kostica", 77, "Kovilj", 21243);
//		Adress adress2 = new Adress("Krva Bikickog", 24, "Budisava", 21242);
//		List<Adress> adresses = new ArrayList<Adress>();
//		adresses.add(adress);
//		adresses.add(adress2);
//		
//		Gson gson = new Gson();
//		Writer writer = new FileWriter("C:\\Users\\dZoNi\\eclipseEnterprise-workspace\\PassFit\\data\\adress.json", true);
//		gson.toJson(adresses, writer);
//		writer.flush();
//		writer.close();
//		Primer dobre serijalizcije (Neophodno prvo dodati bazu koja ce cuvati liste podataka iz modela)!
//		String date1 = "01/12/2000";
//		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
//		Date d = format.parse(date1);
//		Administrator admin1 = new Administrator("dzonik", "123", "Nikola", "Kolarov", Gender.M, d, UserType.ADMINISTRATOR);
//		String date2 = "06/12/2000";
//		Date d2 = format.parse(date2);
//		Administrator admin2 = new Administrator("miskic", "1234", "Dragan", "Miskic", Gender.M, d2, UserType.ADMINISTRATOR);
//		List<Administrator> admins = new ArrayList<Administrator>();
//		admins.add(admin1);
//		admins.add(admin2);
//		Gson gson = new GsonBuilder().setPrettyPrinting().create();
//		Writer writer = new FileWriter("C:\\Users\\dZoNi\\eclipseEnterprise-workspace\\PassFit\\data\\administrators.json");
//		gson.toJson(admins, writer);
//		writer.flush();
//		writer.close();
//		
//		String date = "29/07/2022";
//		String date2 = "29/08/2022 12:00:00 PM";
//		
//		SimpleDateFormat format2 = new SimpleDateFormat("dd/MM/yyyy");
//		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss aaa");
//		
//		Date d1 = format2.parse(date);
//		Date d2 = format.parse(date2);
//		
//		Adress adress = new Adress("Laze Kostica", 77, "Kovilj", 21243);
//		Location location = new Location(15.42, 16.23, adress);
//		
//		SportObject sportObject = new SportObject("Ahilej", ObjectType.GYM, "Ova teretana nudi usluge grupnih i personalnih treninga", ObjectStatus.WORKING, location, "./images/ahilej.png", 3.5, "08:00-22:00");
//		List<SportObject> sportObjects = new ArrayList<SportObject>();
//		sportObjects.add(sportObject);
//		
//		BuyerType by = new BuyerType(BType.SILVER, 0.15, 1000);
//		
//		Membership membership = new Membership(MembershipType.ANNUAL, d1, d2, 3000, null, MembershipStatus.ACTIVE, 10);
//		
//		Buyer buyer = new Buyer("kols", "12345", "Marko", "Nesin", Gender.M, d1, UserType.BUYER, membership, sportObjects, 0, by);
//		
//		membership.setBuyer(buyer);
//		
//		List<Buyer> buyers = new ArrayList<Buyer>();
//		buyers.add(buyer);
//		
//		Gson gson = new GsonBuilder().setPrettyPrinting().create();
//		Writer writer = new FileWriter("./data/buyers.json");
//		gson.toJson(buyers, writer);
//		writer.flush();
//		writer.close();
		
		String date = "29/07/2022";
		String date2 = "29/08/2022 12:00:00 PM";
		
		SimpleDateFormat format2 = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss aaa");
		
		Date d1 = format2.parse(date);
		Date d2 = format.parse(date2);
		
		Adress adress = new Adress("Laze Kostica", 77, "Kovilj", 21243);
		Location location = new Location(15.42, 16.23, adress);
		
		SportObject sportObject = new SportObject("Ahilej", ObjectType.GYM, "Ova teretana nudi usluge grupnih i personalnih treninga", ObjectStatus.WORKING, location, "./images/ahilej.png", 3.5, "08:00-22:00");
		List<SportObject> sportObjects = new ArrayList<SportObject>();
		sportObjects.add(sportObject);
		
		BuyerType by = new BuyerType(BType.SILVER, 0.15, 1000);
		
		Membership membership = new Membership(MembershipType.ANNUAL, d1, d2, 3000, null, MembershipStatus.ACTIVE, 10);
		
		Buyer buyer = new Buyer("kols", "12345", "Marko", "Nesin", Gender.M, d1, UserType.BUYER, membership, sportObjects, 0, by);
		
		membership.setBuyer(buyer);
		
		BuyerRepository br = new BuyerRepository();
//		br.addBuyer(buyer);
		for (Buyer b : br.findAllBuyers()) {
			System.out.println(b.getName());
		}
		
		MembershipRepository mr = new MembershipRepository();
		Buyer marko = br.findBuyer("kols");
//		mr.addMemebership(marko.getMembership());
	}

}
	