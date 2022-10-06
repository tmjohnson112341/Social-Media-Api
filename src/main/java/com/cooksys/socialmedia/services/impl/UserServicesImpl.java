package com.cooksys.socialmedia.services.impl;

import com.cooksys.socialmedia.dtos.CredentialsDto;
import com.cooksys.socialmedia.dtos.ProfileDto;
import com.cooksys.socialmedia.dtos.UserRequestDto;
import com.cooksys.socialmedia.dtos.UserResponseDto;
import com.cooksys.socialmedia.entities.Credentials;
import com.cooksys.socialmedia.entities.Profile;
import com.cooksys.socialmedia.entities.User;
import com.cooksys.socialmedia.mappers.CredentialsMapper;
import com.cooksys.socialmedia.mappers.ProfileMapper;
import com.cooksys.socialmedia.mappers.UserMapper;
import com.cooksys.socialmedia.repositories.UserRepository;
import org.springframework.stereotype.Service;

import com.cooksys.socialmedia.services.UserServices;

import lombok.RequiredArgsConstructor;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServicesImpl implements UserServices{

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final CredentialsMapper credentialsMapper;
    private final ProfileMapper profileMapper;


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


}


