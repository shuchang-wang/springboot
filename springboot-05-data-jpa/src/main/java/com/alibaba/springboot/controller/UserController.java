package com.alibaba.springboot.controller;

import com.alibaba.springboot.entities.User;
import com.alibaba.springboot.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping("/user/{id}")
    public User findOne(@PathVariable("id") Integer id) {
        return userService.findOne(id);
    }

    @GetMapping("/user")
    public List<User> findAll() {
        return userService.findAll();
    }

    @PutMapping("/user")
    public User save(User user) {
        return userService.save(user);
    }

    @GetMapping("/user/exists/{id}")
    public boolean exists(@PathVariable("id") Integer id) {
        return userService.exists(id);
    }

    @GetMapping("/user/count")
    public long count() {
        return userService.count();
    }

    @DeleteMapping("/user/{id}")
    public void delete(@PathVariable("id") Integer id) {
        userService.delete(id);
    }

    @DeleteMapping("/user/all")
    public void deleteAll() {
        userService.deleteAll();
    }

}
