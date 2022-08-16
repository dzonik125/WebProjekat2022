package main;

import static spark.Spark.before;
import static spark.Spark.options;
import static spark.Spark.port;
import static spark.Spark.post;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import controller.BuyerController;
import model.BType;
import model.Buyer;
import model.BuyerType;
import model.Gender;
import model.Membership;
import model.MembershipType;
import model.UserType;

public class Main {
	
	private static Gson g = new Gson();
	private static BuyerController bc = new BuyerController();

	public static void main(String[] args) throws IOException, JsonIOException, JsonSyntaxException, ParseException {
		port(8082);
		
		options("/*", (request, response) -> {

		    String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
		    if (accessControlRequestHeaders != null) {
		        response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
		    }

		    String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
		    if (accessControlRequestMethod != null) {
		        response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
		    }
		    return "OK";
		});

		before((request, response) -> {
		    response.header("Access-Control-Allow-Origin", "*");
		});
		
		post("/rest/registerUser/", (req, res) -> {
			res.type("application/json");
			String payload = req.body();
			JsonParser jsonParser = new JsonParser();
			JsonObject jObject = jsonParser.parse(payload).getAsJsonObject();
			String username = jObject.get("username").getAsString();
			String password = jObject.get("password").getAsString();
			String name = jObject.get("nam").getAsString();
			String surname = jObject.get("surnam").getAsString();
			Gender gender = Gender.valueOf(jObject.get("gender").getAsString());
			String birthday = jObject.get("birthday").getAsString();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date birthd = format.parse(birthday);
			Buyer buyer = new Buyer(username, password, name, surname, gender, birthd, UserType.BUYER, null, null, 0, null);
			boolean success = bc.addBuyer(buyer);
			if(success) {
				return("OK");
			}else {
				return(400);
			}
		});
	}

}
	