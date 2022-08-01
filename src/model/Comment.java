package model;

import java.io.IOException;

import repository.CommentRepository;

public class Comment {
	private int id;
	private Buyer buyer;
	private SportObject sportObject;
	private String text;
	private boolean deleted;
	private int grade;
	private CommentRepository cr;
	
	public Comment(Buyer buyer, SportObject sportObject, String text, int grade) throws IOException {
		super();
		cr = new CommentRepository();
		if(cr.findAllComments() == null) {
			id = 1;
		} else {
			id = cr.findAllComments().get(cr.findAllComments().size() - 1).getId() + 1;
		}
		this.buyer = buyer;
		this.sportObject = sportObject;
		this.text = text;
		this.grade = grade;
		this.deleted = false;
	}
	
	public Buyer getBuyer() {
		return buyer;
	}
	public void setBuyer(Buyer buyer) {
		this.buyer = buyer;
	}
	public SportObject getSportObject() {
		return sportObject;
	}
	public void setSportObject(SportObject sportObject) {
		this.sportObject = sportObject;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	
	
	
}
