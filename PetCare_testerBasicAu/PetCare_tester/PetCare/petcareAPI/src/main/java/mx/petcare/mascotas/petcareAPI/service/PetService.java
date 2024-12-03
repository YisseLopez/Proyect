package mx.petcare.mascotas.petcareAPI.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import mx.petcare.mascotas.petcareAPI.model.Pet;
import mx.petcare.mascotas.petcareAPI.repository.PetRepository;

@Service
@Transactional
public class PetService {
    @Autowired
    private PetRepository repo; 

    public List<Pet> getAll() {
        return repo.findAll();
    }

    public Pet save(Pet pet) {
        return repo.save(pet);
    }

    public Pet getByidPet(Integer petId) {
        return repo.findById(petId).orElse(null); 
    }

    public void delete(Integer petId) {
        repo.deleteById(petId);
    }

    public Pet findById(Integer id) {
        return repo.findById(id).orElse(null);
    }

    
    public Page<Pet> getPetsPaginated(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return repo.findAll(pageable);
    }
    
    
    public Page<Pet> findByPetNameContaining(String petName, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return repo.findByPetNameContaining(petName, pageable);
    }
}
