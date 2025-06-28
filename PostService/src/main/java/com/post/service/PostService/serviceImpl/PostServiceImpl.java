package com.post.service.PostService.serviceImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.clone.DTOs.CategoryDto;
import com.clone.DTOs.CommentDto;
import com.clone.DTOs.LikeDto;
import com.clone.DTOs.PostDto;
import com.clone.DTOs.UserDto;
import com.post.service.PostService.service.PostService_IF;
import com.serviceclient.services.UserService;

@Service
public class PostServiceImpl implements PostService_IF{
	
	private static final String GET_POST_BY_CATEGORY = "SELECT p.*, c.id AS category_id, c.title AS category_title, c.description AS category_description FROM post p JOIN category c ON p.ct_id = c.id WHERE p.ct_id = ?";
	private static final String GET_POST_BY_USER_ID = "SELECT p.*, c.id AS category_id, c.title AS category_title, c.description AS category_description FROM post p JOIN category c ON p.ct_id = c.id WHERE p.u_id = ?";
	private static final String CREATE_POST = "INSERT INTO post (title, content, imagename, date, ct_id, u_id) VALUES (?, ?, ?, ?, ?, ?)";
	private static final String GET_POST_BY_PID = "SELECT p.*, c.id AS category_id, c.title AS category_title, c.description AS category_description FROM post p JOIN category c ON p.ct_id = c.id WHERE p.id = ?";
	private static final String GET_ALL_POST = "SELECT p.*, c.id AS category_id, c.title AS category_title, c.description AS category_description FROM post p JOIN category c ON p.ct_id = c.id";

	private PostDto mapPost(ResultSet rs) throws SQLException {
		PostDto post = new PostDto();
		post.setPostId(rs.getInt("id"));
		post.setTitle(rs.getString("title"));
		post.setContent(rs.getString("content"));
		post.setImagename(rs.getString("imagename"));
		post.setDate(rs.getTimestamp("date"));

		CategoryDto category = new CategoryDto();
		category.setId(rs.getInt("category_id"));
		category.setTitle(rs.getString("category_title"));
		category.setDescription(rs.getString("category_description"));
		post.setCategory(category);

		UserDto user = new UserDto();
		user.setId(rs.getInt("u_id")); // FIXED this line

		UserService userService = new UserService();
		user = userService.getUserByUserId(String.valueOf(user.getId()));

		post.setUser(user);

		return post;
	}
	
	private LikeDto mapLikes(ResultSet rs, PostDto postAssociatedWithLike) throws SQLException
	{
		LikeDto likeDetails = new LikeDto();
		likeDetails.setLikeId(rs.getString("lid"));

		String uId = rs.getString("uid");
		
		likeDetails.setUserAssociatedWithLike(uId);
		likeDetails.setPostAssociatedWithLike(String.valueOf(postAssociatedWithLike.getPostId()));

		return likeDetails;
	}
	
	private CommentDto mapComments(ResultSet rs, PostDto postAssociatedWithComment) throws SQLException {
		CommentDto commentDetails = new CommentDto();
		
		String uId = rs.getString("u_id");
		String commentId = rs.getString("id");
		String commentContent = rs.getString("comment");

		commentDetails.setCommentId(commentId);
		commentDetails.setUserAssociatedWithComment(uId);
		commentDetails.setPostAssociatedWithComment(String.valueOf(postAssociatedWithComment.getPostId()));
		commentDetails.setCommentContent(commentContent);
		
		return commentDetails;
	}
	

	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	@Override
	public PostDto createPost(PostDto post, int uid, int cid) throws Exception {
		// TODO Auto-generated method stub
		try 
		{
			int rowsInserted = jdbcTemplate.update(CREATE_POST,
					post.getTitle(),
					post.getContent(),
					post.getImagename(),
					new Timestamp(post.getDate().getTime()),
					post.getCategory().getId(),   
					post.getUser().getId()    
					);
			
			if (rowsInserted == 0)
			{
				throw new Exception("There is some error while creating a post.");
			}
			
		}
		catch (Exception e)
		{
			throw e;
		}
		
		return post;
	}

	
	@Override
	public PostDto updatePost(PostDto newPost, int postId) 
	{
		StringBuilder sql = new StringBuilder("UPDATE post SET ");
		
		PostDto existingPostFromDb = this.getPost(postId);
		try 
		{
			List<Object> params = new ArrayList<>();
	
			boolean isSomethingChange = false;
			
			if (!Objects.equals(newPost.getTitle(), existingPostFromDb.getTitle())) 
			{
				sql.append("title = ?, ");
				params.add(newPost.getTitle());
				isSomethingChange = true;
			}
	
			if (!Objects.equals(newPost.getContent(), existingPostFromDb.getContent()))
			{
				sql.append("content = ?, ");
				params.add(newPost.getContent());
				isSomethingChange = true;
			}
	
			if (!Objects.equals(newPost.getImagename(), existingPostFromDb.getImagename())) 
			{
				sql.append("imagename = ?, ");
				params.add(newPost.getImagename());
				isSomethingChange = true;
			}
	
			if (!Objects.equals(newPost.getDate(), existingPostFromDb.getDate())) 
			{
				sql.append("date = ?, ");
				params.add(new Timestamp(newPost.getDate().getTime()));
				isSomethingChange = true;
			}
	
			if (newPost.getCategory() != null && existingPostFromDb.getCategory() != null
					&& newPost.getCategory().getId() != existingPostFromDb.getCategory().getId()) 
			{
				sql.append("ct_id = ?, ");
				params.add(newPost.getCategory().getId());
				isSomethingChange = true;
			}
	
			if (newPost.getUser() != null && existingPostFromDb.getUser() != null
					&& newPost.getUser().getId() != existingPostFromDb.getUser().getId()) 
			{
				sql.append("u_id = ?, ");
				params.add(newPost.getUser().getId());
				isSomethingChange = true;
			}
	
	
			if(isSomethingChange) 
			{
				sql.setLength(sql.length() - 2);
				sql.append(" WHERE id = ?");
				params.add(postId);
	
				int updatedRecords = jdbcTemplate.update(sql.toString(), params.toArray());
				
				if (updatedRecords > 0) 
				{
					return this.getPost(postId);
				}
			}
		}
		catch (Exception e) 
		{
			throw e;
		}

		return existingPostFromDb;
	}

