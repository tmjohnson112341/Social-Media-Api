package com.cooksys.socialmedia.Controllers;

import com.cooksys.socialmedia.Services.HashtagServices;
import com.cooksys.socialmedia.Services.TweetServices;
import com.cooksys.socialmedia.Services.UserService;
import com.cooksys.socialmedia.Services.ValidateServices;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/validate")
public class ValidateController {

    private UserService userService;
    private ValidateServices validateServcies;
    private HashtagServices hashtagServices;
    private TweetServices tweetServices;

    //@GetMapping("tag/exists/{label}") // Given hashtag exists


    //@GetMapping("/username/exists/@{username}") // given username exists


    //@GetMapping("/username/available/@{username}") //given username available








}
