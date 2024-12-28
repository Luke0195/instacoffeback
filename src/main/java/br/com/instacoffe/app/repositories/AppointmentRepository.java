package br.com.instacoffe.app.repositories;

import br.com.instacoffe.app.domain.models.Appointment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends MongoRepository<Appointment, String> {
}
