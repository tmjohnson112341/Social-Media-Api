package com.cooksys.socialmedia.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;


import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@Data
<<<<<<< HEAD
=======

>>>>>>> a37b751695ff41966335496e8d5d0534ab501ebc
public class Profile {
	
	private String firstName;

	private String lastName;

	@Column(nullable = false)
	private String email;

	private String phone;


}
