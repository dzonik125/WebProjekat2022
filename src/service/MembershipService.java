package service;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import model.BType;
import model.BuyerType;
import model.Membership;
import model.MembershipStatus;
import repository.MembershipRepository;
import repository.TrainingHistoryRepository;

public class MembershipService {
	
	private MembershipRepository mr = new MembershipRepository();
	private TrainingHistoryRepository thr = new TrainingHistoryRepository();
	private TrainingHistoryService ths = new TrainingHistoryService();
	private BuyerService bs = new BuyerService();

	public MembershipService() {
		super();
	}
	
	public boolean addMemebership(Membership membership) throws IOException{
		return mr.addMemebership(membership);
	}
	
	public String editMembership(Membership membership, Membership selectedMembership) throws IOException{
		return mr.editMembership(membership, selectedMembership);
	}
	
	public boolean deleteMembership(Membership membership) throws IOException{
		return mr.deleteMembership(membership);
	}
	
	public Membership findMembership(String id) throws IOException{
		return mr.findMembership(id);
	}
	
	public List<Membership> findAllMemberships() throws IOException{
		return mr.findAllMemberships();
	}
	
	public void checkDailyLogs() throws IOException {
		List<Membership> memberships = mr.findAllMemberships();
		if(memberships == null) {
			return;
		}
		Calendar calendar = Calendar.getInstance();
		Date now = calendar.getTime();
		for (Membership membership : memberships) {
				if(getZeroTimeDate(membership.getLastChecked()).before(getZeroTimeDate(now))) {
					if(membership.getMembershipType().toString().equals("MONTHLY")) {
						membership.setDailyLogs(3);
					} else {
						membership.setDailyLogs(10);
					}
				}
				membership.setLastChecked(now);
		}
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		FileWriter fileWriter = new FileWriter("./data/memberships.json");
		gson.toJson(memberships, fileWriter);
		fileWriter.close();
	}
	
	public boolean canTrainToday (String buyer) throws IOException {
		for (Membership membership : mr.findAllMemberships()) {
			if(membership.getBuyer().equals(buyer) && membership.getMembershipStatus().toString().equals("ACTIVE") && membership.getDailyLogs() > 0) {
				return true;
			}
		}
		return false;
	}
	
	public void doOneTraining(String buyer) throws IOException {
		List<Membership> memberships = mr.findAllMemberships();
		for (Membership membership : memberships) {
			if(membership.getBuyer().equals(buyer) && membership.getMembershipStatus().toString().equals("ACTIVE")) {
				membership.setDailyLogs(membership.getDailyLogs() - 1);
			}
		}
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		FileWriter fileWriter = new FileWriter("./data/memberships.json");
		gson.toJson(memberships, fileWriter);
		fileWriter.close();
	}
	
	public void checkMembershipsValidity () throws IOException {
		List<Membership> memberships = mr.findAllMemberships();
		if(memberships == null) {
			return;
		}
		Calendar calendar = Calendar.getInstance();
		Date now = calendar.getTime();
		for (Membership membership : memberships) {
			if(membership.getMembershipStatus().toString().equals("ACTIVE")) {
				if(membership.getActive().before(now)) {
					membership.setMembershipStatus(MembershipStatus.valueOf("INACTIVE"));
					int numberOfUsedLogs = ths.findTrainingHistoriesForBuyer(membership.getBuyer()).size();
					if(numberOfUsedLogs < membership.getTotalLogs() / 3) {
						int points = -((membership.getPrice() / 1000) * 10 * 4);
						bs.addPointsToBuyer(membership.getBuyer(), points);
						if(bs.findBuyer(membership.getBuyer()).getPoints() >= 0 && bs.findBuyer(membership.getBuyer()).getPoints() <= 3942) {
							bs.changeBuyerType(membership.getBuyer(), BType.valueOf("BRONZE"));
						} else if (bs.findBuyer(membership.getBuyer()).getPoints() > 3942 && bs.findBuyer(membership.getBuyer()).getPoints() < 7884) {
							bs.changeBuyerType(membership.getBuyer(), BType.valueOf("SILVER"));
						} else if (bs.findBuyer(membership.getBuyer()).getPoints() >= 7884) {
							bs.changeBuyerType(membership.getBuyer(), BType.valueOf("GOLD"));
						}
						Gson gson = new GsonBuilder().setPrettyPrinting().create();
						FileWriter fileWriter = new FileWriter("./data/memberships.json");
						gson.toJson(memberships, fileWriter);
						fileWriter.close();
						return;
					}
					int points = (membership.getPrice()/1000) * numberOfUsedLogs;
					bs.addPointsToBuyer(membership.getBuyer(), points);
					if(bs.findBuyer(membership.getBuyer()).getPoints() > 0 && bs.findBuyer(membership.getBuyer()).getPoints() <= 3942) {
						bs.changeBuyerType(membership.getBuyer(), BType.valueOf("BRONZE"));
					} else if (bs.findBuyer(membership.getBuyer()).getPoints() > 3942 && bs.findBuyer(membership.getBuyer()).getPoints() < 7884) {
						bs.changeBuyerType(membership.getBuyer(), BType.valueOf("SILVER"));
					} else if (bs.findBuyer(membership.getBuyer()).getPoints() >= 7884) {
						bs.changeBuyerType(membership.getBuyer(), BType.valueOf("GOLD"));
					}
				}
			}
		}
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		FileWriter fileWriter = new FileWriter("./data/memberships.json");
		gson.toJson(memberships, fileWriter);
		fileWriter.close();
	}
	
	private static Date getZeroTimeDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		date = calendar.getTime();
		return date;
	}
}
