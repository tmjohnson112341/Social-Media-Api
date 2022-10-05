package com.cooksys.socialmedia.dtos;

@NoArgsConstructor
@Data
public class TweetRequestDto {
    private String content;
    private CredentialsDto credentials;
}
