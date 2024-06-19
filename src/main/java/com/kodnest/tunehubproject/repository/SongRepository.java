package com.kodnest.tunehubproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kodnest.tunehubproject.entity.Song;

@Repository
public interface SongRepository extends JpaRepository<Song, Integer>{
	
	public Song findByName(String name);
}
