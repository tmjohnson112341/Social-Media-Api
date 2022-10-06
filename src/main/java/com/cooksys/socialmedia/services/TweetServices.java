package com.cooksys.socialmedia.services;

import com.cooksys.socialmedia.dtos.CredentialsDto;
import com.cooksys.socialmedia.dtos.TweetRequestDto;
import com.cooksys.socialmedia.dtos.TweetResponseDto;
import com.cooksys.socialmedia.entities.Tweet;

import java.util.List;

public interface TweetServices {

    List<TweetResponseDto> getAllTweets();

    TweetResponseDto createTweet(TweetRequestDto tweetRequestDto);

    TweetResponseDto getTweet(Long id);

    TweetResponseDto deleteTweet(Long id);

    TweetResponseDto likeTweet(Long id, CredentialsDto credentialsDto);

    TweetResponseDto replyToTweet(Long id);

    TweetResponseDto repostTweet(Long id);

    TweetResponseDto getTag(Long id);

    TweetResponseDto getContext(Long id);

    TweetResponseDto getReposts(Long id);

    TweetResponseDto getMentions(Long id);
}
