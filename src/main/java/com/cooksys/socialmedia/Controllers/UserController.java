package com.cooksys.socialmedia.controllers;

import com.cooksys.socialmedia.services.UserServices;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private UserServices userServices;


//    @GetMapping //get all users (non-deleted)
//
//    @PostMapping //creates new user - reactivates deleted user
//
//    @GetMapping("/@{username}") //retrieves user of given username
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
