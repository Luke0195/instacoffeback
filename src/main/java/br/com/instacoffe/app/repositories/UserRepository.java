package br.com.instacoffe.app.repositories;

import br.com.instacoffe.app.domain.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    @Query(value = "{'email': ?0}")
    Optional<User> findByEmail(String email);
}
