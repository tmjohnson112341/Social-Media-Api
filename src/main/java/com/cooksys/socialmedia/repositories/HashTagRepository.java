package com.cooksys.socialmedia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cooksys.socialmedia.entities.Hashtag;

@Repository
public interface HashTagRepository extends JpaRepository<Hashtag, Long> {

}
