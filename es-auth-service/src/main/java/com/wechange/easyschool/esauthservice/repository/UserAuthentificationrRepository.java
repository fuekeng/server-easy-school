package com.wechange.easyschool.esauthservice.repository;


import com.wechange.easyschool.esmodel.entity.user.User;
import org.springframework.cache.annotation.CachePut;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAuthentificationrRepository extends MongoRepository<User, Long>, PagingAndSortingRepository<User, Long> {

   // @CachePut(value = "users", key = "#p0")
    @Override
    User save(User user);
}
