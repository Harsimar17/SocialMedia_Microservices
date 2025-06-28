package com.post.service.PostService.serviceImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.clone.DTOs.CategoryDto;
import com.clone.DTOs.PostDto;
import com.clone.DTOs.UserDto;
import com.post.service.PostService.service.PostService_IF;
import com.serviceclient.services.UserService;

@Service
public class PostServiceImpl implements PostService_IF{
	
	private static final String CREATE_POST = "INSERT INTO post (title, content, imagename, date, ct_id, u_id) VALUES (?, ?, ?, ?, ?, ?)";
	private static final String GET_POST_BY_PID = "SELECT p.*, c.id AS category_id, c.title AS category_title, c.description AS category_description FROM post p JOIN category c ON p.ct_id = c.id WHERE p.id = ?";
	private static final String GET_ALL_POST = "SELECT p.*, c.id AS category_id, c.title AS category_title, c.description AS category_description FROM post p JOIN category c ON p.ct_id = c.id";

	private PostDto mapUser(ResultSet rs) throws SQLException 
	 {
			PostDto post = new PostDto();
			post.setId(rs.getInt("id"));
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
	public PostDto updatePost(PostDto pt, int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deletePost(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PostDto getPost(int id) 
	{
		List<PostDto> postById = jdbcTemplate.query(GET_POST_BY_PID, new Object[] { id }, (rs, rowNum) -> mapUser(rs));
		
		if(postById != null && postById.size()>0) 
		{
			return postById.get(0);
		}
		return null;
	}

	@Override
	public List<PostDto> getAllPost() {
		// TODO Auto-generated method stub
		
		List<PostDto> allPosts = jdbcTemplate.query(GET_ALL_POST, (rs, rowNum) -> mapUser(rs));
		
		return allPosts;
	}

	@Override
	public List<PostDto> getPostByCategory(int cid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PostDto> getAllPostByUser(int uid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PostDto> searchPost(String key) {
		// TODO Auto-generated method stub
		return null;
	}

}
