package com.cooksys.socialmedia.controllers;

import com.cooksys.socialmedia.dtos.CredentialsDto;
import com.cooksys.socialmedia.dtos.ProfileDto;
import com.cooksys.socialmedia.dtos.UserRequestDto;
import com.cooksys.socialmedia.dtos.UserResponseDto;
import com.cooksys.socialmedia.mappers.UserMapper;
import com.cooksys.socialmedia.services.UserServices;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    @Autowired
    private final UserServices userServices;
    @Autowired
    private final UserMapper userMapper;

    @GetMapping
    public List<UserResponseDto> getAllUsers() {
        return userServices.getAllUsers();

    }
//
    @PostMapping //creates new user - reactivates deleted user
    public UserResponseDto newUser(@RequestBody UserRequestDto userRequestDto){
        return userServices.createNewUser(userRequestDto);
    }
//
    @GetMapping("/@{username}") //retrieves user of given username
    public UserResponseDto getUser(@PathVariable String username){
        return userServices.getUser(username);
    }
//
//    @PatchMapping("/@{username}") //updates profile username
//
//    @DeleteMapping("/@{username}") //deletes given username
//
//    @PostMapping("/@{username}/follow") //subscribe to given username
//
//    @PostMapping("/@{username}/unfollow") //unsubscribe to given username
//
//    @GetMapping("/@{username}/feed") //retrieves all non-deleted tweets from username including replies to non-deleted tweets to deleted tweets
//
//    @GetMapping("/@{username}/mentions") //usernames mentioned in content of tweet
//
//    @GetMapping("/@{username}/followers")  //retrieves followers of username givens
//
//    @GetMapping("/@{username}/following") //retrieves the usernames of given username follows




}
