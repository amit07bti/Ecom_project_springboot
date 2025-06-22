package com.pearsystem.controller;

import com.pearsystem.payload.UserDto;
import com.pearsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

     @PostMapping("/create")
    public ResponseEntity<UserDto>  createUser(@RequestBody UserDto userDto) {
         Date date=new Date();
         userDto.setDate(date);
       UserDto createUser=  this.userService.createUser(userDto);
        return new ResponseEntity<>(createUser, HttpStatus.CREATED);
    }
   @GetMapping("/find_id/{userId}")
   public  ResponseEntity<UserDto> findUserById(@PathVariable int userId) {
        UserDto byId= this.userService.getById(userId);
        return new ResponseEntity<>(byId,HttpStatus.FOUND);
    }
    @DeleteMapping("/delete_user/{userId}")
    public void deleteUserById(@PathVariable int userId) {
          this.userService.deleteUser(userId);

    }
    @GetMapping("/find_all")
    public ResponseEntity<List<UserDto>> findAllUsers() {
         List<UserDto> allUsers = this.userService.getAllUsers();
         return new ResponseEntity<>(allUsers,HttpStatus.OK);
    }
}
