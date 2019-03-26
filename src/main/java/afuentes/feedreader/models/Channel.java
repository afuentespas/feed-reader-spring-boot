package afuentes.feedreader.models;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="channel")
@XmlAccessorType(XmlAccessType.FIELD)
public class Channel {
	
	@XmlElement(name="title")
	private String title;
	
	@XmlElement(name="description")
	private String description;
	
	@XmlElement(name="image")
	private Image image;

	@XmlElement(name="item")
	private List<Item> items;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}
	
}
