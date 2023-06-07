package com.example.vestiback.repository;
import com.example.vestiback.model.Item;
import com.example.vestiback.model.Outfit;
import com.example.vestiback.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    @Override
    <S extends User> List<S> saveAll(Iterable<S> entities);


}