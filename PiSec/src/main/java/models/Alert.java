package models;

import java.io.*;
import java.util.Base64;
import java.util.Date;

public class Alert {
	private final long camId;
	private final String message;
	private final String type;
	private final String dateTime;
	private String imagePath;
	private String encodedImage;

	public Alert(String dateTime, String recording) {
		this.camId = 0L;
		this.message = "";
		this.type = "motion";
		this.dateTime = dateTime;
		this.imagePath = recording;
	}

	public void encodeImage() {
		try {
			int bufLength = 2048;
			byte[] buffer = new byte[2048];
			byte[] data;
			FileInputStream stream = new FileInputStream(imagePath);
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			int readLength;
			while ((readLength = stream.read(buffer, 0, bufLength)) != -1) {
				out.write(buffer, 0, readLength);
			}

			data = out.toByteArray();
			encodedImage = Base64.getEncoder().withoutPadding().encodeToString(data);

			out.close();
			stream.close();
		} catch (IOException e) {
			encodedImage = "";
		}
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public Long getFromId() {return camId;}
	public String getDate() {return dateTime;}
	public String getType() {return type;}
	public String getMessage() {return message;}
	public String getImage() {
		if (encodedImage == null) encodeImage();
		return encodedImage;}
}
