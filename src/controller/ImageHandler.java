package controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.UUID;

import javax.imageio.ImageIO;

public class ImageHandler {
	
	private static String filePath = "./static/passfit/static/";

	public ImageHandler() {
		super();
	}
	
	public String saveImage (String file) throws IOException {
		String[] base64Image = file.split(",");
		byte[] image = Base64.getDecoder().decode(base64Image[1]);
		BufferedImage img = ImageIO.read(new ByteArrayInputStream(image));
		String uniqueName = UUID.randomUUID().toString();
		String extension;
		switch(base64Image[0]) {
		case "data:image/jpeg;base64":
			extension = "jpeg";
			break;
		case "data:image/png;base64":
			extension = "png";
			break;
		default:
			extension = "jpg";
			break;
		}
		String relativePath = "/static/" + uniqueName + "." + extension;
		String imagePath = filePath + uniqueName + "." + extension;
		File outputFile = new File(imagePath);
		ImageIO.write(img, extension, outputFile);
		return relativePath;
	}
	
}
