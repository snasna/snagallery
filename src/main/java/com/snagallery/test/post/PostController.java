package com.snagallery.test.post;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PostController {

	@GetMapping("/post/input/view")
	public String inputView() {
		
		return "post/input";
	}
	
	@GetMapping("/post/community/view")
	public String communityView() {
		return "post/community";
	}
	
	@GetMapping("/post/mein/view")
	public String meinView() {
		return "post/mein";
	}
	
	@GetMapping("/post/notice/view")
	public String noticeView() {
		return "post/notice";
	}
	
	@GetMapping("/post/new/view")
	public String newView() {
		return "post/new";
	}
}
