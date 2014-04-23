package solution;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@XmlRootElement
@XmlAccessorType(value = XmlAccessType.FIELD)
public class Slot {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@XmlAttribute
	private int id;
	@Temporal(TemporalType.DATE)
	@XmlAttribute
	private Date slotDate;
	@XmlAttribute
	private String who;
	@XmlAttribute
	private String notes;
	@ManyToOne
	@JoinColumn(name="sheet_id")
	@XmlTransient
	private Sheet sheet;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public Slot() {
		super();
	}
	public Date getSlotDate() {
		return slotDate;
	}
	public void setSlotDate(Date slotDate) {
		this.slotDate = slotDate;
	}
}
