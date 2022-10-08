package com.cooksys.socialmedia.services;

import java.util.List;

import com.cooksys.socialmedia.dtos.ContextDto;
import com.cooksys.socialmedia.dtos.CredentialsDto;
import com.cooksys.socialmedia.dtos.HashtagDto;
import com.cooksys.socialmedia.dtos.TweetRequestDto;
import com.cooksys.socialmedia.dtos.TweetResponseDto;
import com.cooksys.socialmedia.dtos.UserResponseDto;

public interface TweetServices {

	List<TweetResponseDto> getAllTweets();

	TweetResponseDto createTweet(TweetRequestDto tweetRequestDto);

	TweetResponseDto getTweet(Long id);

	TweetResponseDto deleteTweet(Long id);

	TweetResponseDto likeTweet(Long id, CredentialsDto credentialsDto);

	List<UserResponseDto> likedByUsers(Long id);

	TweetResponseDto replyToTweet(Long id, TweetRequestDto tweetRequestDto);

	List<TweetResponseDto> getReplies(Long id);

	TweetResponseDto repostTweet(Long id, CredentialsDto credentialsDto);

	List<HashtagDto> getTag(Long id);

	ContextDto getContext(Long id);

	List<TweetResponseDto> getReposts(Long id);

	List<UserResponseDto> getMentions(Long id);

}
