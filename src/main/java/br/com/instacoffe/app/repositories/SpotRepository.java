package br.com.instacoffe.app.repositories;

import br.com.instacoffe.app.domain.models.Spot;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpotRepository extends MongoRepository<Spot, String> {
    @Query(value = "{'name': ?0}")
    Optional<Spot> findByName(String name);
}
