package model;

public class Training {
	private String name;
	private TrainingType trainingType;
	private SportObject sportObject;
	private long duration;
	private String description;
	private String imageLocation;
	private boolean deleted;
	private transient Coach coach;
	public Training(String name, TrainingType trainingType, SportObject sportObject, long duration,
			String description, String image) {
		super();
		this.name = name;
		this.trainingType = trainingType;
		this.sportObject = sportObject;
		this.duration = duration;
		this.description = description;
		this.imageLocation = image;
		this.deleted = false;
	}
	public Training(String name, TrainingType trainingType, SportObject sportObject, long duration,
			String description, String image, Coach coach) {
		super();
		this.name = name;
		this.trainingType = trainingType;
		this.sportObject = sportObject;
		this.duration = duration;
		this.description = description;
		this.imageLocation = image;
		this.coach = coach;
		this.deleted = false;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public TrainingType getTrainingType() {
		return trainingType;
	}
	public void setTrainingType(TrainingType trainingType) {
		this.trainingType = trainingType;
	}
	public SportObject getSportObject() {
		return sportObject;
	}
	public void setSportObject(SportObject sportObject) {
		this.sportObject = sportObject;
	}
	public long getDuration() {
		return duration;
	}
	public void setDuration(long duration) {
		this.duration = duration;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImageLocation() {
		return imageLocation;
	}
	public void setImageLocation(String imageLocation) {
		this.imageLocation = imageLocation;
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
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	
}
