package com.coreresources.required;

public class RoleDto {
    int id;
    String name;
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
	public RoleDto(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public RoleDto() {
		super();
		// TODO Auto-generated constructor stub
	}

}
