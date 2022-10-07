package com.cooksys.socialmedia.controllers;

import com.cooksys.socialmedia.dtos.*;
import com.cooksys.socialmedia.entities.Tweet;
import com.cooksys.socialmedia.entities.User;
import com.cooksys.socialmedia.services.TweetServices;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tweets")
public class TweetController {

    private TweetServices tweetServices;



    //@GetMapping //gets all tweets
    public List<TweetResponseDto> getAllTweets(){
        return tweetServices.getAllTweets();

    }

    //@PostMapping //create tweet , credentials in request body validated
    public TweetResponseDto createTweet (@RequestBody TweetRequestDto tweetRequestDto) {
        return tweetServices.createTweet(tweetRequestDto);

    }

    //@GetMapping("/{id}") //retrieves tweet with id
    public TweetResponseDto getTweet (@PathVariable Long id, TweetRequestDto tweetRequestDto) {
        return tweetServices.getTweet(id);

    }

    //@DeleteMapping("/{id}") //delete tweet with given id
    public TweetResponseDto deleteTweet (@PathVariable Long id) {
        return tweetServices.deleteTweet(id);

    }

    //@PostMapping("/{id}/like") //creates a liked relationship with tweet and user(with credentials in request body)
    public TweetResponseDto likeTweet (@PathVariable Long id, @RequestBody CredentialsDto credentialsDto) {
        return tweetServices.likeTweet(id, credentialsDto);

    }

    //@GetMapping("/tweets/{id}/likes") // retrieves the active users who have liked the tweet with the given id
    public List<UserResponseDto> likedByUsers (@PathVariable Long id){
        return tweetServices.likedByUsers(id);
    }

    //@PostMapping("/{id}/reply") //reply to tweet with given id (server creates inReplyTo relationship
    public TweetResponseDto replyToTweet (@PathVariable Long id) {
        return tweetServices.replyToTweet(id);

    }
    //@GetMapping("/{id}/replies") // retrieves the direct replies to the tweet with the given id
    public List<TweetResponseDto> getReplies (@PathVariable Long id){
        return tweetServices.getReplies(id);
    }

    //@PostMapping("/{id}/repost") //creates a repost tweet with given id (server creates repostOf property)
    public TweetResponseDto repostTweet (@PathVariable Long id) {
        return tweetServices.repostTweet(id);

    }

    //@GetMapping("/{id}/tags") //retrieves tag associated with given id
    public List<HashtagDto> getTag (@PathVariable Long id) {
        return tweetServices.getTag(id);

    }

    //@GetMapping("/{id}/context") //retrieves context of a tweet with given id
    public TweetResponseDto getContext (@PathVariable Long id) {
        return tweetServices.getContext(id);
    }

    //@GetMapping("/{id}/reposts") //retrieves direct reposts of tweet with given id
    public List<TweetResponseDto> getReposts (@PathVariable Long id) {
        return tweetServices.getReposts(id);
    }

    //GetMapping("/{id}/mentions") //retrieves users mentioned in tweet with given id
    public List<UserResponseDto> getMentions (@PathVariable Long id) {
        return tweetServices.getMentions(id);

    }



}
