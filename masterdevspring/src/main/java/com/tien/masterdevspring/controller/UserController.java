package com.tien.masterdevspring.controller;


import com.tien.masterdevspring.model.User;
import com.tien.masterdevspring.service.IUserService;
import exception.ValidationRunTimeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService iUserService;
    //Test
    @GetMapping("/")
    public String test() {
        return "Hello World";
    }


    // API add user
    @PostMapping("/add")
    public User addUser(@RequestBody User user)  {
        return iUserService.addUser(user);
    }
    // API update user
    @PutMapping("/update/{id}")
    public User updateUser(@PathVariable("id") int id, @RequestBody User user) throws ValidationRunTimeException {
        return iUserService.updateUser( id ,user);
    }
    // API delete user

    @DeleteMapping("/delete/{id}")
    public boolean deleteUser(@PathVariable("id") int id) {
        return iUserService.deleteUser(id);
    }
    // API get all user
    @GetMapping("/getall")
    public List<User> getAllUser() {
        return iUserService.getAllUser();
    }
    @GetMapping("/get/{id}")
    public User getUser(@PathVariable("id") int id) {
        return iUserService.getUser(id);
    }
}

