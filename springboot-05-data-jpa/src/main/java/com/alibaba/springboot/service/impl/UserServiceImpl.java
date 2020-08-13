package com.alibaba.springboot.service.impl;

import com.alibaba.springboot.dao.IUserDaoRepository;
import com.alibaba.springboot.entities.User;
import com.alibaba.springboot.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDaoRepository userDaoRepository;

    @Override
    public List<User> findAll() {
        return userDaoRepository.findAll();
    }

    @Override
    public User findOne(Integer id) {
        return userDaoRepository.findOne(id);
    }

    @Override
    public User save(User user) {
        return userDaoRepository.save(user);
    }

    @Override
    public boolean exists(Integer id) {
        return userDaoRepository.exists(id);
    }

    @Override
    public long count() {
        return userDaoRepository.count();
    }

    @Override
    public void delete(Integer id) {
        userDaoRepository.delete(id);
    }

    @Override
    public void deleteAll() {
        userDaoRepository.deleteAll();
    }
}
