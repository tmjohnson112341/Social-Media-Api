package com.cooksys.socialmedia.mappers;

import com.cooksys.socialmedia.dtos.HashtagDto;
import com.cooksys.socialmedia.entities.Hashtag;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HashtagMapper {
    HashtagDto entityToDto (Hashtag entity);
}
