package com.kodnest.tunehubproject.service;

import java.util.List;

import com.kodnest.tunehubproject.entity.Playlist;

public interface PlaylistService {
	
	public void addplaylist(Playlist playlist);

	public List<Playlist> fetchAllPlaylists();
}
