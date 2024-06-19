package com.kodnest.tunehubproject.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodnest.tunehubproject.entity.User;
import com.kodnest.tunehubproject.repository.UserRepository;
import com.kodnest.tunehubproject.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public String addUser(User user) {
		userRepository.save(user);
		return "Success";
	}

	//To check the email is present in DB or not
	@Override
	public boolean emailExist(String email) {
		if(userRepository.findByEmail(email)!= null) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean validateUser(String email, String password) {
		User user = userRepository.findByEmail(email);
		if (user != null) {
			String dbEmail = user.getEmail();
			String dbPwd = user.getPassword();
			if(password.equals(dbPwd) && email.equals(dbEmail))
			{
				return true;
			}
	    }
	    return false;
	}

	@Override
	public String getRole(String email) {
		User user = userRepository.findByEmail(email);
		return user.getRole();
	}

	@Override
	public User getUser(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public void updateUser(User user) {
		userRepository.save(user);
	}

}
