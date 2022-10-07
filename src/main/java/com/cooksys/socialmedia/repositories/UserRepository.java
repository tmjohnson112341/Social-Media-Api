package com.cooksys.socialmedia.repositories;


import com.cooksys.socialmedia.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.cooksys.socialmedia.entities.User;

import java.util.Optional;

import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	

    Optional<User> findByCredentialsUsernameAndDeletedFalse(String name);



}
