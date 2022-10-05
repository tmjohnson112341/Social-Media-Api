package com.cooksys.socialmedia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cooksys.socialmedia.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
