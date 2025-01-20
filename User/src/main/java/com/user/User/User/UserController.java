package com.user.User.User;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody User user){
       userService.createUser(user);
       return new ResponseEntity<>("User Created Successfully", HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUser(){

        return ResponseEntity.ok(userService.getAllUser());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id){
        User user=userService.getUserById(id);
        if(user!=null){
            return new ResponseEntity(user,HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(user,HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateById(@PathVariable Long id,@RequestBody User user){
        boolean updated= userService.updateById(id,user);
        if(updated){
            return new ResponseEntity<>("User Updated Successfully",HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("User Not Found", HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id){
        boolean deleted= userService.deleteById(id);
        if(deleted){
            return new ResponseEntity<>("User Deleted Successfully",HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("User Not Found",HttpStatus.NOT_FOUND);
        }
    }

}
