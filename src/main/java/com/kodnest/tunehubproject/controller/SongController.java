package com.kodnest.tunehubproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.kodnest.tunehubproject.entity.Song;
import com.kodnest.tunehubproject.service.SongService;

@Controller
public class SongController {

	@Autowired
	SongService songService;

	@PostMapping("/addsong")
	public String addSong(@ModelAttribute Song song) {
		if (song.getName() != null && !song.getName().isEmpty()) {
			//Checking if song is present in DB or not 
			boolean status = songService.songExist(song.getName());

			if(status == false) {
				songService.addSong(song);
				System.out.println("Song Added");
			}
			else {			
				System.out.println("Song is Already Exist");
			}
		}
		return "newsong";
	}

	@GetMapping("/viewsongs")
	public String viewsongs(Model model) {
		List<Song> songList = songService.fetchAllSongs();
		model.addAttribute("songs", songList);
		return "viewsongs";
	}

	@GetMapping("/playsongs")
	public String playsongs(Model model) {
		boolean premium = false;
		if(premium) {
			List<Song> songList = songService.fetchAllSongs();
			model.addAttribute("songs", songList);
			return "songs";
		}else {
			return "subscriptionform";
		}
	}



}
