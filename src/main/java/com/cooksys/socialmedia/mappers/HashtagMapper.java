package com.cooksys.socialmedia.mappers;

import com.cooksys.socialmedia.dtos.HashtagDto;
import com.cooksys.socialmedia.entities.Hashtag;

import java.util.List;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface HashtagMapper {
    HashtagDto entityToDto (Hashtag entity);
<<<<<<< HEAD
    
    List<HashtagDto> entitiesToDtos(List<Hashtag> entities);
=======
    List<HashtagDto> entitiesToDtos (List<Hashtag> entities);
>>>>>>> 70e21501ed82eebea7cc58de1fed82cf96c93e2e
}
