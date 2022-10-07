package com.cooksys.socialmedia.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cooksys.socialmedia.dtos.CredentialsDto;
import com.cooksys.socialmedia.dtos.TweetResponseDto;
import com.cooksys.socialmedia.dtos.UserRequestDto;
import com.cooksys.socialmedia.dtos.UserResponseDto;
import com.cooksys.socialmedia.entities.User;
import com.cooksys.socialmedia.mappers.UserMapper;
import com.cooksys.socialmedia.services.UserServices;

import lombok.RequiredArgsConstructor;


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

    public UserResponseDto changeUsername (@PathVariable UserRequestDto userRequestDto, String username){
        return userServices.changeUsername(username);
    }
//
//    @DeleteMapping("/@{username}") //deletes given username
    @DeleteMapping("/{username}")
    public UserResponseDto deleteUser(@RequestBody CredentialsDto credentialsDto, @PathVariable String username) {
    	return userServices.deleteUser(credentialsDto, username);
    }
//
//@PostMapping("/@{username}/follow") //subscribe to given username
    @PostMapping("/@{username}/follow")
    public void followUser(@RequestBody CredentialsDto credentialsDto, @PathVariable String username ) {
    	userServices.followUser(credentialsDto, username);
    }
//
//    @PostMapping("/@{username}/unfollow") //unsubscribe to given username
    @PostMapping("/@{username}/unfollow")
    public void unfollowUser(@RequestBody CredentialsDto credentialsDto, @PathVariable String username) {
    	userServices.unfollowUser(credentialsDto, username);
    }
//
//    @GetMapping("/@{username}/feed") //retrieves all non-deleted tweets from username including replies to non-deleted tweets to deleted tweets
    @GetMapping("/@{username}/feed")
    public List<TweetResponseDto>userFeed(@PathVariable String username) {
    	return userServices.userFeed(username);
    }
    
    //@GetMapping("/@{username}/tweets) //retrieves all non-deleted tweets from username.
    @GetMapping("/@{username}/tweets")
    public List<TweetResponseDto>getTweetsByUser(@PathVariable String username){
    	return userServices.getTweetsByUser(username);
    }
    
//    @GetMapping("/@{username}/mentions") //usernames mentioned in content of tweet
    @GetMapping("/@{username}/mentions")
    public List<TweetResponseDto>userMentions(@PathVariable String username) {
    	return userServices.userMentions(username);
    }
//
//    @GetMapping("/@{username}/followers")  //retrieves followers of username givens
    @GetMapping("/@{username}/followers")
    public List<UserResponseDto>userFollowers(@PathVariable String username) {
    	return userServices.userFollowers(username);
    }
//
//    @GetMapping("/@{username}/following") //retrieves the usernames of given username follows
    @GetMapping("/@{username}/following")
    public List<UserResponseDto> userFollowing(@PathVariable String username) {
    	return userServices.userFollowing(username);
    }


}
