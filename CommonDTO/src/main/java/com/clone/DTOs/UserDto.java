package com.clone.DTOs;

import java.util.HashSet;
import java.util.Set;


public class UserDto {

    int id;
    String name;
    String email;
    String password;
    String about;
    Set<RoleDto> roles = new HashSet<>();
    public void addRole(RoleDto role) {
        roles.add(role);
    }
    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public Set<RoleDto> getSet() {
		return roles;
	}

	public void setSet(Set<RoleDto> set) {
		this.roles = set;
	}

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String pass) {
        this.password = pass;
    }

    
}