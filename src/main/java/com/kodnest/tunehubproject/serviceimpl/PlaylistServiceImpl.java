package com.kodnest.tunehubproject.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodnest.tunehubproject.entity.Playlist;
import com.kodnest.tunehubproject.repository.PlaylistRepository;
import com.kodnest.tunehubproject.service.PlaylistService;

@Service
public class PlaylistServiceImpl implements PlaylistService{
	
	@Autowired
	PlaylistRepository playlistRepository;

	@Override
	public void addplaylist(Playlist playlist) {
		playlistRepository.save(playlist);
	}

	@Override
	public List<Playlist> fetchAllPlaylists() {
		List<Playlist> playlists = playlistRepository.findAll();
		return playlists;
	}

}
