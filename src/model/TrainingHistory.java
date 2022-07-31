package model;

import java.util.Date;

public class TrainingHistory {
	private Date apply;
	private Training training;
	private Buyer buyer;
	private Coach coach;
	private boolean deleted;
	
	public TrainingHistory(Date apply, Training training, Buyer buyer, Coach coach) {
		super();
		this.apply = apply;
		this.training = training;
		this.buyer = buyer;
		this.coach = coach;
		this.deleted = false;
	}

	public TrainingHistory(Date apply, Training training, Buyer buyer) {
		super();
		this.apply = apply;
		this.training = training;
		this.buyer = buyer;
		this.deleted = false;
	}

	public Date getApply() {
		return apply;
	}

	public void setApply(Date apply) {
		this.apply = apply;
	}

	public Training getTraining() {
		return training;
	}

	public void setTraining(Training training) {
		this.training = training;
	}

	public Buyer getBuyer() {
		return buyer;
	}

	public void setBuyer(Buyer buyer) {
		this.buyer = buyer;
	}

	public Coach getCoach() {
		return coach;
	}

	public void setCoach(Coach coach) {
		this.coach = coach;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.deleted = isDeleted;
	}
	
	
	
}
