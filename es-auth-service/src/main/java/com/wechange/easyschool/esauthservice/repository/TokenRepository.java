package com.wechange.easyschool.esauthservice.repository;


import com.wechange.easyschool.esmodel.entity.user.Token;
import com.wechange.easyschool.esmodel.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TokenRepository extends MongoRepository<Token, Long>, PagingAndSortingRepository<Token, Long> {

    Token save(Token token);
    Optional<Token> findByToken(String token);
    Optional<Token> findByUser(User user);

}
