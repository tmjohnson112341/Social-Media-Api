package com.cooksys.socialmedia.services.impl;

import com.cooksys.socialmedia.dtos.CredentialsDto;
import com.cooksys.socialmedia.dtos.TweetRequestDto;
import com.cooksys.socialmedia.dtos.TweetResponseDto;
import com.cooksys.socialmedia.entities.Credentials;
import com.cooksys.socialmedia.entities.Tweet;
import com.cooksys.socialmedia.entities.User;
import com.cooksys.socialmedia.exceptions.NotAuthorizedException;
import com.cooksys.socialmedia.exceptions.NotFoundException;
import com.cooksys.socialmedia.mappers.CredentialsMapper;
import com.cooksys.socialmedia.mappers.TweetMapper;
import com.cooksys.socialmedia.repositories.TweetRepository;
import com.cooksys.socialmedia.repositories.UserRepository;
import org.springframework.stereotype.Service;

import com.cooksys.socialmedia.services.TweetServices;

import lombok.RequiredArgsConstructor;

import java.util.*;

@Service
@RequiredArgsConstructor
public class TweetServicesImpl implements TweetServices{

    private TweetMapper tweetMapper;
    private TweetRepository tweetRepository;

    private UserRepository userRepository;

    private CredentialsMapper credentialsMapper;

    private User getUserWithCreds (Credentials credentials) {
        Optional<User> optionalUser = userRepository.findByCredentialsUsername(credentials.getUsername());
        if (optionalUser.isEmpty() || optionalUser.get().isDeleted()) {
            throw new NotFoundException("No user found with username : " +credentials.getUsername());
        }

        if (!optionalUser.get().getCredentials().getPassword().equals(credentials.getPassword())) {
            throw new NotAuthorizedException("Incorrect password!");
        }
        return optionalUser.get();
    }


    private Tweet getTweetById(Long id) {
        Optional<Tweet> optionalTweet = tweetRepository.findById(id);
        if (optionalTweet.isEmpty() || optionalTweet.get().isDeleted()) {
            throw new NotFoundException("That tweet doesn't exist");
        }
        return optionalTweet.orElse(null);
    }


    @Override
    public List<TweetResponseDto> getAllTweets(){
        List<Tweet> realTweets = new ArrayList<>();
        for (Tweet i : tweetRepository.findAll()) {
            if (!i.isDeleted()) {
                realTweets.add(i);
            }
        }
        realTweets.sort(Comparator.comparing(Tweet::getPosted));
        Collections.reverse(realTweets);
        return tweetMapper.entitiesToDtos(realTweets);
    }


    @Override
    public TweetResponseDto createTweet (TweetRequestDto tweetRequestDto) {
        Tweet newTweet = tweetMapper.dtoToEntity(tweetRequestDto);
        return tweetMapper.entityToDto(tweetRepository.saveAndFlush(newTweet));
    }

    @Override
    public TweetResponseDto getTweet (Long id) {
        Tweet foundTweet = getTweetById(id);
        return tweetMapper.entityToDto(foundTweet);
    }

    @Override
    public TweetResponseDto deleteTweet (Long id) {
       Tweet deletedTweet = getTweetById(id);
       deletedTweet.setDeleted(true);
       return tweetMapper.entityToDto(tweetRepository.saveAndFlush(deletedTweet));
    }

    @Override
    public TweetResponseDto likeTweet(Long id, CredentialsDto credentialsDto) {
        Tweet tweet = getTweetById(id);
        User user = getUserWithCreds(credentialsMapper.dtoToEntity(credentialsDto));
        tweet.getLikes().add(user);
        tweetRepository.saveAndFlush(tweet);

        user.getLikedTweets().add(tweet);
        userRepository.saveAndFlush(user);
        return tweetMapper.entityToDto(tweet);

    }

    @Override
    public TweetResponseDto replyToTweet(Long id) {
        return null;

    }

    @Override
    public TweetResponseDto repostTweet(Long id) {
        return null;
    }

    @Override
    public TweetResponseDto getTag (Long id) {
        return null;
    }

    @Override
    public TweetResponseDto getContext(Long id) {
        return null;
    }

    @Override
    public TweetResponseDto getReposts(Long id) {
        return null;

    }

    @Override
    public TweetResponseDto getMentions(Long id) {
        return null;
    }





}
