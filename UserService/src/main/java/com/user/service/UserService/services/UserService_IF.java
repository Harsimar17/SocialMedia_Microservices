package com.user.service.UserService.services;

import java.util.List;

import com.coreresources.required.UserDto;

public interface UserService_IF {
	
	UserDto createuser(UserDto u);

    UserDto updateuser(UserDto u, int id);

    UserDto getUserbyId(int id);

    List<UserDto> getall();

    void deleteuser(int id);
    
	UserDto getUserByEmail(String email);

	String authenticate(String username, String password);

	UserDto getUserByEmailAndPassword(String string, String string2);
}
