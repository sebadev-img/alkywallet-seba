package com.alkemy.wallet.controller;

import com.alkemy.wallet.dto.UserDto;
import com.alkemy.wallet.dto.request.UserUpdateRequestDto;
import com.alkemy.wallet.dto.response.PageableUserResponseDto;
import com.alkemy.wallet.dto.response.UserInfoResponseDto;
import com.alkemy.wallet.entity.User;
import com.alkemy.wallet.service.IUserService;
import com.alkemy.wallet.service.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private final IUserService userService;

    public UserController(UserServiceImpl userService){
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<PageableUserResponseDto> getUsers(@RequestParam(defaultValue = "0") int page){
        PageableUserResponseDto response = userService.getUsers(page);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable Long id){

        User user=userService.deleteUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserInfoResponseDto> getUserById(@PathVariable Long id, @RequestHeader(name = HttpHeaders.AUTHORIZATION) String token){
        UserInfoResponseDto userInfo = userService.getUserById(id,token);
        return new ResponseEntity<>(userInfo,HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserInfoResponseDto> updateUser(@PathVariable Long id, @RequestBody UserUpdateRequestDto userRequest, @RequestHeader(name = HttpHeaders.AUTHORIZATION) String token){
        UserInfoResponseDto userInfo = userService.updateUser(id,userRequest,token);
        return new ResponseEntity<>(userInfo,HttpStatus.OK);
    }
}
