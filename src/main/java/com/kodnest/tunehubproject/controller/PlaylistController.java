package com.kodnest.tunehubproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.kodnest.tunehubproject.entity.Playlist;
import com.kodnest.tunehubproject.entity.Song;
import com.kodnest.tunehubproject.service.PlaylistService;
import com.kodnest.tunehubproject.service.SongService;

@Controller
public class PlaylistController {
	
	@Autowired
	SongService songService;
	
	@Autowired
	PlaylistService playlistService;
	
	@GetMapping("/createplaylist")
	public String createPlaylist(Model model) {
		List<Song> songList = songService.fetchAllSongs();
		model.addAttribute("songs", songList);
		return "createplaylist";
	}

	@PostMapping("/addplaylist")
	public String addPlaylist(@ModelAttribute Playlist playlist) {

		//Updating the play list table
		playlistService.addplaylist(playlist);

		//Updating the song table
		List<Song> songList = playlist.getSongs();
		for(Song s: songList) {
			s.getPlaylists().add(playlist);
			songService.updateSong(s);
		}
		return "adminlogin";
	}

	@GetMapping("/viewplaylist")
	public String viewPlaylist(Model model) {
		List<Playlist> playlists = playlistService.fetchAllPlaylists();
		model.addAttribute("playlists", playlists);
		return "viewplaylist";
	}
}
