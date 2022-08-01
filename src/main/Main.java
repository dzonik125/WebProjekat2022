package main;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import model.Adress;
import model.BType;
import model.Buyer;
import model.BuyerType;
import model.Coach;
import model.Comment;
import model.Gender;
import model.Location;
import model.Manager;
import model.Membership;
import model.MembershipStatus;
import model.MembershipType;
import model.ObjectStatus;
import model.ObjectType;
import model.SportObject;
import model.Training;
import model.TrainingHistory;
import model.TrainingType;
import model.UserType;
import repository.AdressRepository;
import repository.BuyerRepository;
import repository.CoachRepository;
import repository.CommentRepository;
import repository.LocationRepository;
import repository.ManagerRepository;
import repository.MembershipRepository;
import repository.SportObjectRepository;
import repository.TrainingHistoryRepository;
import repository.TrainingRepository;

public class Main {

	@SuppressWarnings("deprecation")
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
		
		CoachRepository cr = new CoachRepository();
		Instant t1 = Instant.parse("2022-02-08T11:00:00Z");
		Instant t2 = Instant.parse("2022-02-08T12:00:00Z");
		long duration = Duration.between(t1, t2).toMinutes();
		
		Training tr = new Training("Kardio", TrainingType.PERSONAL, sportObject, duration, "Trening je bio dobar", "./image/training.jpg", null);
		TrainingHistory th = new TrainingHistory(d2, tr, buyer, null);
		Coach c = new Coach("fiksni", "123456", "Srdjan", "Stanic", Gender.M, d2, UserType.COACH, th);
		th.setCoach(c);
		tr.setCoach(c);
//		cr.addCoach(c);
		System.out.println(duration);
		
		TrainingRepository trr = new TrainingRepository();
//		trr.addTraining(tr);
		
		TrainingHistoryRepository thr = new TrainingHistoryRepository();
//		thr.addTrainingHistory(th);
		
		SportObjectRepository sportObjectRepository = new SportObjectRepository();
		sportObjectRepository.deleteSportObject(location);
		
		LocationRepository locationRepository = new LocationRepository();
//		locationRepository.addLocation(location);
		
		AdressRepository adressRepository = new AdressRepository();
		adressRepository.addAdress(adress);
		
		Manager manager = new Manager("purames", "sicko", "Stefan", "Masnec", Gender.M, d2, UserType.MANAGER, sportObject);
		ManagerRepository managerRepository = new ManagerRepository();
		managerRepository.addManager(manager);
		
		CommentRepository commentRepository = new CommentRepository();
		Comment com = new Comment(buyer, sportObject, date2, 5);
//		commentRepository.addComment(com);
	}

}
	