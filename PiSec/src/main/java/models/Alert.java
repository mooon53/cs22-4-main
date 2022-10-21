package models;

import java.util.Date;

public class Alert {
	private long alertId;
	private String dateTime;
	private long recordId;
	private String recording;

	public Alert(long alertId, String dateTime, String recording) {
		this.alertId = alertId;
		this.dateTime = dateTime;
		this.recording = recording;
	}

	public long getAlertId() {return alertId;}

	public void setAlertId(long alertId) {this.alertId = alertId;}

	public String getDateTime() {return dateTime;}

	public long getRecordId() {return recordId;}

	public void setRecordId(long recordId) {this.recordId = recordId;}
}
