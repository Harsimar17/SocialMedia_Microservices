package com.react.service.ReactionService.reactionserviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.react.service.ReactionService.reactionservice.ReactionService_IF;
import com.serviceclient.services.PostService;
import com.socialmediaapp.required.CommentDto;
import com.socialmediaapp.required.LikeDto;
import com.socialmediaapp.required.PostDto;

@Service
public class ReactionServiceImpl implements ReactionService_IF{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public CommentDto createComment(CommentDto commentDetails, String pid, String uid) {
		
		try
		{
			String sql = "INSERT INTO comment (comment, p_id, u_id) VALUES (?, ?, ?)";
			
			int isCommentAdded = jdbcTemplate.update(sql, commentDetails.getCommentContent(), commentDetails.getPostAssociatedWithComment(), commentDetails.getUserAssociatedWithComment());
			
			if (isCommentAdded > 0) 
			{
				return commentDetails;
			}
		}
		catch (Exception e) 
		{
			throw e;
		}
		return null;
	}

	@Override
	public void deleteComment(String commentId)
	{
		try 
		{
			String sql = "DELETE FROM comment WHERE id = ?";
			
			jdbcTemplate.update(sql, commentId);
		}
		catch (Exception e) 
		{
			throw e;
		}
		
	}

	@Override
	public List<LikeDto> getLikesOfPost(String postId) 
	{
		List<LikeDto> likesOfPost = null;
		
		try 
		{
			 String sql = "SELECT * FROM `post_likes` WHERE pid = ?";
				likesOfPost = jdbcTemplate.query(sql, new Object[] { postId }, (rs, rowNum) -> {
					LikeDto like = new LikeDto();
					like.setUserAssociatedWithLike(rs.getString("uid"));
					like.setPostAssociatedWithLike(rs.getString("pid"));
					return like;
				});
			
		}
		catch (Exception e)
		{
			throw e;
		}
		
		if(likesOfPost != null) 
		{
			return likesOfPost;
		}
		
		return null;
	}
	
	@Override
	public boolean isLiked(String userId, String postId) 
	{
		try 
		{
			boolean isLiked = false;
			
			PostService postService = new PostService();
			PostDto post = postService.getPostByPostId(postId);
			
			List<LikeDto> allLikesOfPost = post.getLikes();
			
			for(LikeDto like : allLikesOfPost) 
			{
				if(like.getUserAssociatedWithLike().equals(userId)) 
				{
					isLiked = true;
					break;
				}
			}
			return isLiked;
		}
		catch (Exception e)
		{
			throw e;
		}
	}

	@Override
	public void addLike(LikeDto likeDetails) {

		try 
		{
			String userAssociatedWithLike = likeDetails.getUserAssociatedWithLike();
			String postAssociatedWithLike = likeDetails.getPostAssociatedWithLike();
			
			if(!isLiked(userAssociatedWithLike,postAssociatedWithLike))
			{
		        String insertSql = "INSERT INTO post_likes (uid, pid) VALUES (?, ?)";
		        jdbcTemplate.update(insertSql, likeDetails.getUserAssociatedWithLike(), likeDetails.getPostAssociatedWithLike());
		    } 
			else
			{
		        String deleteSql = "DELETE FROM post_likes WHERE uid = ? AND pid = ?";
		        jdbcTemplate.update(deleteSql, userAssociatedWithLike, postAssociatedWithLike);
		    }
		}
		catch (Exception e)
		{
			throw e;
		}
	}



}
