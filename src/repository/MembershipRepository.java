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

import model.Membership;
import model.MembershipStatus;

public class MembershipRepository {
	
	private String fileLocation = "./data/memberships.json";

	public MembershipRepository() {
		super();
	}
	
	public boolean addMemebership(Membership membership) throws IOException {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		Type listType = new TypeToken<List<Membership>>(){}.getType();
		FileReader fileReader = new FileReader(fileLocation);
		List<Membership> memberships = gson.fromJson(fileReader, listType);
		fileReader.close();
		if(memberships == null) {
			memberships = new ArrayList<>();
		}
		
		for (Membership membership2 : memberships) {
			if(membership2.getBuyer().equals(membership.getBuyer()) && membership2.getMembershipStatus().toString().equals("ACTIVE")) {
				membership2.setMembershipStatus(MembershipStatus.valueOf("INACTIVE"));
			}
		}
		
		for (Membership membership2 : memberships) {
			if(membership2.getId().equals(membership.getId()) && !membership.isDeleted()) {
				return false;
			}
		}
		
		boolean added = memberships.add(membership);
		FileWriter fileWriter = new FileWriter(fileLocation);
		gson.toJson(memberships, fileWriter);
		fileWriter.close();
		if(added) {
			return true;
		}
		return false;
	}
	
	public String editMembership(Membership membership, Membership selectedMembership) throws IOException {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		Type listType = new TypeToken<List<Membership>>(){}.getType();
		FileReader fileReader = new FileReader(fileLocation);
		List<Membership> memberships = gson.fromJson(fileReader, listType);
		fileReader.close();
		for (Membership membership2 : memberships) {
			if(membership.getId().equals(selectedMembership.getId())) {
				break;
			}
			
			if(membership.getId().equals(membership2.getId())) {
				return "ID se ne moze promeniti u vec postojeci";
			}
		}
		
		for (Membership m : memberships) {
			if(m.getId().equals(selectedMembership.getId())) {
				m.setId(membership.getId());
				m.setMembershipType(membership.getMembershipType());
				m.setDueDate(membership.getDueDate());
				m.setActive(membership.getActive());
				m.setPrice(membership.getPrice());
				m.setBuyer(membership.getBuyer());
				m.setMembershipStatus(membership.getMembershipStatus());
				m.setDailyLogs(membership.getDailyLogs());
				FileWriter fileWriter = new FileWriter(fileLocation);
				gson.toJson(memberships, fileWriter);
				fileWriter.close();
				return "Uspesno izmenjeno";
			}
		}
		return "Ne postoji izabrani membership u listi membershipa";
	}
	
	public boolean deleteMembership(Membership membership) throws IOException {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		Type listType = new TypeToken<List<Membership>>(){}.getType();
		FileReader fileReader = new FileReader(fileLocation);
		List<Membership> memberships = gson.fromJson(fileReader, listType);
		fileReader.close();
		for (Membership m : memberships) {
			if(membership.getId().equals(m.getId())) {
				m.setDeleted(true);
				FileWriter fileWriter = new FileWriter(fileLocation);
				gson.toJson(memberships, fileWriter);
				fileWriter.close();
				return true;
			}
		}
		return false;
	}
	
	public Membership findMembership(String id) throws IOException {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		Type listType = new TypeToken<List<Membership>>(){}.getType();
		FileReader fileReader = new FileReader(fileLocation);
		List<Membership> memberships = gson.fromJson(fileReader, listType);
		fileReader.close();
		for (Membership membership : memberships) {
			if(membership.getId().equals(id)) {
				return membership;
			}
		}
		
		return null;
	}
	
	public List<Membership> findAllMemberships() throws IOException{
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		Type listType = new TypeToken<List<Membership>>(){}.getType();
		FileReader fileReader = new FileReader(fileLocation);
		List<Membership> memberships = gson.fromJson(fileReader, listType);
		fileReader.close();
		return memberships;
	}
	 
}
