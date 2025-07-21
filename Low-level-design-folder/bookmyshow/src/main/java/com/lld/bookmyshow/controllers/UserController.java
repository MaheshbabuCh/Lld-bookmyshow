package com.lld.bookmyshow.controllers;

import com.lld.bookmyshow.dtos.UserRequestDto;
import com.lld.bookmyshow.dtos.UserResponseDto;
import com.lld.bookmyshow.models.User;
import com.lld.bookmyshow.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    public UserResponseDto createUser(UserRequestDto userRequestDto) {

        User user = userService.createUser(userRequestDto.getEmail(), userRequestDto.getPassword(), userRequestDto.getName());

        UserResponseDto userResponseDto = new UserResponseDto();

        userResponseDto.setEmail(user.getEmail());
        userResponseDto.setName(user.getName());

        return null;
    }
}
