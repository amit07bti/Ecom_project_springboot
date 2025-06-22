package com.pearsystem.service;

import com.pearsystem.exception.ResourceNotFoundException;
import com.pearsystem.model.User;
import com.pearsystem.payload.UserDto;
import com.pearsystem.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private  UserRepository userRepo;
    @Autowired
    private ModelMapper mapper;

    public UserDto  createUser(UserDto userDto) {
        // userDto to User
      User user=  this.mapper.map(userDto, User.class);
      //save
       User saveUser= this.userRepo.save(user);
       //user to userDto
        UserDto saveUserDto= this.mapper.map(saveUser, UserDto.class);
        return saveUserDto;
    }
   public UserDto getById(int userId) {
       User findById= this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User Not Found"));
       UserDto userDto=this.mapper.map(findById,UserDto.class);
        return userDto;
    }


    public void  deleteUser(int userId){
      User findById= this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User Not Found"));
      this.userRepo.delete(findById);

    }
    public List<UserDto> getAllUsers() {
        List<User> findAll=this.userRepo.findAll();
        // user--> userDto
        List<UserDto> collect=findAll.stream().map(each->this.mapper.map(each,UserDto.class)).collect(Collectors.toList());
        return collect;
    }








}
