package com.cooksys.socialmedia.controllers;

import com.cooksys.socialmedia.services.ValidateServices;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/validate")
public class ValidateController {

    private ValidateServices validateServices;


    //@GetMapping("tag/exists/{label}") // Given hashtag exists


    //@GetMapping("/username/exists/@{username}") // given username exists


    //@GetMapping("/username/available/@{username}") //given username available








}
