package com.snagallery.test.post.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.snagallery.test.post.model.Post;

@Repository
public interface PostDAO {
	
	public int insertPost(
			@Param("userId") int userId
			, @Param("topic") String topic
			, @Param("title") String title
			, @Param("content") String content
			, @Param("imagePath") String imagePath);
	
	public List<Post> selectPostList(@Param("userId") int userId);
	
	public Post selectPost(@Param("topic") String topic);
	
	public int updatePost(
			@Param("postId") int postId
			, @Param("topic") String topic
			, @Param("title") String title
			, @Param("content") String content);
	
	public int deletePost(@Param("topic") String topic);

}
