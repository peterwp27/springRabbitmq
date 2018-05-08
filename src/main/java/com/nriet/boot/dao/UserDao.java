package com.nriet.boot.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nriet.boot.model.User;

public interface UserDao extends JpaRepository<User, Integer> {

}
