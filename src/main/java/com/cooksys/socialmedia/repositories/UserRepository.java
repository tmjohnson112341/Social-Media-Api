package com.cooksys.socialmedia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cooksys.socialmedia.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
