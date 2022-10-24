package models;

import java.util.ArrayList;
import java.util.List;

public class Camera {
	private final long id;
	private String name;
	private boolean isLive;
	private List<Alert> alerts;
	private String imagePath;

	public Camera(long id, String name) {
		this.id = id;
		this.name = name;
		this.isLive = true;
		this.alerts = new ArrayList<>();
		this.imagePath = "images/office.png";
	}

	public long getId() {return id;}
	public String getName() {return name;}
	public boolean isLive() {return isLive;}
	public List<Alert> getNotifications() {return alerts;}
	public String getShowCaseImage() {return imagePath;}
}
