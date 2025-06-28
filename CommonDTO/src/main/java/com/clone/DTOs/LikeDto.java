package com.clone.DTOs;

public class LikeDto {

    public LikeDto(String likeId, String postAssociatedWithLike, String userAssociatedWithLike) {
		super();
		this.likeId = likeId;
		this.postAssociatedWithLike = postAssociatedWithLike;
		this.userAssociatedWithLike = userAssociatedWithLike;
	}
	String likeId;
    String postAssociatedWithLike;
    String userAssociatedWithLike;
    
    public String getLikeId() {
		return likeId;
	}
	public void setLikeId(String likeId) {
		this.likeId = likeId;
	}
	public String getPostAssociatedWithLike() {
		return postAssociatedWithLike;
	}
	public void setPostAssociatedWithLike(String postAssociatedWithLike) {
		this.postAssociatedWithLike = postAssociatedWithLike;
	}
	public String getUserAssociatedWithLike() {
		return userAssociatedWithLike;
	}
	public void setUserAssociatedWithLike(String userAssociatedWithLike) {
		this.userAssociatedWithLike = userAssociatedWithLike;
	}
	
}