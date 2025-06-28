package com.react.service.ReactionService.reactionservice;

import com.clone.DTOs.CommentDto;
import com.clone.DTOs.LikeDto;

public interface ReactionService_IF {
	
	CommentDto createComment(CommentDto ct, String pid, String uid);

    void deleteComment(String cid);
    
    String getLikesOfPost(String pid);

    void addLike(LikeDto l);
    
    boolean isLiked(String pid, String uid) ;
}
