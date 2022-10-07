package com.cooksys.socialmedia.services;

import com.cooksys.socialmedia.dtos.*;
import com.cooksys.socialmedia.entities.Tweet;
import com.cooksys.socialmedia.entities.User;

import java.util.List;

public interface TweetServices {

    List<TweetResponseDto> getAllTweets();

    TweetResponseDto createTweet(TweetRequestDto tweetRequestDto);

    TweetResponseDto getTweet(Long id);

    TweetResponseDto deleteTweet(Long id);

    TweetResponseDto likeTweet(Long id, CredentialsDto credentialsDto);

    List<UserResponseDto> likedByUsers(Long id);

    TweetResponseDto replyToTweet(Long id);

    List<TweetResponseDto> getReplies(Long id);

    TweetResponseDto repostTweet(Long id);

    List<HashtagDto> getTag(Long id);

    TweetResponseDto getContext(Long id);

    List <TweetResponseDto> getReposts(Long id);

    List<UserResponseDto> getMentions(Long id);


}
