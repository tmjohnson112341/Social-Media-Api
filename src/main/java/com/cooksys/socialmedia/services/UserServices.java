package com.cooksys.socialmedia.services;

import java.util.List;

import com.cooksys.socialmedia.dtos.CredentialsDto;
import com.cooksys.socialmedia.dtos.TweetResponseDto;
import com.cooksys.socialmedia.dtos.UserRequestDto;
import com.cooksys.socialmedia.dtos.UserResponseDto;

public interface UserServices {

	List<UserResponseDto> getAllUsers();

	UserResponseDto createNewUser(UserRequestDto userRequestDto);

	UserResponseDto getUser(String username);

	UserResponseDto changeUsername(String username);

	UserResponseDto patchUser(UserRequestDto userRequestDto, String username);

	UserResponseDto deleteUser(CredentialsDto credentialsDto, String username);

	void followUser(CredentialsDto credentialsDto, String username);

	void unfollowUser(CredentialsDto credentialsDto, String username);

	List<TweetResponseDto> userFeed(String username);

	List<TweetResponseDto> getTweetsByUser(String username);

	List<TweetResponseDto> userMentions(String username);

	List<UserResponseDto> userFollowers(String username);

	List<UserResponseDto> userFollowing(String username);

}
