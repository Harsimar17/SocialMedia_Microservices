package com.clone.DTOs;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class PostDto {
	
	@JsonIgnore
	int postId;
	String title;
	String content;
	String imagename;
	Date date;
	CategoryDto category;
	UserDto user;

	List<LikeDto> likes = new ArrayList<>();
	List<CommentDto> comments = new ArrayList<>();

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImagename() {
		return imagename;
	}

	public void setImagename(String imagename) {
		this.imagename = imagename;
	}

	public Date getDate() {
		return date;
	}

	public CategoryDto getCategory() {
		return category;
	}

	public void setCategory(CategoryDto category) {
		this.category = category;
	}

	public List<CommentDto> getComments() {
		return comments;
	}

	public void setComments(List<CommentDto> comments) {
		this.comments = comments;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public UserDto getUser() {
		return user;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}

	public List<LikeDto> getLikes() {
		return likes;
	}

	public void setLikes(List<LikeDto> likes) {
		this.likes = likes;
	}

}