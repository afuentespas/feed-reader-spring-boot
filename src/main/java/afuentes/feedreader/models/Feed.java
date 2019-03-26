package afuentes.feedreader.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Feed {

	@JsonProperty
	private String url;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
}
