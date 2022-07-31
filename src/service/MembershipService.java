package service;

import java.io.IOException;
import java.util.List;

import model.Membership;
import repository.MembershipRepository;

public class MembershipService {
	
	private MembershipRepository mr = new MembershipRepository();

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
}
