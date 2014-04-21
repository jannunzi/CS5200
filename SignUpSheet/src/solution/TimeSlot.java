package solution;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class TimeSlot {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Date when;
	private String who;
	private String notes;
	private Sheet sheet;
	@Temporal(TemporalType.DATE)
	public Date getWhen() {
		return when;
	}
	public void setWhen(Date when) {
		this.when = when;
	}
	public String getWho() {
		return who;
	}
	public void setWho(String who) {
		this.who = who;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public Sheet getSheet() {
		return sheet;
	}
	public void setSheet(Sheet sheet) {
		this.sheet = sheet;
	}
	public TimeSlot() {
		super();
	}
}
