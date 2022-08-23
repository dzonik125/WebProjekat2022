package main;

import static spark.Spark.before;
import static spark.Spark.get;
import static spark.Spark.options;
import static spark.Spark.port;
import static spark.Spark.post;

import java.io.IOException;
import java.security.Key;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import controller.AdministratorController;
import controller.BuyerController;
import controller.CoachController;
import controller.ManagerController;
import controller.SportObjectController;
import controller.UserController;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import model.Buyer;
import model.Gender;
import model.SportObject;
import model.User;
import model.UserType;

public class Main {
	
	private static Gson g = new Gson();
	private static BuyerController bc = new BuyerController();
	private static UserController uc = new UserController();
	private static AdministratorController ac = new AdministratorController();
	private static CoachController cc = new CoachController();
	private static ManagerController mc = new ManagerController();
	private static SportObjectController soc = new SportObjectController();
	
	static Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

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
			User user = new User(username, password, name, surname, gender, birthd, UserType.BUYER);
			Buyer buyer = new Buyer(username, password, name, surname, gender, birthd, UserType.BUYER, null, null, 0, null);
			boolean success = uc.addUser(user);
			if(success) {
				bc.addBuyer(buyer);
				return("OK");
			}else {
				return(400);
			}
		});
		
		post("/rest/loginUser/", (req, res) -> {
			res.type("application/json");
			String payload = req.body();
			JsonParser jsonParser = new JsonParser();
			JsonObject jObject = jsonParser.parse(payload).getAsJsonObject();
			String username = jObject.get("username").getAsString();
			String password = jObject.get("password").getAsString();
			List<User> users = uc.findAllUsers();
			User user2 = null;
			boolean matched = false;
			
			for (User user : users) {
				if(user.getUserName().equals(username) && !user.getPassword().equals(password)) {
					return 401;
				}
				
				if(user.getUserName().equals(username) && user.getPassword().equals(password)) {
					user2 = user;
					matched = true;
				}
			}
			
			if(matched) {
				String jws = Jwts.builder().setSubject(user2.getUserName() + "." + user2.getUserType()).setIssuedAt(new Date()).signWith(key).compact();
				user2.setJWTToken(jws);
				return g.toJson(user2);
			}
			
			return 400;
		});
		
		get("/rest/checkIfLogged/", (req, res) -> {
			String auth = req.headers("Authorization");
			System.out.println(auth);
			if((auth != null) && (auth.contains("Bearer "))) {
				String jwt = auth.substring(auth.indexOf("Bearer ") + 7);
				try {
					Jws<Claims> claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt);
					return claims.getBody().getSubject();
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
			return "No user logged in";
		});
		
		post("/rest/logOut/", (req, res) -> {
			String payload = req.body();
			JsonParser jsonParser = new JsonParser();
			JsonObject jObject = jsonParser.parse(payload).getAsJsonObject();
			String username = jObject.get("username").getAsString();
			User user = uc.findUser(username);
			if(user == null) {
				return 400;
			}
			return 200;
		});
		
		get("/rest/findAllSportObjects/", (req, res) -> {
			res.type("application/json");
			List<SportObject> sportObjects = soc.findAllSportObjects();
			return g.toJson(sportObjects);
		});
	}

}
	