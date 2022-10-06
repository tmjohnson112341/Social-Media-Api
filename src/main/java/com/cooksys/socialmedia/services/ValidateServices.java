package com.cooksys.socialmedia.services;

public interface ValidateServices {

	boolean validateTag(String label);

	boolean validateUsername(String username);

	boolean validateUsernameAvailable(String username);


}
