package com.cooksys.socialmedia.entities;

import java.sql.Timestamp;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
}
