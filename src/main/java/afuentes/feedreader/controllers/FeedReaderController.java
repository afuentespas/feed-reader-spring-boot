package afuentes.feedreader.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import afuentes.feedreader.models.RSS;
import afuentes.feedreader.services.IFeedService;

@Controller
public class FeedReaderController {
	
	@Autowired
	private IFeedService feedService;

	@RequestMapping(value = "/")
	public String index(Model model) {
		List<RSS> feed = this.feedService.getAllFeeds();
		model.addAttribute("feed", feed);
		return "index";
	}
	
}
