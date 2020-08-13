package com.alibaba.springboot.dao;

import com.alibaba.springboot.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IUserDaoRepository extends JpaRepository<User, Integer> {

}
