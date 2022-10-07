package com.cooksys.socialmedia.services;

import com.cooksys.socialmedia.dtos.CredentialsDto;
import com.cooksys.socialmedia.dtos.ProfileDto;
import com.cooksys.socialmedia.dtos.UserRequestDto;
import com.cooksys.socialmedia.dtos.UserResponseDto;
import com.cooksys.socialmedia.entities.User;

import java.util.List;

public interface UserServices {

    List<UserResponseDto> getAllUsers();


    UserResponseDto createNewUser(UserRequestDto userRequestDto);

    UserResponseDto getUser(String username);
}
