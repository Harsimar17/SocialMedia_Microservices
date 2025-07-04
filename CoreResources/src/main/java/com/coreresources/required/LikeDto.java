package com.coreresources.required;

public class LikeDto {

	String likeId;
	String postAssociatedWithLike;
	String userAssociatedWithLike;

	public String getLikeId() {
		return likeId;
	}

	public void setLikeId(String likeId) {
		this.likeId = likeId;
	}

	public LikeDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LikeDto(String likeId, String userAssociatedWithLike,  String postAssociatedWithLike) {
		super();
		this.likeId = likeId;
		this.postAssociatedWithLike = postAssociatedWithLike;
		this.userAssociatedWithLike = userAssociatedWithLike;
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