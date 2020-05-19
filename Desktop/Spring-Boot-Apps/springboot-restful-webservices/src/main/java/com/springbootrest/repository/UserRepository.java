package com.springbootrest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springbootrest.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