	@Override
	public void deletePost(int postId)
	{
		try 
		{
			 jdbcTemplate.update("DELETE FROM comment WHERE p_id = ?", postId);
			 jdbcTemplate.update("DELETE FROM post_likes WHERE pid = ?", postId);
			 jdbcTemplate.update("DELETE FROM post WHERE id = ?", postId);
		}
		catch (Exception e)
		{
			throw e;
		}
	}

	private void mapLikesAndComments(List<PostDto> rawPosts) 
	{
		for(PostDto postDetails : rawPosts) 
		{
			List<LikeDto> likes = jdbcTemplate.query("SELECT * FROM post_likes WHERE pid = ?", new Object[]{postDetails.getPostId()}, (rs, rowNum)->mapLikes(rs, postDetails));
			postDetails.setLikes(likes);

			List<CommentDto> comments = jdbcTemplate.query("SELECT * FROM comment WHERE p_id = ?", new Object[]{postDetails.getPostId()}, (rs, rowNum)->mapComments(rs, postDetails));
			postDetails.setComments(comments);
		}
	}
	
	@Override
	public PostDto getPost(int id) 
	{
		List<PostDto> postById = new ArrayList<>();
		
		try 
		{
			postById = jdbcTemplate.query(GET_POST_BY_PID, new Object[] { id }, (rs, rowNum) -> mapPost(rs));
			
			mapLikesAndComments(postById);
		}
		catch (Exception e)
		{
			throw e;
		}
		
		if (postById != null && postById.size() > 0) 
		{
			return postById.get(0);
		}
		
		return null;
	}

	@Override
	public List<PostDto> getAllPost() 
	{
		List<PostDto> allPosts  = new ArrayList<>();
		
		try 
		{
			allPosts = jdbcTemplate.query(GET_ALL_POST, (rs, rowNum) -> mapPost(rs));

			mapLikesAndComments(allPosts);
		}
		catch (Exception e) 
		{
			throw e;
		}
		
		return allPosts;
	}

	@Override
	public List<PostDto> getPostByCategory(int categoryId) 
	{
		List<PostDto> allPostsByCategory = new ArrayList<>();

		try
		{
			allPostsByCategory = jdbcTemplate.query(GET_POST_BY_CATEGORY, (rs, rowNum) -> mapPost(rs));

			mapLikesAndComments(allPostsByCategory);
		}

		catch (Exception e) 
		{
			throw e;
		}

		return allPostsByCategory;
	}

	@Override
	public List<PostDto> getAllPostByUser(int userId)
	{
		List<PostDto> allPosts = new ArrayList<>();
		
		try 
		{
			allPosts = jdbcTemplate.query(GET_POST_BY_USER_ID, new Object[] { userId }, (rs, rowNum) -> mapPost(rs));
			
			mapLikesAndComments(allPosts);
		}
		catch (Exception e) 
		{
			throw e;
		}
		
		return allPosts;
	}

	@Override
	public List<PostDto> searchPost(String key) {
		// TODO 
		return null;
	}

}
