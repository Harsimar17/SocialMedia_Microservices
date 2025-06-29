package com.socialmediaapp.required;

public class CommentDto {

    String commentId;
    String postAssociatedWithComment;
    String userAssociatedWithComment;
    String commentContent;
    
    public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	public String getPostAssociatedWithComment() {
		return postAssociatedWithComment;
	}

	public void setPostAssociatedWithComment(String postAssociatedWithComment) {
		this.postAssociatedWithComment = postAssociatedWithComment;
	}

	public String getUserAssociatedWithComment() {
		return userAssociatedWithComment;
	}

	public void setUserAssociatedWithComment(String userAssociatedWithComment) {
		this.userAssociatedWithComment = userAssociatedWithComment;
	}

	public String getCommentId() {
		return commentId;
	}

	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}

	
}