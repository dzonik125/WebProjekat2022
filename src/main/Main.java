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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import controller.AdministratorController;
import controller.BuyerController;
import controller.CoachController;
import controller.ManagerController;
import controller.MembershipController;
import controller.SportObjectController;
import controller.TrainingController;
import controller.TrainingHistoryController;
import controller.UserController;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import model.Administrator;
import model.Adress;
import model.Buyer;
import model.Coach;
import model.Gender;
import model.Location;
import model.Manager;
import model.Membership;
import model.MembershipType;
import model.ObjectType;
import model.SportObject;
import model.Training;
import model.TrainingHistory;
import model.TrainingType;
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
	private static TrainingController tc = new TrainingController();
	private static MembershipController mController = new MembershipController();
	private static TrainingHistoryController trainingHistoryController = new TrainingHistoryController();
	
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
			Buyer buyer = new Buyer(username, password, name, surname, gender, birthd, UserType.BUYER, null, null, 0);
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
				
				if(user.getUserName().equals(username) && user.isDeleted()) {
					return 400;
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
		
		post("/rest/getLoggedInUser/", (req, res) -> {
			res.type("application/json");
			String payload = req.body();
			System.out.println(payload);
			if(uc.findUser(payload) == null) {
				return 400;
			}
			return uc.findUser(payload);
		});
		
		post("/rest/getUser/", (req, res) -> {
			res.type("application/json");
			String payload = req.body();
			JsonParser jsonParser = new JsonParser();
			JsonObject jObject = jsonParser.parse(payload).getAsJsonObject();
			String username = jObject.get("username").getAsString();
			if(uc.findUser(username) == null) {
				return 400;
			}
			return g.toJson(uc.findUser(username));
		});
		
		post("/rest/editUser/", (req, res) -> {
			String payload = req.body();
			JsonParser jsonParser = new JsonParser();
			JsonObject jObject = jsonParser.parse(payload).getAsJsonObject();
			System.out.println(jObject.get("username"));
			String selectedUsername = jObject.get("oldUsername").getAsString();
			String username = jObject.get("username").getAsString();
			String password = jObject.get("password").getAsString();
			String name = jObject.get("name").getAsString();
			String surname = jObject.get("surname").getAsString();
			Gender gender = Gender.valueOf(jObject.get("gender").getAsString());
			String birthday = jObject.get("birthday").getAsString();
			SimpleDateFormat format = new SimpleDateFormat("MMM d, yyyy, HH:mm:ss aaa");
			Date birthd = format.parse(birthday);
			UserType uType = UserType.valueOf(jObject.get("userType").getAsString());
			User user = new User(username, password, name, surname, gender, birthd, uType);
			String edited = uc.editUser(user, selectedUsername);
			if(uType.toString().equals("ADMINISTRATOR")) {
				Administrator administrator = new Administrator(username, password, name, surname, gender, birthd, uType);
				String editedAdmin = ac.editAdministrator(administrator, selectedUsername);
				if(edited.equalsIgnoreCase("Uspesno izmenjeno") && editedAdmin.equalsIgnoreCase("Uspesno izmenjeno")) {
					return 200;
				}
			} else if (uType.toString().equals("BUYER")) {
				Buyer buyer = new Buyer(username, password, name, surname, gender, birthd, uType, null, null, 0);
				String editedBuyer = bc.editBuyer(buyer, selectedUsername);
				if(edited.equalsIgnoreCase("Uspesno izmenjeno") && editedBuyer.equalsIgnoreCase("Uspesno izmenjeno")) {
					return 200;
				}
			} else if (uType.toString().equals("COACH")) {
				Coach coach = new Coach(username, password, name, surname, gender, birthd, uType, null);
				String editedCoach = cc.editCoach(coach, selectedUsername);
				if(edited.equalsIgnoreCase("Uspesno izmenjeno") && editedCoach.equalsIgnoreCase("Uspesno izmenjeno")) {
					return 200;
				}
			} else if (uType.toString().equals("MANAGER")) {
				Manager manager = new Manager(username, password, name, surname, gender, birthd, uType, null);
				String editedManager = mc.editManager(manager, selectedUsername);
				if(edited.equalsIgnoreCase("Uspesno izmenjeno") && editedManager.equalsIgnoreCase("Uspesno izmenjeno")) {
					return 200;
				}
			}
			return 400;
		});
		
		get("/rest/getAllUsers/", (req, res) -> {
			res.type("application/json");
			List<User> allUsers = new ArrayList<User>();
			allUsers = uc.findAllUsers();
			return g.toJson(allUsers);
		});
		
		post("/rest/deleteUser/", (req, res) -> {
			String payload = req.body();
			JsonParser jsonParser = new JsonParser();
			JsonObject jObject = jsonParser.parse(payload).getAsJsonObject();
			String username = jObject.get("username").getAsString();
			User u = uc.findUser(username);
			boolean deleted= uc.deleteUser(username);
			if(u.getUserType().toString().equals("ADMINISTRATOR")) {
				boolean adminDeleted = ac.deleteAdministrator(username);
				if(adminDeleted && deleted) {
					return 200;
				}
			} else if (u.getUserType().toString().equals("BUYER")) {
				boolean buyerDeleted = bc.deleteBuyer(username);
				if(buyerDeleted && deleted) {
					return 200;
				}
			} else if (u.getUserType().toString().equals("MANAGER")) {
				boolean managerDeleted = mc.deleteManager(username);
				if(managerDeleted && deleted) {
					return 200;
				}
			} else if (u.getUserType().toString().equals("COACH")) {
				boolean coachDeleted = cc.deleteCoach(username);
				tc.deleteCoachesTrainings(username);
				if(coachDeleted && deleted) {
					return 200;
				}
			}
			return 400;
		});
		
		post("rest/createSportObject/", (req, res) -> {
			String payload = req.body();
			JsonParser jsonParser = new JsonParser();
			JsonObject jObject = jsonParser.parse(payload).getAsJsonObject();
			String name = jObject.get("name").getAsString();
			ObjectType oType = ObjectType.valueOf(jObject.get("objectType").getAsString());
			String services = jObject.get("services").getAsString();
			double lat = jObject.get("lat").getAsDouble();
			double longt = jObject.get("long").getAsDouble();
			String street = jObject.get("street").getAsString();
			int sNum = jObject.get("sNum").getAsInt();
			String city = jObject.get("city").getAsString();
			int postcode = jObject.get("postcode").getAsInt();
			String image = jObject.get("image").getAsString();
			String workingHours = jObject.get("workingHours").getAsString();
			String managerUsername = jObject.get("manager").getAsString();
			Adress a = new Adress(street, sNum, city, postcode);
			Location l = new Location(lat, longt, a);
			SportObject so = new SportObject(name, oType, services, null, l, image, 0, workingHours);
			if(soc.addSportObject(so) && mc.bindManagerWithSportObject(managerUsername, so)) {
				return 200;
			}
			return 400;
		});
		
		post("rest/createManager/", (req, res) -> {
			String payload = req.body();
			JsonParser jsonParser = new JsonParser();
			JsonObject jObject = jsonParser.parse(payload).getAsJsonObject();
			String username = jObject.get("username").getAsString();
			String password = jObject.get("password").getAsString();
			String name = jObject.get("nam").getAsString();
			String surname = jObject.get("surnam").getAsString();
			System.out.println(jObject.get("gender").getAsString());
			Gender gender = Gender.valueOf(jObject.get("gender").getAsString());
			String birthday = jObject.get("birthday").getAsString();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date birthd = format.parse(birthday);
			User user = new User(username, password, name, surname, gender, birthd, UserType.MANAGER);
			Manager manager = new Manager(username, password, name, surname, gender, birthd, UserType.MANAGER, null);
			boolean created = uc.addUser(user);
			if(!created) {
				return 400;
			}
			boolean managerCreated = mc.addManager(manager);
			if(created && managerCreated) {
				return 200;
			}
			return 400;
		});
		
		post("rest/createCoach/", (req, res) -> {
			String payload = req.body();
			JsonParser jsonParser = new JsonParser();
			JsonObject jObject = jsonParser.parse(payload).getAsJsonObject();
			String username = jObject.get("username").getAsString();
			String password = jObject.get("password").getAsString();
			String name = jObject.get("nam").getAsString();
			String surname = jObject.get("surnam").getAsString();
			System.out.println(jObject.get("gender").getAsString());
			Gender gender = Gender.valueOf(jObject.get("gender").getAsString());
			String birthday = jObject.get("birthday").getAsString();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date birthd = format.parse(birthday);
			User user = new User(username, password, name, surname, gender, birthd, UserType.COACH);
			Coach coach = new Coach(username, password, name, surname, gender, birthd, UserType.COACH, null);
			boolean created = uc.addUser(user);
			if(!created) {
				return 400;
			}
			boolean coachCreated = cc.addCoach(coach);
			if(created && coachCreated) {
				return 200;
			}
			return 400;
		});
		
		get("rest/getFreeManagers/", (req, res) -> {
			res.type("application/json");
			return g.toJson(mc.findFreeManagers());
		});
		
		post("rest/getManagerObject/", (req, res) -> {
			res.type("application/json");
			String payload = req.body();
			System.out.println(payload);
			JsonParser jsonParser = new JsonParser();
			JsonObject jObject = jsonParser.parse(payload).getAsJsonObject();
			String username = jObject.get("username").getAsString();
			return g.toJson(mc.getManagerSportObject(username));
		});
		
		post("/rest/createTraining/", (req, res) -> {
			String payload = req.body();
			JsonParser jsonParser = new JsonParser();
			JsonObject jObject = jsonParser.parse(payload).getAsJsonObject();
			String name = jObject.get("name").getAsString();
			TrainingType trainingType = TrainingType.valueOf(jObject.get("trainingType").getAsString());
			String image = jObject.get("image").getAsString();
			long duration = jObject.get("duration").getAsLong();
			String description = jObject.get("description").getAsString();
			String objectName = jObject.get("sportObject").getAsString();
			System.out.println(jObject.get("coach").getAsString());
			if(jObject.get("coach") == null || jObject.get("coach").getAsString().equals("")) {
				Training training = new Training(name, trainingType, soc.findSportObject(objectName), duration, description, image);
				if(tc.addTraining(training)) {
					return 200;
				}
				return 400;
			} else {
				String coach = jObject.get("coach").getAsString();
				Training t = new Training(name, trainingType, soc.findSportObject(objectName), duration, description, image, coach);
				if(tc.addTraining(t)) {
					return 200;
				}
				return 400;
			}
		});
		
		post("rest/getTrainingsForObject/", (req, res) -> {
			String payload = req.body();
			JsonParser jsonParser = new JsonParser();
			JsonObject jObject = jsonParser.parse(payload).getAsJsonObject();
			String object = jObject.get("object").getAsString();
			return g.toJson(tc.findTrainingsForSportObject(object));
		});
		
		
		get("rest/getAllCoaches/", (req, res) -> {
			res.type("application/json");
			return g.toJson(cc.findAllCoaches());
		});
		
		post("rest/getCoachName/", (req, res) -> {
			res.type("text");
			String payload = req.body();
			JsonParser jsonParser = new JsonParser();
			JsonObject jObject = jsonParser.parse(payload).getAsJsonObject();
			if(jObject.get("coach") == null || jObject.get("coach").getAsString().equals(" ")) {
				return "Bez trenera";
			}
			String username = jObject.get("coach").getAsString();
			System.out.println(username);
			return cc.findCoach(username).getName() + " " + cc.findCoach(username).getSurname();
		});
		
		post("rest/getCoachByObject/", (req, res) -> {
			res.type("application/json");
			String payload = req.body();
			JsonParser jsonParser = new JsonParser();
			JsonObject jObject = jsonParser.parse(payload).getAsJsonObject();
			String object = jObject.get("object").getAsString();
			List<Training> trainings = tc.findTrainingsForSportObject(object);
			List<String> coachUsernames = new ArrayList<>();
			for (Training training : trainings) {
				if (training.getCoach() != null) {
					coachUsernames.add(training.getCoach());
				}
			}
			Set<String> s = new LinkedHashSet<String>(coachUsernames);
			coachUsernames = new ArrayList<String>(s);
			List<Coach> toRet = new ArrayList<Coach>();
			for (String string : coachUsernames) {
				toRet.add(cc.findCoach(string));
			}
			return g.toJson(toRet);
		});
		
		post("rest/editTraining/", (req, res) -> {
			res.type("application/json");
			String payload = req.body();
			JsonParser jsonParser = new JsonParser();
			JsonObject jObject = jsonParser.parse(payload).getAsJsonObject();
			TrainingType type = TrainingType.valueOf(jObject.get("type").getAsString());
			long duration = jObject.get("duration").getAsLong();
			int id = jObject.get("id").getAsInt();
			String description = jObject.get("description").getAsString();
			Training training = new Training(null, type, null, duration, description, null);
			Training selectedTraining = tc.findTraining(id);
			if(tc.editTraining(training, selectedTraining).equals("Uspesno izmenjeno")) {
				return 200;
			}
			return 400;
		});
		
		post("/rest/GetObjectByName/", (req, res) -> {
			res.type("application/json");
			String payload = req.body();
			JsonParser jsonParser = new JsonParser();
			JsonObject jObject = jsonParser.parse(payload).getAsJsonObject();
			String name = jObject.get("object").getAsString();
			return g.toJson(soc.findSportObject(name));
		});
		
		post("/rest/buyMembership/", (req, res) -> {
			String payload = req.body();
			JsonParser jsonParser = new JsonParser();
			JsonObject jObject = jsonParser.parse(payload).getAsJsonObject();
			String username = jObject.get("username").getAsString();
			MembershipType membershipType = MembershipType.valueOf(jObject.get("type").getAsString());
			int daily = jObject.get("dailyLogs").getAsInt();
			int price = jObject.get("price").getAsInt();
			Membership membership = new Membership(membershipType, price, username, daily);
			if(mController.addMemebership(membership)) {
				return 200;
			}
			return 400;
		});
		
		get("/rest/checkDailyLogs/", (req, res) -> {
			mController.checkDailyLogs();
			return 200;
		});
		
		post("/rest/train/", (req, res) -> {
			String payload = req.body();
			JsonParser jsonParser = new JsonParser();
			JsonObject jObject = jsonParser.parse(payload).getAsJsonObject();
			String object = jObject.get("object").getAsString();
			String training = jObject.get("training").getAsString();
			String buyer = jObject.get("user").getAsString();
			Training t1 = null;
			for (Training t : tc.findTrainingsForSportObject(object)) {
				if(t.getName().equals(training)) {
					t1 = t;
				}
			}
			if(mController.canTrainToday(buyer)) {
				mController.doOneTraining(buyer);
				Calendar calendar = Calendar.getInstance();
				Date now = calendar.getTime();
				TrainingHistory trainingHistory = new TrainingHistory(now, t1, buyer);
				trainingHistoryController.addTrainingHistory(trainingHistory);
				bc.addObjectToBuyerVisited(buyer, object);
				return 200;
			}
			return 400;
		});
		
		get("/rest/checkMembershipActivity/", (req, res) -> {
			mController.checkMembershipsValidity();
			return 200;
		});
		
		post("/rest/scheduleTraining/", (req, res) -> {
			String payload = req.body();
			JsonParser jsonParser = new JsonParser();
			JsonObject jObject = jsonParser.parse(payload).getAsJsonObject();
			String dt = jObject.get("date").getAsString();
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
			Date date = simpleDateFormat.parse(dt);
			String coach = jObject.get("coach").getAsString();
			String name = jObject.get("name").getAsString();
			String[] spllited = coach.split(" ");
			String object = jObject.get("object").getAsString();
			String buyer = jObject.get("user").getAsString();
			List<Training> trainings = tc.findTrainingsForSportObject(object);
			for (Training training : trainings) {
				if(training.getName().equals(name)) {
					training.setAppointmentDate(date);
					cc.appointTrainingToCoach(training, spllited[0], spllited[1]);
					bc.appointTrainingToBuyer(buyer, training);
					return 200;
				}
			}
			return 400;
		});
		
		post("/rest/getScheduledTrainingsForCoach/", (req, res) -> {
			res.type("application/json");
			String payload = req.body();
			JsonParser jsonParser = new JsonParser();
			JsonObject jObject = jsonParser.parse(payload).getAsJsonObject();
			String username = jObject.get("coach").getAsString();
			return g.toJson(cc.getScheduledTrainingsForCoach(username));
		});
		
		post("/rest/getGroupTrainingsForCoach/", (req, res) -> {
			res.type("application/json");
			String payload = req.body();
			JsonParser jsonParser = new JsonParser();
			JsonObject jObject = jsonParser.parse(payload).getAsJsonObject();
			String username = jObject.get("coach").getAsString();
			return g.toJson(tc.getGroupTrainingsForCoach(username));
		});
		
		get("/rest/sortObjects/", (req, res) -> {
			res.type("application/json");
			return g.toJson(soc.sortSportObjectsByClosingTime());
		});
		
	
		post("/rest/cancelTraining/", (req, res) -> {
			res.type("application/json");
			String payload = req.body();
			JsonParser jsonParser = new JsonParser();
			JsonObject jObject = jsonParser.parse(payload).getAsJsonObject();
			String name = jObject.get("name").getAsString();
			String date = jObject.get("date").getAsString();
			String username = jObject.get("username").getAsString();
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
			Date dt = simpleDateFormat.parse(date);
			bc.deleteBuyerAppointedTrainings(username, name, dt);
			if(cc.deleteAppointedTraining(username, name, dt)) {
				return 200;
			}
			return 400;
		});
		
		post("/rest/getObjectBuyers/", (req, res) -> {
			res.type("application/json");
			String payload = req.body();
			JsonParser jsonParser = new JsonParser();
			JsonObject jObject = jsonParser.parse(payload).getAsJsonObject();
			String object = jObject.get("object").getAsString();
			return g.toJson(bc.findBuyersVisitedSportObject(object));
		});
		
		post("/rest/getBuyerSpecificData/", (req, res) -> {
			res.type("application/json");
			String payload = req.body();
			JsonParser jsonParser = new JsonParser();
			JsonObject jObject = jsonParser.parse(payload).getAsJsonObject();
			String username = jObject.get("username").getAsString();
			return g.toJson(bc.findBuyer(username));
		});
		
		post("/rest/getAllBuyersTrainings/", (req, res) -> {
			res.type("application/json");
			String payload = req.body();
			JsonParser jsonParser = new JsonParser();
			JsonObject jObject = jsonParser.parse(payload).getAsJsonObject();
			String username = jObject.get("username").getAsString();
			return g.toJson(trainingHistoryController.findTrainingHistoriesForBuyer(username));
		});
		
		post("/rest/getDiscount/", (req, res) -> {
			res.type("application/json");
			String payload = req.body();
			JsonParser jsonParser = new JsonParser();
			JsonObject jObject = jsonParser.parse(payload).getAsJsonObject();
			String username = jObject.get("username").getAsString();
			return g.toJson(bc.findBuyer(username).getBuyerType().getDiscount());
		});
		
		post("/rest/getBuyerScheduledTrainings/", (req, res) -> {
			res.type("application/json");
			String payload = req.body();
			JsonParser jsonParser = new JsonParser();
			JsonObject jObject = jsonParser.parse(payload).getAsJsonObject();
			String username = jObject.get("user").getAsString();
			return g.toJson(bc.getBuyerAppointedTrainings(username));
		});
		
		get("/rest/checkIfPersonalTrainingIsCompleted/", (req, res) -> {
			tc.checkCompletedPersonal();
			return 200;
		});
		
		post("/rest/deleteSportObject/", (req, res) -> {
			String payload = req.body();
			JsonParser jsonParser = new JsonParser();
			JsonObject jObject = jsonParser.parse(payload).getAsJsonObject();
			String object = jObject.get("object").getAsString();
			if(soc.deleteSportObject(object)) {
				trainingHistoryController.deleteSportObjectFromTH(object);
				bc.deleteSportObjectFromBuyersTrainings(object);
				cc.deleteSportObjectFromCoachTrainings(object);
				mc.deleteManagerSportObject(object);
				tc.deleteSportObjectsFromTrainings(object);
				return 200;
			}
			return 400;
		});
	}

}
	