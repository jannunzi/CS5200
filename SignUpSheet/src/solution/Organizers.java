package solution;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
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
