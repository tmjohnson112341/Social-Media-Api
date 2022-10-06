package com.cooksys.socialmedia.mappers;


import com.cooksys.socialmedia.dtos.ProfileDto;
import com.cooksys.socialmedia.entities.Profile;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface ProfileMapper {
    ProfileDto entityToDto (Profile entity);
}
