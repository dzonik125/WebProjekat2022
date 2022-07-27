package model;

import java.time.LocalDateTime;
import java.util.Random;

import javaxt.utils.string;

public class Membership {
	private String id;
	private MembershipType membershipType;
	private LocalDateTime dueDate;
	private LocalDateTime active;
	private int price;
	private Buyer buyer;
	private MembershipStatus membershipStatus;
	private int dailyLogs; //broj ulazaka dnevno (koliko je dozvoljeno)
	
	private String generateID() {
		int leftLimit = 48; // numeral '0'
	    int rightLimit = 122; // letter 'z'
	    int targetStringLength = 10;
	    Random random = new Random();

	    return random.ints(leftLimit, rightLimit + 1)
	      .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
	      .limit(targetStringLength)
	      .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
	      .toString();
	}
}
