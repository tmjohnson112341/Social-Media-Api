package com.cooksys.socialmedia.entities;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.boot.context.config.Profiles;

@Entity
@NoArgsConstructor
@Data
@Table(name = "user_table")
public class User {

	@Id
	@GeneratedValue
	private Long id;
	
	@Embedded
	private Credentials credentials;
	
	@CreationTimestamp
	private Timestamp joined;
	
	private boolean deleted;
	
	@Embedded
	private Profile profile;

	@OneToMany(mappedBy = "author")
	private List<Tweet> tweets;

	@ManyToMany(mappedBy = "following")
	private Set<User> followers;

	@ManyToMany
	private Set<User> following;

	@ManyToMany(mappedBy = "likes")
	private Set<Tweet> likedTweets;

	@ManyToMany(mappedBy = "mentions")
	private Set<Tweet> tweetsMentioned;




}
