package mx.petcare.mascotas.petcareAPI.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mx.petcare.mascotas.petcareAPI.model.Pet;

@Repository
public interface PetRepository extends JpaRepository<Pet, Integer>{
     Page<Pet> findByPetNameContaining(String petName, Pageable pageable);
}
