package com.cooksys.socialmedia.Controllers;

import com.cooksys.socialmedia.Services.HashtagServices;
import com.cooksys.socialmedia.Services.TweetServices;
import com.cooksys.socialmedia.Services.UserService;
import com.cooksys.socialmedia.Services.ValidateServices;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tags")
public class HashtagController {

    private UserService userService;
    private ValidateServices validateServcies;
    private HashtagServices hashtagServices;
    private TweetServices tweetServices;

    //@GetMapping("/{label}") //retrieves all  non-deleted tweets contain given hashtag



}
