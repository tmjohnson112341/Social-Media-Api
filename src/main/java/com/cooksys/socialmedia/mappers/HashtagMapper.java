package com.cooksys.socialmedia.mappers;

import com.cooksys.socialmedia.dtos.HashtagDto;
import com.cooksys.socialmedia.entities.Hashtag;

import java.util.List;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HashtagMapper {
    HashtagDto entityToDto (Hashtag entity);
    
    List<HashtagDto> entitiesToDtos(List<Hashtag> entities);
}
