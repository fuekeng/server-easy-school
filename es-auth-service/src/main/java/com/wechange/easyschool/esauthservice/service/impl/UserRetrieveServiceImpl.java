package com.wechange.easyschool.esauthservice.service.impl;


import com.wechange.easyschool.esauthservice.repository.UserRepository;
import com.wechange.easyschool.esauthservice.service.UserRetrieveService;
import com.wechange.easyschool.escommon.common.RestResponse;
import com.wechange.easyschool.esmodel.entity.course.Course;
import com.wechange.easyschool.esmodel.entity.user.User;

import lombok.AllArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;




@Service
@AllArgsConstructor
public class UserRetrieveServiceImpl implements UserRetrieveService {


    private UserRepository userRepository;


    @Override
    public Optional<User> get(String id){
		return this.userRepository.findById(id);			 
	}

    @Override
    public Optional<User>getByPseudo(String pseudo) {
        return null;
    }

    @Override
    public Optional<User> getByEmailIgnoreCase(String email) {
        return null;
    }

    @Override
    public Optional<User> findById(String id) {
        return Optional.empty();
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.empty();
    }

    @Override
    public Optional<User> findByPseudo(String email) {
        return Optional.empty();
    }

    @Override
    public Optional<User> findByEmailIgnoreCase(String email) {
        return userRepository.findOneByEmailIgnoreCase(email);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return null;
    }

    @Override
    public List<User> getAll() {
    	return this.userRepository.findByDeleted(false);	
  
    }

    @Override
    public Long countPseudoLike(String pseudo) {
        return null;
    }

    @Override
    public Optional<User> findByUsernameOrPseudo(String username, String pseudo) {

        return userRepository.findByUsernameOrPseudo(username,pseudo);
    }

    @Override
    public String retrieveFirstName(String fullName) {
        return null;
    }

    @Override
    public String retrieveLastName(String fullName) {
        return null;
    }

	@Override
	public Page<User> findByFirstNameContainingIgnoreCase(String firstName, Pageable paging) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<User> getAll(Pageable paging) {
		// TODO Auto-generated method stub
		return userRepository.findAll(paging);
	}
}
