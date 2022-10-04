package com.cooksys.socialmedia.entities;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@NoArgsConstructor
@Data
public class Hashtag {
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable = false, unique = true)
	private String label;
	
	private Timestamp firstUsed = Timestamp.valueOf(LocalDateTime.now());
	
	private Timestamp lastUsed = Timestamp.valueOf(LocalDateTime.now());

	@ManyToMany
	private Set<Tweet> hashtags;
	
}
