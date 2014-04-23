package solution;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@XmlRootElement
@XmlAccessorType(value = XmlAccessType.FIELD)
public class Sheet {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@XmlAttribute
	private int id;
	@XmlAttribute
	private String name;
	@XmlAttribute
	private String description;
	@Temporal(TemporalType.DATE)
	@XmlAttribute
	private Date eventDate;
	@OneToOne(mappedBy="sheet")
	@XmlElement
	private Address where;
	@ManyToOne
	@JoinColumn(name="organizer_id")
	@XmlTransient
	private Organizer organizer;
	@OneToMany(mappedBy="sheet")
	@XmlElement(name="slot")
	private List<Slot> timeSlots = new ArrayList<Slot>();
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
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
	public List<Slot> getTimeSlots() {
		return timeSlots;
	}
	public void setTimeSlots(List<Slot> timeSlots) {
		this.timeSlots = timeSlots;
	}
	public Sheet() {
		super();
	}
	public Date getEventDate() {
		return eventDate;
	}
	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}
}
