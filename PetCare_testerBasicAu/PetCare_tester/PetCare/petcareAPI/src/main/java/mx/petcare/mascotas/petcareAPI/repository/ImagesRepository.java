package mx.petcare.mascotas.petcareAPI.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import mx.petcare.mascotas.petcareAPI.model.ImagesResource;

public interface ImagesRepository extends MongoRepository<ImagesResource, String> {
}


