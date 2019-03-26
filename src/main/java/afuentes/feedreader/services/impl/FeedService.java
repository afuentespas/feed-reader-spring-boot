package afuentes.feedreader.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import afuentes.feedreader.components.FeedProperties;
import afuentes.feedreader.models.RSS;
import afuentes.feedreader.services.IFeedRequestService;
import afuentes.feedreader.services.IFeedService;

@Service
public class FeedService implements IFeedService {

	@Autowired
	private FeedProperties feedProperties;
	
	@Autowired
	private IFeedRequestService feedRequestService;
	
	@Override
	public List<RSS> getAllFeeds() {
		List<RSS> feed = new ArrayList<>();
		List<String> urls = this.feedProperties.getFeed();
		if(!urls.isEmpty()) {
			List<ResponseEntity<RSS>> response = urls.stream().map(url -> this.feedRequestService.execute(url)).collect(Collectors.toList());
			response.stream().forEach(rss -> feed.add(rss.getBody()));
		}
		return feed;
	}

}
