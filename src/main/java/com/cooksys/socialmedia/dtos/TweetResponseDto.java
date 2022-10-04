package com.cooksys.socialmedia.dtos;

import java.sql.Timestamp;

import com.cooksys.socialmedia.entities.User;

public class TweetResponseDto {
    private Integer id;
    private User author;
    private Timestamp posted;
    private String content;
    private TweetResponseDto inReplyTo;
    private TweetResponseDto repostOf;
}
