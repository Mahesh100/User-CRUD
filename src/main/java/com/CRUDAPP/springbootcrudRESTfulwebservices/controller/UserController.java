package com.CRUDAPP.springbootcrudRESTfulwebservices.controller;

import com.CRUDAPP.springbootcrudRESTfulwebservices.entity.User;
import com.CRUDAPP.springbootcrudRESTfulwebservices.exception.ResourceNotFoundException;
import com.CRUDAPP.springbootcrudRESTfulwebservices.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    //Get all users

    @GetMapping("/")
   public List<User> getAllUsers(){
       return this.userRepository.findAll();
   }
    //GET user by id

    @GetMapping("/{id}")  //id is path variable
    public User getUserById(@PathVariable (value = "id") long userId){
        return this.userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User not found with id"+ userId));
    }
    //create user

    @PostMapping("/addUser")
    public User createUser(@RequestBody User user){
        return this.userRepository.save(user);
    }
    //update user

    @PutMapping("/{id}")
    public User updateUser(@RequestBody User user, @PathVariable("id") long userId){
        User existingUser = this.userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User not found with id"+ userId));
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        return this.userRepository.save(existingUser);
    }
    //delete user by id
    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable(value = ("id")) long userId){
        User existingUser = this.userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User not found with id"+ userId));
        userRepository.delete(existingUser);
        return ResponseEntity.ok().build();
    }

}
