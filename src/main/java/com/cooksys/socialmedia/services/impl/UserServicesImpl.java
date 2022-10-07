package com.cooksys.socialmedia.services.impl;

import com.cooksys.socialmedia.dtos.CredentialsDto;
import com.cooksys.socialmedia.dtos.ProfileDto;
import com.cooksys.socialmedia.dtos.TweetResponseDto;
import com.cooksys.socialmedia.dtos.UserRequestDto;
import com.cooksys.socialmedia.dtos.UserResponseDto;
import com.cooksys.socialmedia.entities.Credentials;
import com.cooksys.socialmedia.entities.Profile;
import com.cooksys.socialmedia.entities.Tweet;
import com.cooksys.socialmedia.entities.User;
import com.cooksys.socialmedia.exceptions.NotAuthorizedException;
import com.cooksys.socialmedia.exceptions.NotFoundException;
import com.cooksys.socialmedia.mappers.CredentialsMapper;
import com.cooksys.socialmedia.mappers.ProfileMapper;
import com.cooksys.socialmedia.mappers.TweetMapper;
import com.cooksys.socialmedia.mappers.UserMapper;
import com.cooksys.socialmedia.repositories.UserRepository;
import org.springframework.stereotype.Service;

import com.cooksys.socialmedia.services.UserServices;

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServicesImpl implements UserServices{

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final TweetMapper tweetMapper;
    private final CredentialsMapper credentialsMapper;
    private final ProfileMapper profileMapper;

    
    private User findUser(String username) {
        Optional<User> optionalUser = userRepository.findByCredentialsUsernameAndDeletedFalse(username);
        if (optionalUser.isEmpty()) {
            throw new NotFoundException("the user by the name " + username + " does not exist.");
        } else {
            return optionalUser.get();
        }
    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        return userMapper.entityToDtos(userRepository.findAll());
    }

    @Override
    public UserResponseDto createNewUser(UserRequestDto userRequestDto) {
        User createdUser = new User();
        Credentials newCreds = credentialsMapper.dtoToEntities(userRequestDto.getCredentials());
        Profile newProfile = profileMapper.dtoToEntities(userRequestDto.getProfile());
        System.out.print(newProfile);
        System.out.print(newCreds);

        createdUser.setCredentials(newCreds);
        createdUser.setProfile(newProfile);

        userRepository.saveAndFlush(createdUser);
        return userMapper.entityToDto(createdUser);

    }
    
    
    @Override
    public UserResponseDto getUser(String username) {
        User user1 = new User();

        for (User user: userRepository.findAll()){
            user1.setCredentials(user.getCredentials());
            user1.setProfile(user.getProfile());
            user1.setJoined(user.getJoined());
            if (user1.getCredentials().getUsername().equals(username)) {
                return userMapper.entityToDto(user1);
            }
        }
        return null;
    }

	@Override
	public UserResponseDto patchUser(UserRequestDto userRequestDto, String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserResponseDto deleteUser(CredentialsDto credentialsDto, String username) {
		User userToDelete = findUser(username);
		if (userToDelete.getCredentials().equals(credentialsMapper.dtoToEntities(credentialsDto))) {
			userToDelete.setDeleted(true);
			userRepository.saveAndFlush(userToDelete);
			return userMapper.entityToDto(userToDelete);
		} else {
			throw new NotAuthorizedException("Username: " + username + "not found with these credentials");
		}
	}

	@Override
	public void followUser(CredentialsDto credentialsDto, String username) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void unfollowUser(CredentialsDto credentialsDto, String username) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<TweetResponseDto> userFeed(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TweetResponseDto> getTweetsByUser(String username) {
		User userTweets = findUser(username);
		List<Tweet> tweets = userTweets.getTweets();
		return tweetMapper.entitiesToDtos(tweets);
	}

	@Override
	public List<TweetResponseDto> userMentions(String username) {
		User userMentions = findUser(username);
		List<Tweet> tweets = userMentions.getTweetsMentioned();
		return tweetMapper.entitiesToDtos(tweets);
	}

	@Override
	public List<UserResponseDto> userFollowers(String username) {
		User usersFollowers = findUser(username);
		return userMapper.entitiesToDtos(usersFollowers.getFollowers());
	}

	@Override
	public List<UserResponseDto> userFollowing(String username) {
		User usersFollowing = findUser(username);
		return userMapper.entitiesToDtos(usersFollowing.getFollowing());
	}




}


