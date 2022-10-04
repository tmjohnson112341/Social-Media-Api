package com.cooksys.socialmedia.entities;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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
	
	private User author;
	
	@CreationTimestamp
	private Timestamp posted;
	
	private boolean deleted = false;

	@Column
	private String content;
	
	
	private Tweet inReplyTo;
	
	
	private Tweet repostOf;
	
	
}
