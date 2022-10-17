package com.snagallery.test.post.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.snagallery.test.post.model.Post;

@Repository
public interface PostDAO {
	
	public int insertPost(
			@Param("userId") int userId
			, @Param("content") String content
			, @Param("imagePath") String imagePath);
	
	public Post selectPost(@Param("postId") int postId);
	
	public int deletePost(@Param("postId") int postId, @Param("userId") int userId);
	

	public List<Post> selectPostList();
	
	public Post selectPostByIdAndUserId(@Param("id") int id, @Param("userId") int userId);
	
	public int deletePost(@Param("postId") int postId);

}
