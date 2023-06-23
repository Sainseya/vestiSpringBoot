package com.example.vestiback.repository;
import com.example.vestiback.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    @Query(value = "{ 'userId': ?0, 'wardrobes.items.type': ?1 }", fields = "{ 'wardrobes.items.$': 1 }")
    List<User> findUsersByUserIdAndItemType(String userId, String itemType);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);

    Optional<Object> findByUsername(String username);
}