package main;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import model.Administrator;
import model.Buyer;
import model.Gender;
import model.Membership;
import model.MembershipType;
import model.UserType;
import repository.AdministratorRepository;

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
	}

}
	