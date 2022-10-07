package com.cooksys.socialmedia.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cooksys.socialmedia.dtos.CredentialsDto;
import com.cooksys.socialmedia.dtos.TweetResponseDto;
import com.cooksys.socialmedia.dtos.UserRequestDto;
import com.cooksys.socialmedia.dtos.UserResponseDto;
import com.cooksys.socialmedia.entities.Credentials;
import com.cooksys.socialmedia.entities.Profile;
import com.cooksys.socialmedia.entities.Tweet;
import com.cooksys.socialmedia.entities.User;
import com.cooksys.socialmedia.exceptions.BadRequestException;
import com.cooksys.socialmedia.exceptions.NotAuthorizedException;
import com.cooksys.socialmedia.exceptions.NotFoundException;
import com.cooksys.socialmedia.mappers.CredentialsMapper;
import com.cooksys.socialmedia.mappers.ProfileMapper;
import com.cooksys.socialmedia.mappers.TweetMapper;
import com.cooksys.socialmedia.mappers.UserMapper;
import com.cooksys.socialmedia.repositories.UserRepository;
import com.cooksys.socialmedia.services.UserServices;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServicesImpl implements UserServices{

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final TweetMapper tweetMapper;
    private final CredentialsMapper credentialsMapper;
    private final ProfileMapper profileMapper;

    
    private User findUser(String username) {
        Optional<User> optionalUser = userRepository.findByCredentialsUsername(username);
        if (optionalUser.isEmpty()) {
            throw new NotFoundException("the user by the name " + username + " does not exist.");
        } else {
            return optionalUser.get();
        }
    }

    @Override
    public List<UserResponseDto> getAllUsers() {
    	List<User> allUsers = userRepository.findUsersByDeletedFalse();
        return userMapper.entityToDtos(allUsers);
    }

    @Override
    public UserResponseDto createNewUser(UserRequestDto userRequestDto) {
        if (userRequestDto == null) {
            throw new BadRequestException("There is nothing here");
        }

        User createdUser = userMapper.dtoToEntity(userRequestDto);

        if (createdUser.getProfile() == null || createdUser.getProfile().getEmail() == null) {
            throw new BadRequestException("You don't exist");
        }

        if (createdUser.getCredentials() == null ||
                createdUser.getCredentials().getPassword() == null ||
                createdUser.getCredentials().getUsername() == null) {
            throw new BadRequestException("A username and password are required");
        }

        userRepository.saveAndFlush(createdUser);
        return userMapper.entityToDto(createdUser);

    }
    
    
    @Override
    public UserResponseDto getUser(String username) {
    	User userToGet = findUser(username);
    	return userMapper.entityToDto(userToGet);
    }


    @Override
    public UserResponseDto changeUsername(String username) {
        getUser(username);
        return null;
    }

	@Override
	public UserResponseDto patchUser(UserRequestDto userRequestDto, String username) {
		User userToPatch = findUser(username);
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
			throw new NotAuthorizedException("Username: " + username + " not found with these credentials");
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


