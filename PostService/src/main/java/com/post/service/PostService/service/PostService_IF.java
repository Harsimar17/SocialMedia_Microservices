package com.post.service.PostService.service;

import java.util.List;

import com.coreresources.required.PostDto;

public interface PostService_IF {
	PostDto createPost(PostDto pt, int uid, int cid) throws Exception;

	PostDto updatePost(PostDto pt, int id, String jwtToken);

	void deletePost(int id);

	PostDto getPost(int id, String jwtToken);

	List<PostDto> getAllPost(String jwtToken);

	List<PostDto> getPostByCategory(int cid, String jwtToken);

	List<PostDto> getAllPostByUser(int uid, String jwtToken);

	List<PostDto> searchPost(String key);
}
