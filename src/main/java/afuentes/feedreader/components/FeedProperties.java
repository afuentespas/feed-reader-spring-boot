package afuentes.feedreader.components;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import afuentes.feedreader.models.Feed;

@Component
public class FeedProperties {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	public List<String> getFeed() {
		ObjectMapper mapper = new ObjectMapper();
		TypeReference<List<Feed>> mapType = new TypeReference<List<Feed>>() {};
		InputStream is = null;
		List<Feed> feeds = null;
		List<String> listUrl = null;
		try {
			is = new ClassPathResource("feed-urls.json").getInputStream();	
			feeds = mapper.readValue(is, mapType);
			listUrl = feeds.stream().map(Feed::getUrl).collect(Collectors.toList());
		} catch (IOException e) {
			log.error(e.getMessage());
		}
		return listUrl;
	}
	
}
