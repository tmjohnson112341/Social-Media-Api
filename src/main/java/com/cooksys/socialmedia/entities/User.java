package com.cooksys.socialmedia.entities;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;
import lombok.NoArgsConstructor;


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
	private List<User> followers;

	@ManyToMany
	@JoinTable(name = "followers_following")
	private List<User> following;

	@ManyToMany
	@JoinTable(
			name = "user_likes",
			joinColumns = @JoinColumn(name ="user_id"),
			inverseJoinColumns = @JoinColumn(name ="tweet_id")
			)
	
	private List<Tweet> likedTweets;

	@ManyToMany(mappedBy = "mentions")
	private List<Tweet> tweetsMentioned;


}
