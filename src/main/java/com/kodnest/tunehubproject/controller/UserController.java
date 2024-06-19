package com.kodnest.tunehubproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kodnest.tunehubproject.entity.Song;
import com.kodnest.tunehubproject.entity.User;
import com.kodnest.tunehubproject.serviceimpl.SongServiceImpl;
import com.kodnest.tunehubproject.serviceimpl.UserServiceImpl;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {

	@Autowired
	UserServiceImpl userServiceImpl;

	@Autowired
	SongServiceImpl songServiceImpl;
	

	@PostMapping("/register")
	public String addUser(@ModelAttribute User user) 
	{		
		//Email taken from registration form
		String email = user.getEmail();

		//Checking if email is present in DB or not 
		boolean status = userServiceImpl.emailExist(email);

		if(status == true) 
		{
			System.out.println("User is Already Exist");
		}
		else 
		{			
			userServiceImpl.addUser(user);
			System.out.println("User Added");
			if((user.getRole()).equalsIgnoreCase("admin")) {
				return "adminlogin";
			}
			else {
				return "customerlogin";
			}
		}
		return "index";
	}

	@PostMapping("/login")
	public String validate(@RequestParam("email") String email, @RequestParam("password") String password, 
			HttpSession session, Model model) 
	{
		if(userServiceImpl.validateUser(email, password) == true) 
		{
			String role = userServiceImpl.getRole(email);
			
			session.setAttribute("email", email);
			
			if(role.equalsIgnoreCase("admin")) 
			{
				return "adminlogin";
			}
			else 
			{
				User user = userServiceImpl.getUser(email);
				boolean isPremium = user.getIsPremium();
				List<Song> songList = songServiceImpl.fetchAllSongs();
				model.addAttribute("songs", songList);
				model.addAttribute("ispremium",isPremium);
				return "customerlogin";
			}
		}
		else 
		{
			model.addAttribute("loginError", "*Please enter the correct password or email.");
			return "login";
		}
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "login";
	}
	
	@GetMapping("/index")
	public String index() {
		return "index";
	}
}
