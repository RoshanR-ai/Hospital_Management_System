package com.cts.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.cts.exception.DuplicateUserException;
import com.cts.exception.InvalidCredentialsException;
import com.cts.exception.UserNotFoundException;
import com.cts.model.User;
import com.cts.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository repo;
	
	
	public boolean validateUserCredentials(String username,String password)throws UserNotFoundException,InvalidCredentialsException {		
			User user = repo.findByUserName(username);
			if(user==null)
			{
				throw new UserNotFoundException("No user found with the given username");
			}
			if(!user.getPassword().equals(password)) {
				throw new InvalidCredentialsException("Username doesn't match with the password");
			}
			return true;
	}
	
	
	public User getUserbyUserName(String userName)throws UserNotFoundException {
		 User user = repo.findByUserName(userName);
		 
		 return user;
	}
	
	public User getUserbyUserId(int id) throws UserNotFoundException {
		User user =  repo.findByUserId(id);
		if(user==null) {
			throw new UserNotFoundException("No user found with the given userId");
		}
		return user;
	}
	
	public User saveUser(User user) throws DuplicateUserException{
		try {			
			return repo.save(user);
		}
		catch(DataIntegrityViolationException e) {
			throw new DuplicateUserException("Username is already taken");
		}
	}
	
	public boolean changeUserPassword(String password,String username) {
		int rowsChanged = repo.changePassword(password, username);
		if(rowsChanged==1) {
			return true;
		}
		return false;
	}
	
}
