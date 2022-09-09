package controller;

import java.io.IOException;
import java.util.List;

import model.Membership;
import service.MembershipService;

public class MembershipController {

	private MembershipService ms = new MembershipService();

	public MembershipController() {
		super();
	}
	
	public boolean addMemebership(Membership membership) throws IOException{
		return ms.addMemebership(membership);
	}
	
	public String editMembership(Membership membership, Membership selectedMembership) throws IOException{
		return ms.editMembership(membership, selectedMembership);
	}
	
	public boolean deleteMembership(Membership membership) throws IOException{
		return ms.deleteMembership(membership);
	}
	
	public Membership findMembership(String id) throws IOException{
		return ms.findMembership(id);
	}
	
	public List<Membership> findAllMemberships() throws IOException{
		return ms.findAllMemberships();
	}
	
	public void checkDailyLogs() throws IOException {
		ms.checkDailyLogs();
	}
	
	public boolean canTrainToday (String buyer) throws IOException {
		return ms.canTrainToday(buyer);
	}
	
	public void doOneTraining(String buyer) throws IOException {
		ms.doOneTraining(buyer);
	}
	
	public void checkMembershipsValidity () throws IOException {
		ms.checkMembershipsValidity();
	}
}
