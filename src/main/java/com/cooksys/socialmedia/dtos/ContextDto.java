package com.cooksys.socialmedia.dtos;

import java.util.List;

public class ContextDto {
    private TweetResponseDto target;
    private List<TweetResponseDto> before;
    private List<TweetResponseDto> after;
}
