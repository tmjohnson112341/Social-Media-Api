package com.cooksys.socialmedia.services.impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cooksys.socialmedia.dtos.HashtagDto;
import com.cooksys.socialmedia.dtos.TweetResponseDto;
import com.cooksys.socialmedia.entities.Hashtag;
import com.cooksys.socialmedia.entities.Tweet;
import com.cooksys.socialmedia.exceptions.NotFoundException;
import com.cooksys.socialmedia.mappers.HashtagMapper;
import com.cooksys.socialmedia.mappers.TweetMapper;
import com.cooksys.socialmedia.repositories.HashTagRepository;
import com.cooksys.socialmedia.services.HashtagServices;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HashtagServicesImpl implements HashtagServices {

	private final HashTagRepository hashtagRepository;

	private final HashtagMapper hashtagMapper;

	private final TweetMapper tweetMapper;

	@Override
	public List<HashtagDto> getAllHashtags() {
		return hashtagMapper.entitiesToDtos(hashtagRepository.findAll());
	}

	@Override
	public List<TweetResponseDto> getTweetsByHashtag(String label) {
		Optional<Hashtag> optionalHashtag = hashtagRepository.findByLabel(label);
		if (optionalHashtag.isEmpty()) {
			throw new NotFoundException("Hashtag not found");
		}
		List<Tweet> tweets = optionalHashtag.get().getTaggedTweets();

		Collections.reverse(tweets);

		return tweetMapper.entitiesToDtos(tweets);
	}

}
