package com.user.service.UserService.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.clone.DTOs.RoleDto;
import com.clone.DTOs.UserDto;

@Repository
public class UserServiceDAO {
	
	private static final String SELECT_FROM_USERS_USING_USER_ID = "SELECT * FROM users WHERE id = ?";
	private static final String SELECT_ROLE_USING_ROLE_ID = "SELECT r.id, r.name FROM roles r JOIN user_roles ur ON r.id = ur.role_id WHERE ur.user_id = ?";
	private static final String SELECT_FROM_USERS_USING_EMAIL = "SELECT * FROM users WHERE email = ?";

	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	 private UserDto mapUser(ResultSet rs) throws SQLException {
	        UserDto user = new UserDto();
	        user.setId(rs.getInt("id"));
	        user.setName(rs.getString("name"));
	        user.setEmail(rs.getString("email"));
	        user.setPassword(rs.getString("password"));
	        user.setAbout(rs.getString("about"));
	        return user;
	    }
	
	public UserDto findByEmail(String email) 
	{
        List<UserDto> users = jdbcTemplate.query(SELECT_FROM_USERS_USING_EMAIL, new Object[]{email}, (rs, rowNum) -> mapUser(rs));

        if (users.isEmpty()) {
            return null;
        }

        UserDto user = users.get(0);

        List<RoleDto> roles = jdbcTemplate.query(SELECT_ROLE_USING_ROLE_ID, new Object[]{user.getId()}, (rs, rowNum) ->
            new RoleDto(rs.getInt("id"), rs.getString("name"))
        );

        roles.forEach(user::addRole);

        return user;
	}
	
	public UserDto findByUserId(String userId) 
	{
        List<UserDto> users = jdbcTemplate.query(SELECT_FROM_USERS_USING_USER_ID, new Object[]{userId}, (rs, rowNum) -> mapUser(rs));

        if (users.isEmpty()) {
            return null;
        }

        UserDto user = users.get(0);

        String roleSql = "SELECT r.id, r.name FROM roles r JOIN user_roles ur ON r.id = ur.role_id WHERE ur.user_id = ?";
        
        List<RoleDto> roles = jdbcTemplate.query(roleSql, new Object[]{user.getId()}, (rs, rowNum) ->
            new RoleDto(rs.getInt("id"), rs.getString("name"))
        );

        roles.forEach(user::addRole);

        return user;
	}
	
	public UserDto findByEmailAndPassword(String email, String password) 
	{
		String sql = "SELECT * FROM users WHERE email = ? and password = ?";
		
        List<UserDto> users = jdbcTemplate.query(sql, new Object[]{email, password}, (rs, rowNum) -> mapUser(rs));

        if (users.isEmpty()) {
            return null;
        }

        UserDto user = users.get(0);

        String roleSql = "SELECT r.id, r.name FROM roles r JOIN user_roles ur ON r.id = ur.role_id WHERE ur.user_id = ?";
        
        List<RoleDto> roles = jdbcTemplate.query(roleSql, new Object[]{user.getId()}, (rs, rowNum) ->
            new RoleDto(rs.getInt("id"), rs.getString("name"))
        );

        roles.forEach(user::addRole);

        return user;
	}
}
