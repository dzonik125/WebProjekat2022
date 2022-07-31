package service;

import java.io.IOException;
import java.util.List;

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
	
}
