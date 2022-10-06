package com.cooksys.socialmedia.controllers;

import com.cooksys.socialmedia.services.ValidateServices;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/validate")
public class ValidateController {

    private ValidateServices validateServices;


    //@GetMapping("tag/exists/{label}") // Given hashtag exists
    
    @GetMapping("tag/exists/{label}")
    public boolean validateTag(@PathVariable String label) {
    	return validateServices.validateTag(label);
    }
    

    //@GetMapping("/username/exists/@{username}") // given username exists
    
    @GetMapping("/username/exists/@{username}")
    public boolean validateUsername(@PathVariable String username) {
    	return validateServices.validateUsername(username);
    }


    //@GetMapping("/username/available/@{username}") //given username available
    
    @GetMapping("/username/available/@{username}")
    public boolean validateUsernameAvailable(@PathVariable String username) {
    	return validateServices.validateUsernameAvailable(username);
    }






}
