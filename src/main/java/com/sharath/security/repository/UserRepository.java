package com.sharath.security.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sharath.security.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	List<User> getallUser = null;

	User findByEmail(String email);



}
