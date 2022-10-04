package com.cooksys.socialmedia.entities;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class Tweet {

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne
	private User author;
	
	private Timestamp posted = Timestamp.valueOf(LocalDateTime.now());
	
	private boolean deleted = false;

	@Column
	private String content;

	@OneToMany(mappedBy = "inReplyTo")
	private List<Tweet> replies;

	@ManyToOne
	private Tweet inReplyTo;

	@OneToMany(mappedBy = "repostOf")
	private List<Tweet> repostsOf;
	
	@ManyToOne
	private Tweet repostOf;

	@ManyToMany
	private Set<Tweet> likes;

	@ManyToMany
	private Set<Tweet> mentions;

	@ManyToMany(mappedBy = "tweet")
	private Set<Hashtag> hashtags;


	
}
