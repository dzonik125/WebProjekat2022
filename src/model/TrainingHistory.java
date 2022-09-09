package model;

import java.util.Date;

public class TrainingHistory {
	private Date apply;
	private Training training;
	private String buyer;
	private boolean deleted;

	public TrainingHistory(Date apply, Training training, String buyer) {
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

	public String getBuyer() {
		return buyer;
	}

	public void setBuyer(String buyer) {
		this.buyer = buyer;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.deleted = isDeleted;
	}
	
	
	
}
