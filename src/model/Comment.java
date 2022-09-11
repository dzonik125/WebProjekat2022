package model;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import repository.CommentRepository;

public class Comment {
	private int id;
	private String buyer;
	private String sportObject;
	private String text;
	private boolean deleted;
	private int grade;
	private CommentRepository cr;
	private boolean approved;
	private Date created;
	
	public Comment(String buyer, String sportObject, String text, int grade) throws IOException {
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
		this.approved = false;
		Calendar calendar = Calendar.getInstance();
		this.created = calendar.getTime();
	}
	
	public String getBuyer() {
		return buyer;
	}
	public void setBuyer(String buyer) {
		this.buyer = buyer;
	}
	public String getSportObject() {
		return sportObject;
	}
	public void setSportObject(String sportObject) {
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

	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}
	
	
	
}
