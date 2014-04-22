package solution;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(value = XmlAccessType.FIELD)
public class Organizers {
	@XmlElement(name="organizer")
	List<Organizer> organizers;
	public List<Organizer> getOrganizers() {
		return organizers;
	}
	public void setOrganizers(List<Organizer> organizers) {
		this.organizers = organizers;
	}
	public Organizers() {
		super();
	}
}
