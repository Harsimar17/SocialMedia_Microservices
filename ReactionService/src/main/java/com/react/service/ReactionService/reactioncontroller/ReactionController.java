package com.react.service.ReactionService.reactioncontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clone.DTOs.CommentDto;
import com.clone.DTOs.LikeDto;
import com.react.service.ReactionService.reactionservice.ReactionService_IF;

@RestController
@RequestMapping("/reaction-service")
public class ReactionController 
{
    @Autowired
    ReactionService_IF reactionService;

    @PutMapping("/addLike/{userId}/{postId}")
    public ResponseEntity<String> addLikes(@PathVariable("postId") int postId, @PathVariable("userId") int userId)
    {
    	reactionService.addLike(new LikeDto(String.valueOf(0), String.valueOf(userId), String.valueOf(postId)));
        return new ResponseEntity<String>("You liked this post", HttpStatus.OK);
    }

    @GetMapping("/getLikes/{postId}")
    public ResponseEntity<String> getLikes(@PathVariable("postId") String postId) {
        return new ResponseEntity<String>(reactionService.getLikesOfPost(postId), HttpStatus.OK);
    }

    @GetMapping("/checkIfPostIsLikedByUser/{postId}/{userId}")
    public ResponseEntity<Boolean> isLiked(@PathVariable("postId") String postId, @PathVariable("userId") String userId) {
        return new ResponseEntity<Boolean>(reactionService.isLiked(postId, userId), HttpStatus.OK);
    }
    
    @PostMapping("/create/{userId}/{postId}")
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDetails, @PathVariable("postId") String postId, @PathVariable("userId") String userId) {
        return new ResponseEntity<CommentDto>(reactionService.createComment(commentDetails, postId, userId), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{commentId}")
    public void deleteComment(@PathVariable("commentId") String commentId) {
        reactionService.deleteComment(commentId);
    }
}
