package com.cooksys.socialmedia.services.impl;

import org.springframework.stereotype.Service;

import com.cooksys.socialmedia.services.ValidateServices;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ValidateServicesImpl implements ValidateServices {@Override
	public boolean validateTag(String label) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean validateUsername(String username) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean validateUsernameAvailable(String username) {
		// TODO Auto-generated method stub
		return false;
	}
}
