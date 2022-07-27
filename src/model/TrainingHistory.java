package model;

import java.time.LocalDateTime;

public class TrainingHistory {
	private LocalDateTime apply;
	private Training training;
	private Buyer buyer;
	private Coach coach;
	
	public TrainingHistory(LocalDateTime apply, Training training, Buyer buyer, Coach coach) {
		super();
		this.apply = apply;
		this.training = training;
		this.buyer = buyer;
		this.coach = coach;
	}

	public TrainingHistory(LocalDateTime apply, Training training, Buyer buyer) {
		super();
		this.apply = apply;
		this.training = training;
		this.buyer = buyer;
	}
}
