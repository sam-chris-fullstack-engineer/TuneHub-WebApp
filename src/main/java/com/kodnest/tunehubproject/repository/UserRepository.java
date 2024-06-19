package com.kodnest.tunehubproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kodnest.tunehubproject.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String>{

	public User findByEmail(String email);


}
