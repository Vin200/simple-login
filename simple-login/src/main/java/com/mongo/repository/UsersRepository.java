package com.mongo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mongo.model.UserModel;

public interface UsersRepository extends JpaRepository<UserModel, Integer>{
	Optional<UserModel> findByLoginAndPassword(String login, String password);
}
