package models;

import java.io.*;
import java.util.Base64;
import java.util.Date;

public class Alert {
	private final int id;
	private final long camId;
	private final String message;
	private final String type;
	private final Date dateTime;
	private String imagePath;
	private String encodedImage;

	public Alert(int id, Date dateTime, String imagePath) {
		this.id = id;
		this.camId = 0L;
		this.message = "";
		this.type = "motion";
		this.dateTime = dateTime;
		this.imagePath = imagePath;
	}

	public Alert(int id, Date dateTime) {
		this(id, dateTime, null);
	}

	public void encodeImage() {
		String home = System.getProperty("user.home");
		try (FileInputStream stream = new FileInputStream(home + "/Pictures/" + imagePath);
		     ByteArrayOutputStream out = new ByteArrayOutputStream()){
			int bufLength = 2048;
			byte[] buffer = new byte[2048];
			byte[] data;
			int readLength;
			while ((readLength = stream.read(buffer, 0, bufLength)) != -1) {
				out.write(buffer, 0, readLength);
			}

			data = out.toByteArray();
			encodedImage = Base64.getEncoder().withoutPadding().encodeToString(data);
		} catch (IOException e) {
			e.printStackTrace();
			encodedImage = "";
		}
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public int getId() {return id;}
	public Long getFromId() {return camId;}
	public Date getDateTime() {return dateTime;}
	public String getType() {return type;}
	public String getMessage() {return message;}
	public String getImage() {
		if (encodedImage == null && imagePath != null) encodeImage();
		return encodedImage;}
}
