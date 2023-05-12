package com.mongo.service;

import org.springframework.stereotype.Service;

import com.mongo.model.UserModel;
import com.mongo.repository.UsersRepository;

@Service
public class UserService {
	
	private final UsersRepository usersRepository;
	
	public UserService(UsersRepository usersRepository) {
		this.usersRepository = usersRepository;
	}
	
	public UserModel registerUser(String login, String password, String email) {
		if(login == null || password == null) {
			return null;
		}else {
			UserModel userModel = new UserModel();
			userModel.setLogin(login);
			userModel.setPassword(password);
			userModel.setEmail(email);
			return usersRepository.save(userModel);
		}
	}
	
	public UserModel authenticate(String login, String password) {
		return usersRepository.findByLoginAndPassword(login, password).orElse(null);
	}
}
