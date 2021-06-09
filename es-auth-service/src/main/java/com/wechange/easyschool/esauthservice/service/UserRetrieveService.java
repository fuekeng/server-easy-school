package com.wechange.easyschool.esauthservice.service;

import com.wechange.easyschool.esmodel.entity.user.User;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserRetrieveService {
	 Optional<User> get(String id);
     Optional<User> getByPseudo(String pseudo);
     Optional<User>getByEmailIgnoreCase(String email);
     Optional<User> findById(String id);
     Optional<User> findByEmail(String email);
     Optional<User> findByPseudo(String email);
     Optional<User> findByEmailIgnoreCase(String email);
     Boolean existsByEmail(String email);
     List<User> getAll();
     Long countPseudoLike(String pseudo);
     Optional<User> findByUsernameOrPseudo(String email, String pseudo);
     String retrieveFirstName(String fullName);
     String retrieveLastName(String fullName);
     
	 public Page<User> findByFirstNameContainingIgnoreCase(String firstName, Pageable paging);
	 public Page<User>  getAll(Pageable paging);
}
