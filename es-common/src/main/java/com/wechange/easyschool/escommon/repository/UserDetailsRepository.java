package com.wechange.easyschool.escommon.repository;

import com.wechange.easyschool.esmodel.entity.user.User;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDetailsRepository extends MongoRepository<User, String>, PagingAndSortingRepository<User, String> {

    @Cacheable(value = "users", key = "#p0")
    @Override
    Optional<User> findById(String id);

    @Cacheable(value = "users", key = "#p0")
    Optional<User> findByEmail(String email);

    @Cacheable(value = "users", key = "#p0")
    Boolean existsByEmail(String email);

    @Cacheable(value = "users", key = "#p0p1")
    Optional<User> findByEmailOrUsername(String email, String username);
}
