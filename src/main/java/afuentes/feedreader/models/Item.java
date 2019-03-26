package afuentes.feedreader.models;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import org.slf4j.LoggerFactory;
import afuentes.feedreader.utils.StringUtils;

@XmlRootElement(name="item")
@XmlAccessorType(XmlAccessType.FIELD)
public class Item {
	
	private String title;
	
	private String description;
	
	@XmlElement(namespace="http://purl.org/dc/elements/1.1/")
	private String creator;
	
	@XmlElement(name="pubDate")
	private String date;

	private String link;
	
	private String blogImg;
	
	private Enclosure enclosure;
	
	@XmlElement(namespace="http://purl.org/rss/1.0/modules/content/")
	private String encoded;
	
	@XmlElement(namespace="http://search.yahoo.com/mrss/")
	private Content content;

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

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Date getDate() {
		DateFormat format = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z", Locale.ENGLISH);
		Date dateObj = null;
		try {
			dateObj = format.parse(this.date);
		} catch (ParseException e) {
			LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
		}
		return dateObj;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getBlogImg() {
		return this.blogImg;
	}
	
	public Content getContent() {
		return content;
	}

	public void setContent(Content thumbnail) {
		this.content = thumbnail;
	}

	public String getItemImage() {
		String img = this.blogImg;
		if(StringUtils.isNullOrEmpty(img)) {
			if(this.enclosure != null && !StringUtils.isNullOrEmpty(this.enclosure.getUrl())) {
				img = this.enclosure.getUrl();
			}
			else if(this.content != null && !StringUtils.isNullOrEmpty(this.content.getUrl())) {
				img = this.content.getUrl();
			}
			else {
				img = this.getSrcImage(this.description);
				if(StringUtils.isNullOrEmpty(img)) {
					img = this.getSrcImage(this.encoded);
				}
			}
		}
		return img;
	}
	
	private String getSrcImage(String string) {
		if(!StringUtils.isNullOrEmpty(string) && string.contains("<img")) {
			string = string.substring(string.indexOf("<img"));
			string = string.substring(string.indexOf("src")+5);
			string = string.substring(0, string.indexOf('\"'));
			if(string.contains("'")) {
				string = string.substring(0, string.indexOf('\''));
			}
		}
		else {
			string = null;
		}
		return string;
	}

	public void setBlogImg(String blogImg) {
		this.blogImg = blogImg;
	}

	public Enclosure getEnclosure() {
		return enclosure;
	}

	public void setEnclosure(Enclosure enclosure) {
		this.enclosure = enclosure;
	}

	public String getEncoded() {
		return encoded;
	}

	public void setEncoded(String encoded) {
		this.encoded = encoded;
	}
	
	public String getCompleteDescription() {
		String desc = this.getEncoded();
		if(StringUtils.isNullOrEmpty(desc)) {
			desc = this.description;
		}
		return desc;
	}

	@Override
	public String toString() {
		return "Item [title=" + title + ", description=" + description + ", creator=" + creator + ", date=" + date
				+ ", link=" + link + ", blogImg=" + blogImg + ", enclosure=" + enclosure + "]";
	}
	
}
