package com.react.service.ReactionService.reactionserviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.coreresources.required.CommentDto;
import com.coreresources.required.LikeDto;
import com.coreresources.required.PostDto;
import com.react.service.ReactionService.reactionservice.ReactionService_IF;
import com.serviceclient.services.HeaderUtil;
import com.serviceclient.services.PostService;

@Service
public class ReactionServiceImpl implements ReactionService_IF{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private PostService postService;

	@Override
	public CommentDto createComment(CommentDto commentDetails, String pid, String uid,  String jwtToken) {
		
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
	public List<LikeDto> getLikesOfPost(String postId,  String jwtToken) 
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
	public boolean isLiked(String userId, String postId, String jwtToken) 
	{
		try 
		{
			boolean isLiked = false;
			
			PostDto post = postService.getPostByPostId(postId, jwtToken);
			
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
	public void addLike(LikeDto likeDetails,  String jwtToken) {

		try 
		{
			String userAssociatedWithLike = likeDetails.getUserAssociatedWithLike();
			String postAssociatedWithLike = likeDetails.getPostAssociatedWithLike();
			
			if(!isLiked(userAssociatedWithLike,postAssociatedWithLike, jwtToken))
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
