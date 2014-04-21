package solution;

import java.util.Date;
import java.util.List;

public class Sheet {
	private String name;
	private String description;
	private Date when;
	private Address where;
	private Organizer organizer;
	private List<TimeSlot> timeSlots;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getWhen() {
		return when;
	}
	public void setWhen(Date when) {
		this.when = when;
	}
	public Address getWhere() {
		return where;
	}
	public void setWhere(Address where) {
		this.where = where;
	}
	public Organizer getOrganizer() {
		return organizer;
	}
	public void setOrganizer(Organizer organizer) {
		this.organizer = organizer;
	}
	public List<TimeSlot> getTimeSlots() {
		return timeSlots;
	}
	public void setTimeSlots(List<TimeSlot> timeSlots) {
		this.timeSlots = timeSlots;
	}
	public Sheet() {
		super();
	}
}
