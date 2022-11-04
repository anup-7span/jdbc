package com.jdbc.demo.controller;

import com.jdbc.demo.entity.User;
import com.jdbc.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userServices;

    @PostMapping("/add")
    public User addUser(@RequestBody User user){
        return userServices.saveUser(user);
    }
    @PutMapping("/user")
    public User update(@RequestBody User user){
        return userServices.updateUser(user);
    }
    @GetMapping("/{id}")
    public User get(@PathVariable Integer id){
        return userServices.getUser(id);
    }
   @GetMapping()
   public List<User> getAll(){
        return userServices.allUser();
   }

}
