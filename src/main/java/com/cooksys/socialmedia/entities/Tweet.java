package com.cooksys.socialmedia.entities;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class Tweet {

	@Id
	@GeneratedValue
	private Long id;
	
	private User author;
	
	private Timestamp posted = Timestamp.valueOf(LocalDateTime.now());
	
	private boolean deleted = false;

	@Column
	private String content;
	
	
	private Tweet inReplyTo;
	
	
	private Tweet repostOf;
	
	
}
