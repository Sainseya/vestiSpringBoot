package com.example.vestiback.repository;


import com.example.vestiback.model.ERole;
import com.example.vestiback.model.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RoleRepository extends MongoRepository<Role, Long> {

    Optional<Role> findByName(ERole name);
}
