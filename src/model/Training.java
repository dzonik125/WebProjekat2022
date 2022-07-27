package model;

import java.awt.Image;
import java.time.LocalTime;

import javaxt.utils.string;

public class Training {
	private string name;
	private TrainingType trainingType;
	private SportObject sportObject;
	private LocalTime duration;
	private string description;
	private Image image;
	private Coach coach;
	public Training(string name, TrainingType trainingType, SportObject sportObject, LocalTime duration,
			string description, Image image) {
		super();
		this.name = name;
		this.trainingType = trainingType;
		this.sportObject = sportObject;
		this.duration = duration;
		this.description = description;
		this.image = image;
	}
	public Training(string name, TrainingType trainingType, SportObject sportObject, LocalTime duration,
			string description, Image image, Coach coach) {
		super();
		this.name = name;
		this.trainingType = trainingType;
		this.sportObject = sportObject;
		this.duration = duration;
		this.description = description;
		this.image = image;
		this.coach = coach;
	}
	
	
	
	
}
