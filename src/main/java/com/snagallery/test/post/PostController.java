package com.snagallery.test.post;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.snagallery.test.post.bo.PostBO;
import com.snagallery.test.post.model.Post;

@Controller
@RequestMapping("/post")
public class PostController {

	@Autowired
	private PostBO postBO;
	
	@GetMapping("/input/view")
	public String inputView() {
		
		return "post/input";
	}
	
	@GetMapping("/community/view")
	public String community(HttpServletRequest request
				, Model model) {
			
			HttpSession session = request.getSession();
			int userId = (Integer)session.getAttribute("userId");
			
			List<Post> postList = postBO.getPostList(userId);
			
			model.addAttribute("postList", postList);	
		return "post/community";
	}
	
	@GetMapping("/mein/view")
	public String meinView() {
		return "post/mein";
	}
	
	@GetMapping("/notice/view")
	public String noticeView() {
		return "post/notice";
	}
	
	@GetMapping("/new/view")
	public String newView(HttpServletRequest request
			, Model model) {
		
		HttpSession session = request.getSession();
		int userId = (Integer)session.getAttribute("userId");
		
		List<Post> postList = postBO.getPostList(userId);
		
		model.addAttribute("postList", postList);
		return "post/new";
	}
	
	@GetMapping("/list/view")
	public String list(HttpServletRequest request
			, Model model) {
		
		HttpSession session = request.getSession();
		int userId = (Integer)session.getAttribute("userId");
		
		List<Post> postList = postBO.getPostList(userId);
		
		model.addAttribute("postList", postList);
		
		return "post/list";
	}
	
	@GetMapping("/create/view")
	public String snaInput() {
		return "post/input";
	}
	
	@GetMapping("/detail/view")
	public String snaDetail(@RequestParam("id") int id, Model model) {
		
		Post post = postBO.getPost(id);
		
		model.addAttribute("post", post);
		
		return "post/detail";
	}
}
