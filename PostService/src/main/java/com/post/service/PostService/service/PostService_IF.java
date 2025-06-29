package com.post.service.PostService.service;

import java.util.List;

import com.socialmediaapp.required.PostDto;

public interface PostService_IF {
	PostDto createPost(PostDto pt, int uid, int cid) throws Exception;

	PostDto updatePost(PostDto pt, int id);

	void deletePost(int id);

	PostDto getPost(int id);

	List<PostDto> getAllPost();

	List<PostDto> getPostByCategory(int cid);

	List<PostDto> getAllPostByUser(int uid);

	List<PostDto> searchPost(String key);
}
