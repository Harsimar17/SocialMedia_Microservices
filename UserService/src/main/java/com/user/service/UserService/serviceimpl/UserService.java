package com.user.service.UserService.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coreresources.required.UserDto;
import com.user.service.UserService.DAO.UserServiceDAO;
import com.user.service.UserService.services.UserService_IF;

@Service
public class UserService implements UserService_IF{
	
	@Autowired
	UserServiceDAO userDao;

	@Override
	public UserDto createuser(UserDto u) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDto updateuser(UserDto u, int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDto getUserbyId(int id) {
		// TODO Auto-generated method stub
		return userDao.findByUserId(String.valueOf(id));
	}

	@Override
	public List<UserDto> getall() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteuser(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public UserDto getUserByEmail(String email) {
		// TODO Auto-generated method stub
		UserDto user = userDao.findByEmail(email);
		return user;
	}

	@Override
	public String authenticate(String username, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDto getUserByEmailAndPassword(String email, String password) {
		UserDto user = userDao.findByEmailAndPassword(email, password);
		return user;
	}

}
