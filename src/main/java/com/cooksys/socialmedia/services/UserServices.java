package com.cooksys.socialmedia.services;

import com.cooksys.socialmedia.dtos.UserRequestDto;
import com.cooksys.socialmedia.dtos.UserResponseDto;

import java.util.List;

public interface UserServices {

    List<UserResponseDto> getAllUsers();


    UserResponseDto createNewUser(UserRequestDto userRequestDto);

    UserResponseDto getUser(String username);

    UserResponseDto changeUsername(String username);
}
