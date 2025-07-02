package com.react.service.ReactionService.reactionservice;

import java.util.List;

import com.coreresources.required.CommentDto;
import com.coreresources.required.LikeDto;

public interface ReactionService_IF {
	
	CommentDto createComment(CommentDto ct, String pid, String uid,  String jwtToken);

    void deleteComment(String cid);
    
    List<LikeDto> getLikesOfPost(String pid, String jwtToken);

    void addLike(LikeDto l,  String jwtToken);
    
    boolean isLiked(String pid, String uid,  String jwtToken) ;
}
