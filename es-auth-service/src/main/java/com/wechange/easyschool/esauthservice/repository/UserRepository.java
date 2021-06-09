package com.wechange.easyschool.esauthservice.repository;

import com.wechange.easyschool.esmodel.entity.course.Course;
import com.wechange.easyschool.esmodel.entity.user.User;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import java.nio.channels.FileChannel;
import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepository extends MongoRepository<User, String>, PagingAndSortingRepository<User,String> {
	public List<User> findByDeleted(boolean b);
	
    //@CachePut(value = "users", key = "#p0")
    @Override
    User save(User user);

    //@Cacheable(value = "users", key = "#p0")
    @Override
    Optional<User> findById(String id);

    @Cacheable(value = "users", key = "#p0")
    @Override
    List<User> findAll();

    @CacheEvict(value = "users", allEntries=true)
    @Override
    void delete(User user);

    @Cacheable(value = "users", key = "#p0")
    Optional<User> findByPseudo(String pseudo);

    Optional<User> findByEmail(String email);

    Boolean existsByEmail(String email);

    @Cacheable(value = "users", key = "#p0")
    Optional<User> findByUsernameOrPseudo(String email, String pseudo);

    Optional<User> findOneByEmailIgnoreCase(String email);

    Optional<User> findOneByActivationKey(String key);

    Optional<User> findOneByUsername(String username);

    Optional<User> findByUsernameOrEmail(String username, String email);
	public Page<Course> findByFirstNameContainingIgnoreCase(String firstName, Pageable paging);
}
