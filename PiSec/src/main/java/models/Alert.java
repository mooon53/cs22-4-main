package models;

import java.util.Date;

public class Alert {
	private long camId;
	private String message;
	private String type;
	private String dateTime;
	private String recordingPath;

	public Alert(String dateTime, String recording) {
		this.camId = 0L;
		this.message = "";
		this.type = "motion";
		this.dateTime = dateTime;
		this.recordingPath = recording;
	}

	public Long getFromId() {return camId;}
	public String getDate() {return dateTime;}
	public String getType() {return type;}
	public String getMessage() {return message;}
	public String getRecordingPath() {return recordingPath;}
}
