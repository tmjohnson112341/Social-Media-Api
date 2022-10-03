package com.cooksys.socialmedia.Controllers;

import com.cooksys.socialmedia.Services.TweetServices;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tweets")
public class TweetController {

    private TweetServices tweetServices;



    //@GetMapping //gets all tweets

    //@PostMapping //create tweet , credentials in request body validated

    //@GetMapping("/{id}") //retrieves tweet with id

    //@DeleteMapping("/{id}") //delete tweet with given id

    //@PostMapping("/{id}/like") //creates a liked relationship with tweet and user(with credentials in request body)

    //@PostMapping("/{id}/reply") //reply to tweet with given id (server creates inReplyTo relationship)

    //@PostMapping("/{id}/repost") //creates a repost tweet with given id (server creates repostOf property)

    //@GetMapping("/{id}/tags") //retrieves tag associated with given id

    //@GetMapping("/{id}/context") //retrieves context of a tweet with given id

    //@GetMapping("/{id}/reposts") //retrieves direct reposts of tweet with given id

    //GetMapping("/{id}/mentions") //retrieves users mentioned in tweet with given id



}
