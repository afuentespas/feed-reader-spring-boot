package afuentes.feedreader.services;

import org.springframework.http.ResponseEntity;
import afuentes.feedreader.models.RSS;


public interface IFeedRequestService {

	public ResponseEntity<RSS> execute(String url);
	
}
