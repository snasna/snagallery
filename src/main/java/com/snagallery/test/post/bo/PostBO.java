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
	
	public int addPost(int userId,  String title, String content, MultipartFile file) {
		
		
		String imagePath = null;
		if(file != null) {
			imagePath = FileManagerService.saveFile(userId, file);
			
			if(imagePath == null) {
				
				return 0;
			}
		
		}
		
		return postDAO.insertPost(userId,  title, content, imagePath);
	}
	
	
	public List<Post> getPostList(int userId) {
		return postDAO.selectPostList(userId);
			
	}
	
	
	public Post getPost(int id) {
		return postDAO.selectPost(id);
	}
	
	
	public int updatePost(int postId,  String title, String content) {
		return postDAO.updatePost(postId,  title, content);
	}
	
	
	public int deletePost(int postId) {
		
		
		Post post = postDAO.selectPost(postId);
		
		FileManagerService.removeFile(post.getImagePath());
		
		return postDAO.deletePost(postId);
	}
	
	
	
	

}