package com.snagallery.test.post.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.snagallery.test.common.FileManagerService;
import com.snagallery.test.post.dao.PostDAO;
import com.snagallery.test.post.model.Post;

@Service
public class PostBO {
	
	@Autowired
	private PostDAO postDAO;
	
	public int addPost(int userId, String topic, String title, String content, MultipartFile file) {
		
		
		String imagePath = null;
		if(file != null) {
			imagePath = FileManagerService.saveFile(userId, file);
			
			if(imagePath == null) {
				
				return 0;
			}
		
		}
		
		return postDAO.insertPost(userId, topic, title, content, imagePath);
	}
	
	
	public List<Post> getPostList(int userId) {
		return postDAO.selectPostList(userId);
			
	}
	
	
	public Post getPost(String topic) {
		return postDAO.selectPost(topic);
	}
	
	
	public int updatePost(int postId, String topic,  String title, String content) {
		return postDAO.updatePost(postId, topic, title, content);
	}
	
	
	public int deletePost(String topic) {
		
		
		Post post = postDAO.selectPost(topic);
		
		FileManagerService.removeFile(post.getImagePath());
		
		return postDAO.deletePost(topic);
	}
	
	
	
	

}