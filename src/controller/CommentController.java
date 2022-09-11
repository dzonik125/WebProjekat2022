package controller;

import java.io.IOException;
import java.util.List;

import model.Comment;
import service.CommentService;

public class CommentController {
	
	private CommentService cs = new CommentService();

	public CommentController() {
		super();
	}
	
	public boolean addComment(Comment comment) throws IOException{
		return cs.addComment(comment);
	}
	
	public boolean editComment(Comment comment, Comment selectedComment) throws IOException{
		return cs.editComment(comment, selectedComment);
	}
	
	public boolean deleteComment(int id) throws IOException{
		return cs.deleteComment(id);
	}
	
	public Comment findComment(int id) throws IOException{
		return cs.findComment(id);
	}
	
	public List<Comment> findAllComments() throws IOException{
		return cs.findAllComments();
	}
	
	public void approveComment(int id) throws IOException {
		cs.approveComment(id);
	}
	
	public void disapproveComment(int id) throws IOException {
		cs.disapproveComment(id);
	}
}
