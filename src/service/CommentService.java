package service;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import model.Comment;
import repository.CommentRepository;

public class CommentService {

	private CommentRepository cr = new CommentRepository();
	
	
	
	public CommentService() {
		super();
	}

	public boolean addComment(Comment comment) throws IOException{
		return cr.addComment(comment);
	}
	
	public boolean editComment(Comment comment, Comment selectedComment) throws IOException{
		return cr.editComment(comment, selectedComment);
	}
	
	public boolean deleteComment(int id) throws IOException{
		return cr.deleteComment(id);
	}
	
	public Comment findComment(int id) throws IOException{
		return cr.findComment(id);
	}
	
	public List<Comment> findAllComments() throws IOException{
		return cr.findAllComments();
	}
	
	public void approveComment(int id) throws IOException {
		List<Comment> comments = findAllComments();
		for (Comment comment : comments) {
			if(comment.getId() == id) {
				comment.setApproved(true);
			}
		}
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		FileWriter fileWriter = new FileWriter("./data/comments.json");
		gson.toJson(comments, fileWriter);
		fileWriter.close();
	}
	
	public void disapproveComment(int id) throws IOException {
		List<Comment> comments = findAllComments();
		for (Comment comment : comments) {
			if(comment.getId() == id) {
				comment.setApproved(false);
			}
		}
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		FileWriter fileWriter = new FileWriter("./data/comments.json");
		gson.toJson(comments, fileWriter);
		fileWriter.close();
	}
	
}
