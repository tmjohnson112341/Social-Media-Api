package com.cooksys.socialmedia.services.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cooksys.socialmedia.entities.Hashtag;
import com.cooksys.socialmedia.entities.User;
import com.cooksys.socialmedia.repositories.HashTagRepository;
import com.cooksys.socialmedia.repositories.UserRepository;
import com.cooksys.socialmedia.services.ValidateServices;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ValidateServicesImpl implements ValidateServices {
	
	private final HashTagRepository hashtagRepository;
	private final UserRepository userRepository;
	
	@Override
	public boolean validateTag(String label) {
		Optional<Hashtag>optionalHashtag = hashtagRepository.findByLabel(label);
		if(optionalHashtag.isEmpty()) {
			return false;
		}else {
			return true;
		}	
	}

	@Override
	public boolean validateUsername(String username) {
		Optional<User>optionalUser = userRepository.findByCredentialsUsername(username);
		if(optionalUser.isEmpty()) {
			return false;
		}else {
			return true;
		}
	}

	@Override
	public boolean validateUsernameAvailable(String username) {
		return !validateUsername(username);
	}
}
