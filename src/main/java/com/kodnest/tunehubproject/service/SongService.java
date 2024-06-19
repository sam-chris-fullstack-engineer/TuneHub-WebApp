package com.kodnest.tunehubproject.service;

import java.util.List;

import com.kodnest.tunehubproject.entity.Song;

public interface SongService {
	public void addSong(Song song);

	public List<Song> fetchAllSongs();

	public boolean songExist(String name);

	public void updateSong(Song s);
}
