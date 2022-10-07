package com.cooksys.socialmedia.services;

import java.util.List;

import com.cooksys.socialmedia.dtos.HashtagDto;
import com.cooksys.socialmedia.dtos.TweetResponseDto;

public interface HashtagServices {

	List<HashtagDto> getAllHashtags();

	List<TweetResponseDto> getTweetsByHashtag(String label);
}
