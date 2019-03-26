package afuentes.feedreader.services.impl;

import java.util.Arrays;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import afuentes.feedreader.models.RSS;
import afuentes.feedreader.services.IFeedRequestService;

@Service
public class FeedRequestService implements IFeedRequestService {
	
	private static final String USER_AGENT = "user-agent";
	
	private static final String USER_AGENT_VALUE = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36"; 

	@Override
	public ResponseEntity<RSS> execute(String url) {
		RestTemplate restTemplate = new RestTemplateBuilder().build();
		HttpEntity<String> entity = new HttpEntity<>(null, this.getHeaders());
		ResponseEntity<RSS> feed = null;
		feed = restTemplate.exchange(url, HttpMethod.GET, entity, RSS.class);
		return feed;
	}
	
	private HttpHeaders getHeaders() {
		HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.ALL));
        headers.add(USER_AGENT, USER_AGENT_VALUE);
        return headers;
	}

}
