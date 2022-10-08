package com.cooksys.socialmedia.controllers;

import com.cooksys.socialmedia.dtos.HashtagDto;
import com.cooksys.socialmedia.dtos.TweetResponseDto;
import com.cooksys.socialmedia.services.HashtagServices;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tags")
public class HashtagController {

    private final HashtagServices hashtagServices;

    //@Getmapping get all hashtags
	@GetMapping
	public List<HashtagDto> getAllHashtags() {
		return hashtagServices.getAllHashtags();
	}

    //@GetMapping("/{label}") //retrieves all  non-deleted tweets contain given hashtag
	
	@GetMapping("/{label}")
	public List<TweetResponseDto> getTweetsByHashtag(@PathVariable String label) {
		return hashtagServices.getTweetsByHashtag(label);
	}

}
