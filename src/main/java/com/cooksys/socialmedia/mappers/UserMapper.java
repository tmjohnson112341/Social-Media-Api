package com.cooksys.socialmedia.mappers;

import com.cooksys.socialmedia.dtos.UserRequestDto;
import com.cooksys.socialmedia.dtos.UserResponseDto;
import com.cooksys.socialmedia.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = { ProfileMapper.class, CredentialsMapper.class })
public interface UserMapper {
    
    @Mapping(target = "username", source = "credentials.username")
    UserResponseDto entityToDto (User entity);

    User dtoToEntity (UserRequestDto userRequestDto);

    List<UserResponseDto> entitiesToDtos(List<User> livingMentionedUsers);
}
