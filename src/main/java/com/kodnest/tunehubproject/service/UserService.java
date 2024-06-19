package com.kodnest.tunehubproject.service;

import com.kodnest.tunehubproject.entity.User;

public interface UserService {
	public String addUser(User user) ;
	public boolean emailExist(String email);
	public boolean validateUser(String email, String password);
	public String getRole(String email);
	
	public User getUser(String email);
	public void updateUser(User user);
}
