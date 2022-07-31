package repository;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import model.Comment;

public class CommentRepository {
	private String fileLocation = "./data/comments.json";
	
	public boolean addComment(Comment comment) throws IOException {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		Type listType = new TypeToken<List<Comment>>(){}.getType();
		FileReader fileReader = new FileReader(fileLocation);
		List<Comment> comments = gson.fromJson(fileReader, listType);
		fileReader.close();
		if(comments == null) {
			comments = new ArrayList<>();
		}
		boolean added = comments.add(comment);
		FileWriter fileWriter = new FileWriter(fileLocation);
		gson.toJson(comments, fileWriter);
		fileWriter.close();
		return added;
	}
	
	public boolean editComment(Comment comment, Comment selectedComment) throws IOException {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		Type listType = new TypeToken<List<Comment>>(){}.getType();
		FileReader fileReader = new FileReader(fileLocation);
		List<Comment> comments = gson.fromJson(fileReader, listType);
		fileReader.close();
		for (Comment comment1 : comments) {
			if(comment1.getId() == selectedComment.getId()) {
				comment1.setText(comment.getText());
				comment1.setSportObject(comment.getSportObject());
				comment1.setBuyer(comment.getBuyer());
				comment1.setGrade(comment.getGrade());
				FileWriter fileWriter = new FileWriter(fileLocation);
				gson.toJson(comments, fileWriter);
				fileWriter.close();
				return true;
			}
		}
		return false;
	}
	
	public boolean deleteComment(int id) throws IOException {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		Type listType = new TypeToken<List<Comment>>(){}.getType();
		FileReader fileReader = new FileReader(fileLocation);
		List<Comment> comments = gson.fromJson(fileReader, listType);
		fileReader.close();
		for (Comment comment : comments) {
			if(comment.getId() == id) {
				comment.setDeleted(true);
				return true;
			}
		}
		return false;
	}
	
	public Comment findComment(int id) throws IOException {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		Type listType = new TypeToken<List<Comment>>(){}.getType();
		FileReader fileReader = new FileReader(fileLocation);
		List<Comment> comments = gson.fromJson(fileReader, listType);
		fileReader.close();
		for (Comment comment : comments) {
			if(comment.getId() == id) {
				return comment;
			}
		}
		return null;
	}
	
	public List<Comment> findAllComments() throws IOException {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		Type listType = new TypeToken<List<Comment>>(){}.getType();
		FileReader fileReader = new FileReader(fileLocation);
		List<Comment> comments = gson.fromJson(fileReader, listType);
		fileReader.close();
		return comments;
	}
	
}
