package models;

import java.util.Date;

public class Alert {
	private long alertId;
	private Date dateTime;
	private long recordId;

	public Alert(long alertId, Date dateTime, long recordId) {
		this.alertId = alertId;
		this.dateTime = dateTime;
		this.recordId = recordId;
	}

	public long getAlertId() {return alertId;}

	public void setAlertId(long alertId) {this.alertId = alertId;}

	public Date getDateTime() {return dateTime;}

	public void setDateTime(Date dateTime) {this.dateTime = dateTime;}

	public long getRecordId() {return recordId;}

	public void setRecordId(long recordId) {this.recordId = recordId;}
}
