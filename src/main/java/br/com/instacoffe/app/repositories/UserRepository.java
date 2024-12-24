package br.com.instacoffe.app.repositories;

import br.com.instacoffe.app.domain.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
}
