package com.cooksys.socialmedia.dtos;

import java.sql.Timestamp;

public class TweetResponseDto {
    private Integer id;
    private UserResponseDto author;
    private Timestamp posted;
    private String content;
    private TweetResponseDto inReplyTo;
    private TweetResponseDto repostOf;
}
