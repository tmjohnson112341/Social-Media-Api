package com.cooksys.socialmedia.mappers;

import com.cooksys.socialmedia.dtos.TweetRequestDto;
import com.cooksys.socialmedia.dtos.TweetResponseDto;
import com.cooksys.socialmedia.entities.Tweet;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = { UserMapper.class })
public interface TweetMapper {
    TweetResponseDto entityToDto (Tweet entity);
    Tweet dtoToEntity (TweetRequestDto tweetRequestDto);

}
