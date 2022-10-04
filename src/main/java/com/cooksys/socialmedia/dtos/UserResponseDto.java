package com.cooksys.socialmedia.dtos;


import com.cooksys.socialmedia.entities.Profile;

import java.sql.Timestamp;

import com.cooksys.socialmedia.entities.Profile;

public class UserResponseDto {
    private String username;
    private ProfileDto profile;
    private Timestamp joined;
}
