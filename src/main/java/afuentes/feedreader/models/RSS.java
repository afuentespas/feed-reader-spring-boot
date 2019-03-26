package afuentes.feedreader.models;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="rss")
public class RSS {
	
	private Channel channel;

	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}

}
