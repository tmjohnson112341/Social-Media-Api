package com.cooksys.socialmedia.entities;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;

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
	
	@CreationTimestamp
	private Timestamp posted;
	
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
