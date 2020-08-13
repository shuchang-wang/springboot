package com.alibaba.springboot.service;

import com.alibaba.springboot.entities.User;

import java.util.List;

public interface IUserService {

    List<User> findAll();

    User findOne(Integer id);

    User save(User user);

    long count();

    boolean exists(Integer id);

    void delete(Integer id);

    void deleteAll();
}
