package com.react.service.ReactionService.reactionservice;

import java.util.List;

import com.socialmediaapp.required.CommentDto;
import com.socialmediaapp.required.LikeDto;

public interface ReactionService_IF {
	
	CommentDto createComment(CommentDto ct, String pid, String uid);

    void deleteComment(String cid);
    
    List<LikeDto> getLikesOfPost(String pid);

    void addLike(LikeDto l);
    
    boolean isLiked(String pid, String uid) ;
}
