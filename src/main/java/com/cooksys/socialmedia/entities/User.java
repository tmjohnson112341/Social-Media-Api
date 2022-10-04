package com.cooksys.socialmedia.entities;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;
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
	
	private Timestamp joined;
	
	private boolean deleted;
	
	@Embedded
	private Profiles profile;

	@OneToMany
	private List<Tweet> tweets;

	@ManyToMany(mappedBy = "following")
	private Set<User> followers;

	@ManyToMany
	private Set<User> following;

	@ManyToMany(mappedBy = "user")
	private Set<User> likes;

	@ManyToMany(mappedBy = "user")
	private Set<User> mentions;




}
