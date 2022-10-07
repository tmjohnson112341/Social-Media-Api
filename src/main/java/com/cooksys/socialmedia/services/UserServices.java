package com.cooksys.socialmedia.services;

import com.cooksys.socialmedia.dtos.CredentialsDto;
import com.cooksys.socialmedia.dtos.ProfileDto;
import com.cooksys.socialmedia.dtos.TweetResponseDto;
import com.cooksys.socialmedia.dtos.UserRequestDto;
import com.cooksys.socialmedia.dtos.UserResponseDto;
import com.cooksys.socialmedia.entities.User;

import java.util.List;

public interface UserServices {

    List<UserResponseDto> getAllUsers();


    UserResponseDto createNewUser(UserRequestDto userRequestDto);

    UserResponseDto getUser(String username);


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
