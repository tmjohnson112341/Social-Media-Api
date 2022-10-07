package com.cooksys.socialmedia.services.impl;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;

import org.springframework.stereotype.Service;

import com.cooksys.socialmedia.dtos.ContextDto;
import com.cooksys.socialmedia.dtos.CredentialsDto;
import com.cooksys.socialmedia.dtos.HashtagDto;
import com.cooksys.socialmedia.dtos.TweetRequestDto;
import com.cooksys.socialmedia.dtos.TweetResponseDto;
import com.cooksys.socialmedia.dtos.UserResponseDto;
import com.cooksys.socialmedia.entities.Credentials;
import com.cooksys.socialmedia.entities.Hashtag;
import com.cooksys.socialmedia.entities.Tweet;
import com.cooksys.socialmedia.entities.User;
import com.cooksys.socialmedia.exceptions.BadRequestException;
import com.cooksys.socialmedia.exceptions.NotAuthorizedException;
import com.cooksys.socialmedia.exceptions.NotFoundException;
import com.cooksys.socialmedia.mappers.CredentialsMapper;
import com.cooksys.socialmedia.mappers.HashtagMapper;
import com.cooksys.socialmedia.mappers.TweetMapper;
import com.cooksys.socialmedia.mappers.UserMapper;
import com.cooksys.socialmedia.repositories.HashTagRepository;
import com.cooksys.socialmedia.repositories.TweetRepository;
import com.cooksys.socialmedia.repositories.UserRepository;
import com.cooksys.socialmedia.services.TweetServices;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TweetServicesImpl implements TweetServices{

    private TweetMapper tweetMapper;
    private TweetRepository tweetRepository;

    private UserRepository userRepository;

    private CredentialsMapper credentialsMapper;

    private HashtagMapper hashtagMapper;

    private HashTagRepository hashtagRepository;

    private UserMapper userMapper;

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

    private List<Hashtag> hashtagFinder (Tweet tweet) {
        String[] searcher = tweet.getContent().split("\\s+");
        List <String> rawHashtags = new ArrayList<>();
        for (int i = 0; i < searcher.length; i++) {
            if (searcher[i].startsWith("#")) {

            rawHashtags.add(searcher[i].substring(1));
            }
        }
        List <Hashtag> cookedHashtags = new ArrayList<>();
        for (int i = 0; i < rawHashtags.size(); i++){
            Optional<Hashtag> hashtagValidation = hashtagRepository.findByLabel(rawHashtags.get(i));
            if (hashtagValidation.isPresent()){
                Hashtag h = hashtagValidation.get();
                h.setLastUsed(Timestamp.valueOf(LocalDateTime.now()));
                h.getTaggedTweets().add(tweet);
                hashtagRepository.saveAndFlush(h);
                cookedHashtags.add(h);
            }
            else {
                Hashtag h = new Hashtag();
                h.setLabel(rawHashtags.get(i));
                h.setFirstUsed(Timestamp.valueOf(LocalDateTime.now()));
                h.setLastUsed(Timestamp.valueOf(LocalDateTime.now()));
                h.getTaggedTweets().add(tweet);
                hashtagRepository.saveAndFlush(h);
                cookedHashtags.add(h);
            }
        }

        return cookedHashtags;
    }

    private List<User> mentionFinder (Tweet tweet) {
        String[] searcher = tweet.getContent().split("\\s+");
        List <String> rawMentions = new ArrayList<>();
        for (int i = 0; i < searcher.length; i++) {
            if (searcher[i].startsWith("@")) {
                // check if user exists?
                rawMentions.add(searcher[i].substring(1));
            }
        }
        List <User> cookedMentions = new ArrayList<>();
        for (int i = 0; i < rawMentions.size(); i++){
            Optional<User> mentionValidation = userRepository.findByCredentialsUsername(rawMentions.get(i));
            if (mentionValidation.isPresent()) {
                User m = mentionValidation.get();
                m.getTweetsMentioned().add(tweet);
                userRepository.saveAndFlush(m);
                cookedMentions.add(m);
            }
        }
        return cookedMentions;
    }

    private List<Tweet> beforeChain (Tweet tweet){
        Tweet blank = tweet;
        List<Tweet> rawChain = new ArrayList<>();
        while (blank.getInReplyTo() != null){
            if (!blank.isDeleted()) {
                rawChain.add(blank);
            }
            blank = blank.getInReplyTo();
        }
        return rawChain;
    }

    private List<Tweet> afterChain (Tweet tweet){
       Tweet blank = tweet;
       List<Tweet> rawChain = new ArrayList<>();
       Queue<Tweet> tweetQ = new LinkedList<>();
       tweetQ.addAll(tweet.getReplies());
       while (!tweetQ.isEmpty()) {
           blank = tweetQ.poll();
           if (!blank.isDeleted()){
               rawChain.add(blank);
           }
           tweetQ.addAll(blank.getReplies());
       }
        return rawChain;
    }


    private Tweet getTweetById(Long id) {
        Optional<Tweet> optionalTweet = tweetRepository.findById(id);
        if (optionalTweet.isEmpty() || optionalTweet.get().isDeleted()) {
            throw new NotFoundException("That tweet no longer exists");
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
        if (tweetRequestDto.getCredentials() != null){
            throw new NotAuthorizedException("Log in to tweet");
        }

        if (tweetRequestDto.getContent() != null){
            throw new BadRequestException("Can't post empty tweet");
        }
        User author = getUserWithCreds(credentialsMapper.dtoToEntities(tweetRequestDto.getCredentials()));

        Tweet tweet = tweetMapper.dtoToEntity(tweetRequestDto);

        author.getTweets().add(tweet);

        userRepository.saveAndFlush(author);

        tweet.setAuthor(author);

        tweetRepository.saveAndFlush(tweet);

        tweet.setHashtags(hashtagFinder(tweet));

        tweet.setMentions(mentionFinder(tweet));

        return tweetMapper.entityToDto(tweetRepository.saveAndFlush(tweet));

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
        User user = getUserWithCreds(credentialsMapper.dtoToEntities(credentialsDto));
        tweet.getLikes().add(user);
        tweetRepository.saveAndFlush(tweet);

        user.getLikedTweets().add(tweet);
        userRepository.saveAndFlush(user);
        return tweetMapper.entityToDto(tweet);

    }

    @Override
    public List<UserResponseDto> likedByUsers(Long id){
        Tweet tweet = getTweetById(id);
        List<User> livingTweetLikers = new ArrayList<>();
        for (User i : tweet.getLikes()) {
            if (!i.isDeleted()){
                livingTweetLikers.add(i);
            }
        }
        return userMapper.entitiesToDtos(livingTweetLikers);
    }

    @Override
    public TweetResponseDto replyToTweet(Long id, TweetRequestDto tweetRequestDto) {
        if (tweetRequestDto.getCredentials() != null){
            throw new NotAuthorizedException("Log in to tweet");
        }

        if (tweetRequestDto.getContent() != null){
            throw new BadRequestException("Can't post empty tweet");
        }
        User author = getUserWithCreds(credentialsMapper.dtoToEntities(tweetRequestDto.getCredentials()));

        Tweet tweet = tweetMapper.dtoToEntity(tweetRequestDto);

        author.getTweets().add(tweet);

        userRepository.saveAndFlush(author);

        tweet.setAuthor(author);

        tweet.setInReplyTo(getTweetById(id));

        tweetRepository.saveAndFlush(tweet);

        tweet.setHashtags(hashtagFinder(tweet));

        tweet.setMentions(mentionFinder(tweet));

        return tweetMapper.entityToDto(tweetRepository.saveAndFlush(tweet));

    }

    @Override
    public List<TweetResponseDto> getReplies(Long id) {
        Tweet tweet = getTweetById(id);
        List<Tweet> rawReplies = tweet.getReplies();
        List<Tweet> livingReplies = new ArrayList<>();
        for (Tweet i : rawReplies){
            if (!i.isDeleted()){
                livingReplies.add(i);
            }
        }
        return tweetMapper.entitiesToDtos(livingReplies);
    }

    @Override
    public TweetResponseDto repostTweet(Long id, CredentialsDto credentialsDto) {
        User user = getUserWithCreds(credentialsMapper.dtoToEntities(credentialsDto));
        Tweet repostee = getTweetById(id);
        Tweet newTweet = new Tweet();
        newTweet.setAuthor(user);
        newTweet.setRepostOf(repostee);

        repostee.getRepostsOf().add(newTweet);
        //save and flush both tweets
        tweetRepository.saveAndFlush(repostee);
        tweetRepository.saveAndFlush(newTweet);
        return tweetMapper.entityToDto(newTweet);

    }

    @Override
    public List<HashtagDto> getTag (Long id) {

        Tweet tweet = getTweetById(id);

        return hashtagMapper.entitiesToDtos(tweet.getHashtags());
    }

    @Override
    public ContextDto getContext(Long id) {
        Tweet tweet = getTweetById(id);
        ContextDto contextDto = new ContextDto();
        contextDto.setBefore(tweetMapper.entitiesToDtos(beforeChain(tweet)));
        contextDto.setAfter(tweetMapper.entitiesToDtos(afterChain(tweet)));
        contextDto.setTarget(tweetMapper.entityToDto(tweet));
        return contextDto;
    }

    @Override
    public List<TweetResponseDto> getReposts(Long id) {
        Tweet tweet = getTweetById(id);
        List <Tweet> rawReposts = tweet.getRepostsOf();
        List <Tweet> livingReposts = new ArrayList<>();
        for (Tweet i : rawReposts) {
            if (!i.isDeleted()){
                livingReposts.add(i);
            }
        }
        return tweetMapper.entitiesToDtos(livingReposts);

    }

    @Override
    public List<UserResponseDto> getMentions(Long id) {
        Tweet tweet = getTweetById(id);

       List<User> mentionedUsers = tweet.getMentions();
       List<User> livingMentionedUsers = new ArrayList<>();
       for (User i : mentionedUsers)  {
           if (!i.isDeleted()){
               livingMentionedUsers.add(i);
           }
       }
       return userMapper.entitiesToDtos(livingMentionedUsers);

    }





}
