package com.cooksys.socialmedia.repositories;


import com.cooksys.socialmedia.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cooksys.socialmedia.entities.User;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	Optional<User>findByCredentialsUsername(String username);

    Optional<User> findByCredentialsUsername(String name);

}
